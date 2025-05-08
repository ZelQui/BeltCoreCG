<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<head>
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.2/css/all.min.css"/>
  <link rel="stylesheet" href="<%= request.getContextPath() %>/assets/css/productos.css" />
</head>

<div class="productos-container">
  <h1>Gestión de Productos</h1>

  <div class="acciones">
    <button id="btnProducto"><i class="fas fa-plus"></i> Agregar Producto</button>
  </div>

  <div class="filter-container">
    <div class="search-container">
      <input type="text" class="search-provider" placeholder="Buscar producto...">
    </div>

    <select id="roleFilter">
      <option value="">Todos las categorias</option>
      <option value="correa">Correas 1</option>
      <option value="correa">Correas 2</option>

    </select>

    <select id="statusFilter">
      <option value="">Todos los estados</option>
      <option value="En almacén">En almacén</option>
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
        <th>Foto</th>
        <th>Nombre</th>
        <th>Categoría</th>
        <th>Precio</th>
        <th>Estado</th>
        <th>Acciones</th>
      </tr>
      </thead>
      <tbody>
      <tr>
        <td>1</td>
        <td><img src="../assets/img/correa.png" alt="Producto 1" class="product-img" /></td>
        <td>CORREA CIVVAL3325001NT-MR</td>
        <td>Correa</td>
        <td>S/ 139.90</td>
        <td><span class="role-badge role-green">En almacén</span></td>
        <td>
          <button class="btn view" title="Ver"><i class="fas fa-eye"></i></button>
          <button class="btn edit" title="Editar"><i class="fas fa-edit"></i></button>
          <button class="btn deactivate" title="Desactivar"><i class="fas fa-ban"></i></button>
        </td>
      </tr>
      <tr>
        <td>2</td>
        <td><img src="../assets/img/correa1.png" alt="Producto 2" class="product-img" /></td>
        <td>Correa Reversible De Vestir Negro/Marron 5AAC006</td>
        <td>Correa</td>
        <td>S/ 189.90</td>
        <td><span class="role-badge role-red">Agotado</span></td>
        <td>
          <button class="btn view" title="Ver"><i class="fas fa-eye"></i></button>
          <button class="btn edit" title="Editar"><i class="fas fa-edit"></i></button>
          <button class="btn activate" title="Activar"><i class="fas fa-check-circle"></i></button>
        </td>
      </tr>
      </tbody>
    </table>
  </div>
</div>

<!-- Modal Producto -->
<div id="modal-producto" class="modal oculto">
  <div class="modal-contenido">
    <div class="modal-header">
      <h2>Nuevo Producto</h2>
      <span class="cerrar-modal">&times;</span>
    </div>
    <form id="form-producto" class="formulario">
      <label>Foto:</label>
      <input type="file" accept="image/*" />

      <label>Nombre del producto:</label>
      <input type="text" placeholder="Ingrese nombre" />

      <label>Categoría:</label>
      <select>
        <option value="">Seleccione una categoría</option>
      </select>

      <label>Precio:</label>
      <input type="number" step="0.01" placeholder="S/ 0.00" />

      <div class="botones">
        <button type="button" class="cancelar">Cancelar</button>
        <button type="submit">Agregar</button>
      </div>
    </form>
  </div>
</div>

<script src="<%= request.getContextPath() %>/assets/js/productos.js"></script>
