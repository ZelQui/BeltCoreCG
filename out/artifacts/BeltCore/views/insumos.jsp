<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<head>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.2/css/all.min.css"/>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/assets/css/insumos.css" />
    <script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>
</head>

<div class="productos-container">
  <h1>Gestión de Insumos</h1>

  <div class="acciones">
    <button id="btnProducto"><i class="fas fa-plus"></i> Agregar Insumo</button>
  </div>

  <div class="filter-container">
    <div class="search-container">
      <input type="text" class="search-provider" placeholder="Buscar insumo...">
    </div>

    <select id="roleFilter">
      <option value="">Filtrar por unidad</option>
      <option value="m²">m²</option>
      <option value="kg">kg</option>
      <option value="litros">litros</option>
      <option value="m">m</option>
    </select>

    <select id="statusFilter">
      <option value="">Todos los estados</option>
      <option value="Disponible">Disponible</option>
      <option value="Agotado">Agotado</option>
    </select>

    <button class="btn primary" id="applyFilters">Buscar</button>
  </div>

  <!-- Tabla de productos -->
  <div class="table-container">
    <table class="user-table">
      <thead>
        <tr>
          <th>ID</th>
          <th>Nombre</th>
          <th>Descripción</th>
          <th>Stock</th>
          <th>Precio por unidad</th>
          <th>Unidad de medida</th>
          <th>Estado</th>
          <th>Acciones</th>
        </tr>
      </thead>
      <tbody>
        <tr>
          <td>1</td>
          <td>Cuero negro</td>
          <td>Descripción</td>
          <td>120</td>
          <td>S/ 15</td>
          <td>m²</td>
          <td><span class="role-badge role-green">Disponible</span></td>
          <td>
            <button class="btn view" title="Ver"><i class="fas fa-eye"></i></button>
            <button class="btn edit" title="Editar"><i class="fas fa-edit"></i></button>
            <button class="btn deactivate" title="Desactivar"><i class="fas fa-ban"></i></button>
          </td>
        </tr>
        <!-- Más productos pueden ir aquí -->
      </tbody>
    </table>
  </div>

  <!-- Modal Agregar Insumo -->
  <div id="modal-producto" class="modal oculto">
    <div class="modal-contenido">
      <div class="modal-header">
        <h2>Nuevo Insumo</h2>
        <span class="cerrar-modal">&times;</span>
      </div>
      <form id="form-producto" class="formulario">
        <label>Nombre:</label>
        <input type="text" id="nuevo-nombre" required />

        <label>Descripción:</label>
        <textarea id="nuevo-descripcion" required></textarea>

        <label>Precio por unidad:</label>
        <input type="number" id="nuevo-precio" step="0.01" required />

        <label>Unidad de medida:</label>
        <select id="nuevo-unidad" required>
          <option value="">Seleccionar unidad</option>
          <option value="m²">m²</option>
          <option value="kg">kg</option>
          <option value="litros">litros</option>
          <option value="m">m</option>
        </select>

        <div class="botones">
          <button type="button" class="cancelar">Cancelar</button>
          <button type="submit">Agregar</button>
        </div>
      </form>
    </div>
  </div>

  <!-- Modal Editar Insumo -->
  <div id="modal-editar-producto" class="modal oculto">
    <div class="modal-contenido">
      <div class="modal-header">
        <h2>Editar Insumo</h2>
        <span class="cerrar-modal">&times;</span>
      </div>
      <form id="form-editar-producto" class="formulario">
        <label>Nombre:</label>
        <input type="text" id="editar-nombre" required />

        <label>Descripción:</label>
        <textarea id="editar-descripcion" required></textarea>

        <label>Precio por unidad:</label>
        <input type="number" id="editar-precio" step="0.01" required />

        <label>Unidad de medida:</label>
        <select id="editar-unidad" required>
          <option value="">Seleccionar unidad</option>
          <option value="m²">m²</option>
          <option value="kg">kg</option>
          <option value="litros">litros</option>
          <option value="m">m</option>
        </select>

        <div class="botones">
          <button type="button" class="cancelar">Cancelar</button>
          <button type="submit">Guardar Cambios</button>
        </div>
      </form>
    </div>
  </div>

  <script src="<%=request.getContextPath()%>/assets/js/insumos.js"></script>
</div>
