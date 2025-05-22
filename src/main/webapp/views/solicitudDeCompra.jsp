<%@ page import="development.team.DAO.InsumoDAO" %>
<%@ page import="development.team.Models.Insumo" %>
<%@ page import="java.util.List" %>
<%@ page import="development.team.DAO.OrdenDeCompraDAO" %>
<%@ page import="development.team.Models.Compra" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<head>
    <meta charset="UTF-8">
    <title>Solicitud de Compra</title>
    <link rel="stylesheet" href="<%= request.getContextPath() %>/assets/css/solicitudDeCompra.css">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" />
    <script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>
</head>

<!-- MENSAJE REGISTRO DE SOLICITUD DE COMPRA -->
<%
    String mensaje = (String) session.getAttribute("mensajeExito");
    if (mensaje != null) {
%>
<script>
    Swal.fire('Éxito', '<%= mensaje %>', 'success');
</script>
<%
        session.removeAttribute("mensajeExito"); // Limpiar después de mostrar
    }
%>

<!-- MENSAJE DE EDITAR SOLICITUD -->
<%
    String mensajeEditar = (String) session.getAttribute("mensajeExito");
    if (mensajeEditar != null) {
%>
<script>
    Swal.fire('Éxito', '<%= mensajeEditar %>', 'success');
</script>
<%
        session.removeAttribute("mensajeExito"); // Limpiar después de mostrar
    }
%>

<!-- MENSAJE PARA ANULAR SOLICITUD -->
<%
    String mensajeAnulado = (String) session.getAttribute("mensaje");
    if (mensajeAnulado != null) {
%>
<script>
    Swal.fire('Éxito', '<%= mensajeAnulado %>', 'success');
</script>
<%
        session.removeAttribute("mensaje"); // Limpiar después de mostrar
    }
%>


