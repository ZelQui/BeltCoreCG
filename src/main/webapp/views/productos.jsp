<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="es">
<head>
  <meta charset="UTF-8" />
  <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
  <title>Productos</title>
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.2/css/all.min.css"/>
  <link rel="stylesheet" href="../assets/css/productos.css" />
</head>
<body>
  <div class="productos-container">
    <h1>Gestión de Productos</h1>

    <div class="acciones">
      <button id="btnProducto"><i class="fas fa-plus"></i> Agregar Producto</button>
      <button id="btnCategoria"><i class="fas fa-tags"></i> Agregar Categoría</button>
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

    <!-- Modal Categoría -->
    <div id="modal-categoria" class="modal oculto">
      <div class="modal-contenido">
        <div class="modal-header">
          <h2>Nueva Categoría</h2>
          <span class="cerrar-modal">&times;</span>
        </div>
        <form id="form-categoria" class="formulario">
          <label>Nombre de la categoría:</label>
          <input type="text" placeholder="Ingrese nombre" />

          <label>Descripción (opcional):</label>
          <textarea placeholder="Ingrese una descripción"></textarea>

          <div class="botones">
            <button type="button" class="cancelar">Cancelar</button>
            <button type="submit">Agregar</button>
          </div>
        </form>
      </div>
    </div>
  </div>

  <script src="../assets/js/productos.js"></script>
  <script>
    // Ejecutar inicialización inmediata si esta página se carga dentro de otro contenedor
    if (typeof window.inicializarProductos === 'function') {
      window.inicializarProductos();
    }
  </script>
</body>
</html>