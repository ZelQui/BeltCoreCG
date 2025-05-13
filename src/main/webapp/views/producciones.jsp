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
          <label>Producto:</label>
          <select id="producto-select" required>
            <option value="">Seleccione un producto</option>
            <option value="Correa negra">Correa negra</option>
            <option value="Correa marrón">Correa marrón</option>
          </select>

          <label>Cantidad a producir:</label>
          <input type="number" id="cantidad-producir" required min="1" />

          <div class="botones">
            <button type="button" class="cancelar">Cancelar</button>
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

  </div>

  <script src="<%=request.getContextPath()%>/assets/js/produccion.js"></script>