<div class="content-container">
    <h3>Órdenes de Compra Pendientes</h3>
    <button class="btn btn-primary mb-3" onclick="abrirModalSolicitud()">Nueva Solicitud</button>

    <div class="mb-3">
        <label for="filtroEstado" class="form-label">Filtrar por estado:</label>
        <select id="filtroEstado" class="form-select form-select-sm" onchange="filtrarPorEstado()">
            <option value="">-- Todos --</option>
            <option value="PENDIENTE">PENDIENTE</option>
            <option value="ANULADA">ANULADA</option>
        </select>
    </div>


    <table id="tablaCompras" class="table table-bordered table-sm">
        <thead class="table-light">
        <tr>
            <th>ID</th>
            <th>Fecha Solicitud</th>
            <th>Estado</th>
            <th>Acciones</th>
        </tr>
        </thead>
        <tbody>
        <%
            List<Compra> ordenes = OrdenDeCompraDAO.obtenerCompras();
            for (Compra orden : ordenes) {
        %>
        <tr>
            <td><%= orden.getIdCompra() %></td>
            <td><%= orden.getFechaSolicitud() %></td>
            <td><%= orden.getEstado() %></td>
            <td>
                <% if (orden.getEstado().equals("PENDIENTE")) { %>
                <button class="btn btn-sm btn-primary" onclick="editarSolicitud(<%= orden.getIdCompra() %>)">Editar</button>
                <button class="btn btn-sm btn-info" onclick="anularSolicitud(<%= orden.getIdCompra() %>)">Anular Solicitud</button>
                <% } else { %>
                <button class="btn btn-sm btn-info" onclick="verSolicitud(<%= orden.getIdCompra() %>)">Ver Solicitud</button>
                <% } %>
            </td>
        </tr>
        <%
            }
        %>
        </tbody>
    </table>


    <!-- Modal Solicitud -->
    <div class="modal fade" id="modalSolicitud" tabindex="-1">
        <div class="modal-dialog modal-lg modal-lg-custom">
            <div class="modal-content">
                <form id="formSolicitud" method="post" action="<%=request.getContextPath()%>/OrdenDeCompraServlet?accion=registrar">
                    <div class="modal-header">
                        <h5 class="modal-title">Nueva Solicitud</h5>
                        <button type="button" class="btn-close" data-bs-dismiss="modal"></button>
                    </div>
                    <div class="modal-body">
                        <div class="row g-3 mb-3">
                            <div class="col-md-6">
                                <label>Insumo<span class="required-star">*</span></label>
                                <select id="insumoInput" class="form-select">
                                    <option value="">Seleccione un insumo</option>
                                    <%
                                        InsumoDAO insumoDao = new InsumoDAO();
                                        List<Insumo> insumos = insumoDao.listarInsumos();
                                        if (insumos != null) {
                                            for (Insumo ins : insumos) {
                                    %>
                                    <option value="<%= ins.getIdInsumo() %>">
                                        <%= ins.getNombre() %>
                                    </option>
                                    <%
                                            }
                                        }
                                    %>
                                </select>
                            </div>
                            <div class="col-md-4">
                                <input type="number" id="cantidadInput" class="form-control" placeholder="Cantidad" min="1"/>
                            </div>
                            <div class="col-md-2">
                                <button class="btn btn-success w-100" type="button" onclick="agregarInsumo()">Agregar</button>
                            </div>
                        </div>

                        <table class="table table-bordered table-sm">
                            <thead class="table-light">
                            <tr>
                                <th>Insumo</th>
                                <th>Cantidad</th>
                                <th>Acción</th>
                            </tr>
                            </thead>
                            <tbody id="tablaInsumos"></tbody>
                        </table>
                    </div>
                    <div class="modal-footer">
                        <button class="btn btn-primary" type="submit">Finalizar Solicitud</button>
                    </div>
                </form>
            </div>
        </div>
    </div>

    <!-- Modal para Editar Solicitud -->
    <div class="modal fade" id="modalEditarSolicitud" tabindex="-1" aria-labelledby="modalEditarSolicitudLabel" aria-hidden="true">
        <div class="modal-dialog modal-lg">
            <div class="modal-content">
                <form id="formEditarSolicitud" method="post" action="<%=request.getContextPath()%>/OrdenDeCompraServlet?accion=editar">
                    <input type="hidden" name="idCompra" />
                    <div class="modal-header">
                        <h5 class="modal-title" id="modalEditarSolicitudLabel">Editar Solicitud de Compra</h5>
                        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                    </div>
                    <div class="modal-body">
                        <!-- Campos de la solicitud para edición -->
                        <div class="row g-3 mb-3">
                            <div class="col-md-6">
                                <label for="insumoEditarInput">Insumo<span class="required-star">*</span></label>
                                <select id="insumoEditarInput" name="insumo" class="form-select">
                                    <option value="">Seleccione un insumo</option>
                                    <%
                                        if (insumos != null) {
                                            for (Insumo ins : insumos) {
                                    %>
                                    <option value="<%= ins.getIdInsumo() %>">
                                        <%= ins.getNombre() %>
                                    </option>
                                    <%
                                            }
                                        }
                                    %>
                                </select>
                            </div>
                            <div class="col-md-4">
                                <label for="cantidadEditarInput">Cantidad<span class="required-star">*</span></label>
                                <input type="number" id="cantidadEditarInput" name="cantidad" class="form-control" placeholder="Cantidad" min="1"/>
                            </div>
                            <div class="col-md-2">
                                <button class="btn btn-success w-100" type="button" onclick="agregarInsumoEditar()">Agregar</button>
                            </div>
                        </div>

                        <!-- Tabla de insumos de la solicitud -->
                        <table class="table table-bordered table-sm">
                            <thead class="table-light">
                            <tr>
                                <th>Insumo</th>
                                <th>Cantidad</th>
                                <th>Acción</th>
                            </tr>
                            </thead>
                            <tbody id="tablaEditarInsumos"></tbody>
                        </table>
                    </div>
                    <div class="modal-footer">
                        <button type="submit" class="btn btn-primary">Guardar Cambios</button>
                    </div>
                </form>
            </div>
        </div>
    </div>

    <!-- Modal Ver Solicitud -->
    <div class="modal fade" id="modalVerSolicitud" tabindex="-1" aria-labelledby="modalVerSolicitudLabel" aria-hidden="true">
        <div class="modal-dialog modal-lg modal-dialog-scrollable">
            <div class="modal-content">
                <div class="modal-header bg-info text-white">
                    <h5 class="modal-title" id="modalVerSolicitudLabel">Detalles de la Solicitud</h5>
                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Cerrar"></button>
                </div>
                <div class="modal-body">
                    <table class="table table-bordered table-sm">
                        <thead class="table-light">
                        <tr>
                            <th>Nombre del Insumo</th>
                            <th>Cantidad</th>
                        </tr>
                        </thead>
                        <tbody id="tablaVerInsumos">
                        <!-- Se rellenará con JavaScript -->
                        </tbody>
                    </table>
                </div>
                <div class="modal-footer">
                    <button class="btn btn-secondary" data-bs-dismiss="modal">Cerrar</button>
                </div>
            </div>
        </div>
    </div>

</div>

<script>
    const BASE_URL = "<%=request.getContextPath()%>";
</script>

<script src="<%= request.getContextPath() %>/assets/js/solicitudDeCompras.js"></script>
