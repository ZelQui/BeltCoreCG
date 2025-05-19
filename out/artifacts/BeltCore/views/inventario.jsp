<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Gestión de Inventario</title>
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.2/css/all.min.css">
  <link rel="stylesheet" href="<%=request.getContextPath()%>/assets/css/stock.css">
  <script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>
</head>

  <div class="inventario-container">
    <h1>Inventario de Productos</h1>

    <!-- Filtro por nombre -->
    <div class="filter-container">
      <input type="text" id="filtro-nombre" placeholder="Buscar por nombre...">
      <!-- Filtro por categoría -->
      <select id="filtro-categoria">
        <option value="">Selecciona una categoría</option>
        <option value="Cinturones">Cinturones</option>
        <option value="Carteras">Carteras</option>
        <!-- Agrega más categorías según sea necesario -->
      </select>
    </div>

    <!-- Tabla de Inventario -->
    <div class="table-container">
      <table class="inventory-table">
        <thead>
          <tr>
            <th>ID</th>
            <th>Imagen</th>
            <th>Nombre</th>
            <th>Descripción</th>
            <th>Precio Venta</th>
            <th>Stock</th>
            <th>Categoría</th>
          </tr>
        </thead>
        <tbody id="tabla-inventario">
          <!-- Se llena con JS -->
        </tbody>
      </table>
    </div>
  </div>

  <script>
    const imgPatch = "<%=request.getContextPath()%>";
  </script>
  <script src="<%=request.getContextPath()%>/assets/js/stock.js"></script>
