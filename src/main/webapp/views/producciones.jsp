<%@ page import="java.util.*" %>
<%@ page import="development.team.DAO.UsuarioDAO" %>
<%@ page import="development.team.DTO.UsuarioRolDTO" %>
<%@ page import="development.team.DAO.ProduccionDAO" %>
<%@ page import="development.team.Models.Produccion" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ page import="java.time.format.DateTimeFormatter" %>
<%@ page import="development.team.DAO.ProductoDAO" %>
<%@ page import="development.team.DTO.ProductoCategoriaEstadoDTO" %>
<%@ page import="com.google.gson.Gson" %>
<%@ page import="development.team.DTO.InsumoCantidadUnidad" %>
<%@ page import="development.team.DAO.RecetaDAO" %>
<%@ page import="development.team.DAO.InsumoDAO" %>

<head>
  <meta charset="UTF-8" />
  <meta name="viewport" content="width=device-width, initial-scale=1.0" />
  <title>Gestión de Producción</title>
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.2/css/all.min.css" />
  <link rel="stylesheet" href="<%=request.getContextPath()%>/assets/css/produccion.css" />
  <script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>
</head>

<%
    List<UsuarioRolDTO> usuariosList = UsuarioDAO.obtenerTodosUsuarios();
    List<Produccion> produccionesList = ProduccionDAO.obtenerProducciones();
    List<ProductoCategoriaEstadoDTO> productosCategoriaList= ProductoDAO.obtenerProductos();

    for (ProductoCategoriaEstadoDTO producto : productosCategoriaList) {
        System.out.println(producto);  // Esto imprime en la consola del servidor
    }

    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    Map<Integer, List<InsumoCantidadUnidad>> recetasMap = RecetaDAO.obtenerRecetasMapeadas();
    Map<String, Double> stockMap = InsumoDAO.obtenerStockInsumos();
    Gson gson = new Gson();
%>

