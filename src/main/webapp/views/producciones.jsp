<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<head>
  <meta charset="UTF-8" />
  <meta name="viewport" content="width=device-width, initial-scale=1.0" />
  <title>Gestión de Producción</title>
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.2/css/all.min.css" />
  <link rel="stylesheet" href="<%=request.getContextPath()%>/assets/css/produccion.css" />
  <script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>
</head>

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
      <!-- Las opciones de usuarios se agregarán dinámicamente -->
    </select>
    <!-- Nuevo filtro por estado -->
    <select id="statusFilter">
      <option value="">Todos los estados</option>
      <option value="en proceso">En proceso</option>
      <option value="finalizado">Finalizado</option>
      <option value="anulado">Anulado</option>
    </select>
  </div>
  <!-- Tabla de Producciones -->
  <div class="table-container">
    <table class="user-table">
      <thead>
      <tr>
        <th>ID</th>
        <th>Producto</th>
        <th>Fecha</th>
        <th>Cantidad</th>
        <th>Usuario</th>
        <th>Estado</th>
        <th>Acciones</th>
      </tr>
      </thead>
      <tbody id="tabla-producciones">
      <!-- Se llena con JS -->
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
      <form id="form-generar" class="formulario">
        <!-- Contenedor de dos columnas -->
        <div class="formulario-doble-columna">
          <!-- Columna izquierda -->
          <div class="formulario-izquierda">
            <label for="producto-select">Producto:</label>
            <select id="producto-select" required>
              <option value="">Seleccione un producto</option>
              <option value="Correa negra">Correa negra</option>
              <option value="Correa marrón">Correa marrón</option>
            </select>

            <label for="cantidad-producir">Cantidad a producir:</label>
            <input type="number" id="cantidad-producir" required min="1" />

            <!-- <label for="cantidad-producir">Alerta de insumo insuficiente:</label> -->
            <div id="mensaje-insumos" style="margin-top: 20px;"></div>
          </div>

          <!-- Columna derecha -->
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
        <!-- Botones -->
        <div class="botones">
          <button type="button" class="cancelar" id="btn-cancelar-generar">Cancelar</button>
          <button type="submit">Generar</button>
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
        <input type="hidden" id="editar-id" />

        <label>Producto:</label>
        <select id="editar-producto" required>
          <option value="">Seleccione un producto</option>
          <option value="Correa negra">Correa negra</option>
          <option value="Correa marrón">Correa marrón</option>
        </select>

        <label>Cantidad:</label>
        <input type="number" id="editar-cantidad" required min="1" />

        <div class="botones">
          <button type="button" class="cancelar">Cancelar</button>
          <button type="submit">Guardar Cambios</button>
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
            <label for="cantidadEstimado">Cantidad Estimada:</label>
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
  <div class="modal-contenido">
    <div class="modal-header">
      <h2>Detalle de Producción</h2>
      <span class="cerrar-modal" id="cerrarVer">&times;</span>
    </div>
    <div class="modal-body formulario">
      <!-- Producto -->
      <div class="form-group">
        <label>Producto:</label>
        <input type="text" id="verProducto" readonly />
      </div>

      <!-- Insumos -->
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
        <label>Total Insumos Previstos:</label>
        <input type="number" id="verTotalPrevistos" readonly />
      </div>
      <div class="form-group">
        <label>Total Insumos Reales:</label>
        <input type="number" id="verTotalReales" readonly />
      </div>
      <div class="form-group">
        <label>Merma Total:</label>
        <input type="number" id="verMermaTotal" readonly />
      </div>

      <!-- Producción -->
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

      <div class="botones">
        <button class="cancelar" id="btnCerrarVer">Cerrar</button>
      </div>
    </div>
  </div>
</div>
<script src="<%=request.getContextPath()%>/assets/js/produccion.js"></script>