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

<div id="modal-producto" class="modal oculto">
  <div class="modal-contenido">
    <form id="form-producto" class="formulario">
      <div class="modal-header">
        <h2>Nuevo Producto</h2>
        <span class="cerrar-modal">&times;</span>
      </div>

      <!-- Contenedor de dos columnas -->
      <div class="formulario-doble-columna">
        <!-- Columna izquierda -->
        <div class="formulario-izquierda">
          <label for="foto">Foto:</label>
          <input type="file" id="foto" accept="image/*" />

          <label for="nombre">Nombre del producto:</label>
          <input type="text" id="nombre" placeholder="Ingrese nombre" required />

          <label for="descripcion">Descripción:</label>
          <textarea id="descripcion" placeholder="Agregue una descripción..." rows="3"></textarea>

          <label for="precio">Precio:</label>
          <input type="number" id="precio" step="0.01" placeholder="S/ 0.00" required />

          <label for="stock">Stock:</label>
          <input type="number" id="stock" placeholder="Cantidad" required />

          <label for="categoria">Categoría:</label>
          <select id="categoria" required>
            <option value="">Seleccione una categoría</option>
            <!-- Agrega aquí las opciones dinámicamente -->
          </select>
        </div>

        <!-- Columna derecha -->
        <div class="formulario-derecha">

          <label for="insumo">Insumo:</label>
          <select id="insumo" required>
            <option value="">Seleccione insumo</option>
            <option value="1">Correa</option>
            <!-- Agrega aquí las opciones dinámicamente -->
          </select>

          <label for="cantidad">Cantidad:</label>
          <input type="number" id="cantidad" placeholder="Ej. 50" required min="0" step="0.01" />

          <label for="unidad">Unidad de medida:</label>
          <select id="unidad" required>
            <option value="">Seleccione unidad</option>
            <option value="cm">cm</option>
            <option value="m">metros</option>
            <option value="kg">kg</option>
            <option value="l">litros</option>
            <option value="unidad">unidad</option>
          </select>

          <button type="button" id="btn-agregar-insumo">Agregar</button>

          <!-- Tabla de insumos agregados -->
          <table id="tabla-insumos" class="tabla-insumos" style="margin-top: 15px; width: 100%; border-collapse: collapse; display: none;">
            <thead>
            <tr>
              <th>Insumo</th>
              <th>Cantidad</th>
            </tr>
            </thead>
            <tbody>
            <!-- Filas se agregarán aquí -->
            </tbody>
          </table>
          <!-- Botones -->
          <div class="botones">
            <button type="button" class="cancelar">Cancelar</button>
            <button type="submit" class="guardar">Guardar</button>
          </div>
        </div>
      </div>
    </form>
  </div>
</div>

<script src="<%= request.getContextPath() %>/assets/js/productos.js"></script>