<div class="produccion-container">
    <h1>Gestión de Producción</h1>
    <div class="acciones">
        <button class="btn" id="btnNuevaProduccion"><i class="fas fa-plus"></i> Nueva Producción</button>
    </div>
    <!-- Filtros -->
    <div class="filter-container">
        <input type="text" id="filtro-nombre" placeholder="Buscar por producto..." />
        <select id="filtro-usuario">
            <option value="">Todos los usuarios</option>
            <%
                for (UsuarioRolDTO userList : usuariosList) {
                    int idRol = userList.getIdRol();
                    int idUsuario = userList.getIdUsuario();
                    if ((idRol == 1 || idRol == 4) && idUsuario != 1) {
            %>
            <option value="<%= idUsuario %>">
                <%= userList.getNombreUsuario() + " " + userList.getApellidoPaternoUsr() %>
            </option>
            <%
                    }
                }
            %>
        </select>

        <!-- Nuevo filtro por estado -->
        <select id="statusFilter">
            <option value="">Todos los estados</option>
            <option value="0">EN PROCESO</option>
            <option value="1">FINALIZADA</option>
            <option value="2">ANULADA</option>
        </select>
    </div>

    <!-- Tabla de Producciones -->
    <div class="table-container">
        <table class="user-table">
            <thead>
            <tr>
                <th>ID</th>
                <th>Producto</th>
                <th>Fecha de Producción</th>
                <th>Cantidad Planeada</th>
                <th>Solicitado por</th>
                <th>Estado</th>
                <th>Acciones</th>
            </tr>
            </thead>
            <tbody id="tabla-producciones">
            <%
                int i = 1;
                for (Produccion produccion : produccionesList) {
                    String nombreCompletoUsuario = produccion.getUsuario().getNombres() + " " + produccion.getUsuario().getApellidoPaterno();

                    String estadoTexto = "";
                    String badgeClass = "role-badge ";
                    boolean deshabilitarEditar = false;
                    boolean deshabilitarFinalizar = false;
                    boolean deshabilitarAnular = false;

                    switch (produccion.getEstado()) {
                        case 0:
                            estadoTexto = "EN PROCESO";
                            badgeClass += "role-orange";
                            break;
                        case 1:
                            estadoTexto = "FINALIZADA";
                            badgeClass += "role-green";
                            deshabilitarEditar = true;
                            deshabilitarFinalizar = true;
                            break;
                        case 2:
                            estadoTexto = "ANULADA";
                            badgeClass += "role-red";
                            deshabilitarEditar = true;
                            deshabilitarFinalizar = true;
                            deshabilitarAnular = true;
                            break;
                        default:
                            estadoTexto = "DESCONOCIDO";
                            badgeClass += "role-red"; // fallback
                            deshabilitarEditar = true;
                            deshabilitarFinalizar = true;
                            deshabilitarAnular = true;
                            break;
                    }
            %>
            <tr>
                <td><%= i++ %></td>
                <td><%= produccion.getProducto().getNombre() %></td>
                <td><%= produccion.getFechaProduccion() != null ? produccion.getFechaProduccion().format(formatter) : "" %></td>
                <td><%= produccion.getCantidadPlaneada() %></td>
                <td><%= nombreCompletoUsuario %></td>
                <td><span class="<%= badgeClass %>"><%= estadoTexto %></span></td>
                <td>
                    <button class="btn view" onclick="verDetalle(<%= produccion.getIdProduccion() %>)">
                        <i class="fas fa-eye"></i>
                    </button>
                    <button class="btn edit" onclick="editarProduccion(<%= produccion.getIdProduccion() %>)"
                            <%= deshabilitarEditar ? "disabled" : "" %>>
                        <i class="fas fa-edit"></i>
                    </button>
                    <button class="btn delete" onclick="anularProduccion(<%= produccion.getIdProduccion() %>)"
                            <%= deshabilitarAnular ? "disabled" : "" %>>
                        <i class="fas fa-ban"></i>
                    </button>
                    <button class="btn finalize" onclick="abrirModalFinalizar(<%= produccion.getIdProduccion() %>)"
                            <%= deshabilitarFinalizar ? "disabled" : "" %>>
                        <i class="fas fa-check"></i>
                    </button>
                </td>
            </tr>
            <%
                }
            %>
            </tbody>
        </table>
    </div>

    <!-- Modal Generar Producción -->
    <div id="modal-generar" class="modal oculto">
        <div class="modal-contenido">
            <div class="modal-header">
                <h2>Generar Producción</h2>
                <span class="cerrar-modal">&times;</span>
            </div>
            <form id="form-generar" class="formulario" action="<%=request.getContextPath()%>/produccion" method="post">
                <input type="hidden" name="accion" value="generar">
                <!-- Contenedor de dos columnas -->
                <div class="formulario-doble-columna">
                    <!-- Columna izquierda -->
                    <div class="formulario-izquierda">
                        <label for="producto-select">Producto:</label>
                        <select id="producto-select" name="producto_id" required>
                            <option value="">Seleccione un producto</option>
                        <%
                        for (ProductoCategoriaEstadoDTO productosList : productosCategoriaList) {
                        %>
                            <option value="<%= productosList.getIdProducto() %>">
                                <%=productosList.getNombre() %>
                            </option>
                        <%
                        }
                        %>
                        </select>

                        <label for="cantidad-producir">Cantidad a producir:</label>
                        <input type="number" id="cantidad-producir" name="cantidad" required min="1"/>

                        <!-- <label for="cantidad-producir">Alerta de insumo insuficiente:</label> -->
                        <div id="mensaje-insumos" style="margin-top: 20px;"></div>
                    </div>

                    <!--Columna derecha-->
                    <div class="formulario-derecha">
                        <div id="tabla-insumos-container" class="oculto">
                            <h3>Insumos requeridos</h3>
                            <table id="tabla-insumos" style="margin-top: 15px; width: 100%; border-collapse: collapse; display: table;">
                                <thead>
                                <tr>
                                    <th>Insumo</th>
                                    <th>Cantidad</th>
                                    <th>Unidad</th>
                                </tr>
                                </thead>
                                <tbody id="tabla-insumos-body">
                                <!-- Se llenará dinámicamente -->
                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>
                <!--Botones -->
                <div class="botones">
                    <button type="button" class="cancelar" id="btn-cancelar-generar">Cancelar</button>
                    <button type="submit" id="btn-Generar" disabled>Generar</button>
                </div>
            </form>
        </div>
    </div>

    <!-- Modal Editar Producción -->
    <div id="modal-editar" class="modal oculto">
        <div class="modal-contenido">
            <div class="modal-header">
                <h2>Editar Producción</h2>
                <span class="cerrar-modal">&times;</span>
            </div>
            <form id="form-editar" class="formulario">

                <!-- Contenedor de dos columnas -->
                <div class="formulario-doble-columna">
                    <!-- Columna izquierda -->
                    <div class="formulario-izquierda">
                        <label for="editar-producto-select">Producto:</label>
                        <input type="hidden" id="editar-id" name="id">
                        <select id="editar-producto-select" required>
                            <option value="">Seleccione un producto</option>
                            <option value="Correa negra">Correa negra</option>
                            <option value="Correa marrón">Correa marrón</option>
                        </select>

                        <label for="editar-cantidad-producir">Cantidad a producir:</label>
                        <input type="number" id="editar-cantidad-producir" required min="1" />

                        <!-- <label for="cantidad-producir">Alerta de insumo insuficiente:</label> -->
                        <div id="editar-mensaje-insumos" style="margin-top: 20px;"></div>
                    </div>

                    <!--Columna derecha-->
                    <div class="formulario-derecha">
                        <div id="editar-tabla-insumos-container" class="oculto">
                            <h3>Insumos requeridos</h3>
                            <table id="tabla-insumos" style="margin-top: 15px; width: 100%; border-collapse: collapse; display: table;">
                                <thead>
                                <tr>
                                    <th>Insumo</th>
                                    <th>Cantidad</th>
                                    <th>Unidad</th>
                                </tr>
                                </thead>
                                <tbody id="editar-tabla-insumos-body">
                                <!-- Se llenará dinámicamente -->
                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>
                <!-- Botones -->
                <div class="botones">
                    <button type="button" class="cancelar" id="btn-cancelar-generar">Cancelar</button>
                    <button type="submit">Guardar cambios</button>
                </div>
            </form>
        </div>
    </div>

    <!-- Modal Finalizar Producción -->
    <div id="modalFinalizar" class="modal oculto">
        <div class="modal-contenido expandido">
            <div class="modal-header">
                <h2>Finalizar Producción</h2>
                <span class="cerrar-modal" id="cerrarFinalizar">&times;</span>
            </div>
            <div class="modal-body formulario">

                <!-- Campos de solo lectura: Producto y Cantidad Estimada -->
                <div class="formulario-doble-columna" style="gap:1rem; margin-bottom:1rem;">
                    <div class="formulario-izquierda">
                        <label for="finalizarProducto">Producto:</label>
                        <input type="text" id="finalizarProducto" readonly />
                    </div>
                    <div class="formulario-derecha">
                        <label for="cantidadEstimado">Cantidad Estimada: </label>
                        <input type="number" id="cantidadEstimado" readonly />
                    </div>
                </div>

                <!-- Tabla de insumos -->
                <div id="tabla-insumos-container">
                    <table id="tabla-insumos" border="1" width="100%">
                        <thead>
                        <tr>
                            <th>Insumo</th>
                            <th>Cantidad Prevista</th>
                            <th>Cantidad Real</th>
                            <th>Merma</th>
                        </tr>
                        </thead>
                        <tbody id="tablaInsumosBody">
                        <!-- Aquí se cargarán los insumos dinámicamente -->
                        </tbody>
                    </table>
                </div>

                <!-- Cantidad Real Producida -->
                <label for="cantidadRealProducida">Cantidad Real Producida:</label>
                <input type="number" id="cantidadRealProducida" min="0" value="0" />

                <!-- Productos defectuosos -->
                <label>¿Hubo productos defectuosos?</label>
                <div class="checkbox-container">
                    <div class="checkbox-item">
                        <input type="radio" name="productosDefectuosos" value="si">
                        <label for="defectuososSi">Sí</label>
                    </div>
                    <div class="checkbox-item">
                        <input type="radio" name="productosDefectuosos" value="no" checked>
                        <label for="defectuososNo">No</label>
                    </div>
                </div>


                <!-- Detalles de defectos (condicionales) -->
                <div id="defectosDetalles" class="formulario" style="display:none; margin-top:10px;">
                    <label for="cantidadDefectuosos">Cantidad de Productos Defectuosos:</label>
                    <input type="number" id="cantidadDefectuosos" min="0" value="0" />

                    <label for="motivoDefecto">Motivo del Defecto:</label>
                    <select id="motivoDefecto">
                        <option value="Materia Prima">Materia Prima</option>
                        <option value="Error Operario">Error Operario</option>
                        <option value="Maquinaria">Maquinaria</option>
                        <option value="Problemas Energía">Problemas Energía</option>
                        <option value="Contaminación">Contaminación</option>
                        <option value="Otro">Otro</option>
                    </select>
                    <textarea
                            id="motivoDefectoOtro"
                            maxlength="150"
                            placeholder="Escribe tu motivo (máx. 150 caracteres)"
                            style="display:none; width:100%; margin-top:0.5rem; resize:none;"
                    ></textarea>
                    <small id="contadorOtro" style="display:none; font-size:0.8rem; color:#666;">
                        0 / 150
                    </small>
                </div>
                <!-- Botones -->
                <div class="botones">
                    <button class="cancelar" id="cancelarFinalizar">Cancelar</button>
                    <button type="submit" id="btnGuardarFinalizacion">Guardar</button>
                </div>

            </div>
        </div>
    </div>
</div>

<!-- Modal Ver Detalle -->
<div id="modal-ver" class="modal oculto">
    <div class="modal-contenido" style="width: 850px; height: 660px; ">
        <div class="modal-header">
            <h2>Detalle de Producción</h2>
            <span class="cerrar-modal" id="cerrarVer">&times;</span>
        </div>
        <div class="modal-body formulario">
            <div class="formulario-doble-columna" style="gap: 2rem;">

                <!--Columna Izquierda-->
                <div class="formulario-izquierda">
                    <div class="form-group">
                        <label>Producto:</label>
                        <input type="text" id="verProducto" readonly />
                    </div>
                    <h3>INSUMOS</h3>
                    <table id="verTablaInsumos" class="user-table" style="width:100%; margin-bottom:1rem;">
                        <thead>
                        <tr>
                            <th>Insumo</th>
                            <th>Previsto</th>
                            <th>Real</th>
                            <th>Merma</th>
                        </tr>
                        </thead>
                        <tbody></tbody>
                    </table>
                    <div class="form-group">
                        <label>Total Previstos:</label>
                        <input type="number" id="verTotalPrevistos" readonly />
                    </div>
                    <div class="form-group">
                        <label>Total Reales:</label>
                        <input type="number" id="verTotalReales" readonly />
                    </div>
                    <div class="form-group">
                        <label>Merma Total:</label>
                        <input type="number" id="verMermaTotal" readonly />
                    </div>
                </div>
                <!--Columna Derecha-->
                <div class="formulario-derecha" style="display: block;">
                    <h3>PRODUCCIÓN</h3>
                    <div class="form-group">
                        <label>Unidades Planeadas:</label>
                        <input type="number" id="verPlaneadas" readonly />
                    </div>
                    <div class="form-group">
                        <label>Unidades Producidas:</label>
                        <input type="number" id="verProducidas" readonly />
                    </div>
                    <div class="form-group">
                        <label>Unidades Defectuosas:</label>
                        <input type="number" id="verDefectuosas" readonly />
                    </div>
                    <div class="form-group">
                        <label>Eficiencia (%):</label>
                        <input type="text" id="verEficiencia" readonly />
                    </div>
                </div>
            </div>
            <!--Botón debajo de ambas columnas-->
            <div class="botones" style="margin-top: 1px;">
                <button class="cancelar" id="btnCerrarVer">Cerrar</button>
            </div>
        </div>
    </div>
</div>

<script>
    const recetas = <%= gson.toJson(recetasMap) %>;
    const stockInsumos = <%= gson.toJson(stockMap) %>;
</script>
<script src="<%=request.getContextPath()%>/assets/js/produccion.js"></script>