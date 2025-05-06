<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="es">
<head>
  <meta charset="UTF-8" />
  <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
  <title>Configuración</title>
  <link rel="stylesheet" href="<%=request.getContextPath()%>/assets/css/configuracion.css" />
</head>
<body>
  <div class="config-container">
    <div class="card-grid">
      <div class="config-card" onclick="cargarPagina('usuarios.jsp')">
        <i class="fas fa-users-gear"></i>
        <p>Usuarios</p>
      </div>
      <div class="config-card" onclick="cargarPagina('productos.jsp')">
        <i class="fas fa-boxes"></i>
        <p>Productos / Categorías</p>
      </div>
      <div class="config-card" onclick="location.href='insumos.jsp'">
        <i class="fas fa-cubes"></i>
        <p>Insumos</p>
      </div>
      <div class="config-card" onclick="location.href='producciones.jsp'">
        <i class="fas fa-industry"></i>
        <p>Producciones</p>
      </div>
      <div class="config-card" onclick="location.href='recetas.jsp'">
        <i class="fas fa-utensils"></i>
        <p>Recetas</p>
      </div>
      <div class="config-card" onclick="location.href='creditos.jsp'">
        <i class="fas fa-credit-card"></i>
        <p>Máximo de Créditos</p>
      </div>
      <div class="config-card" onclick="location.href='mermas.jsp'">
        <i class="fas fa-trash-alt"></i>
        <p>Control de Mermas</p>
      </div>
      <div class="config-card" onclick="location.href='defectuosos.jsp'">
        <i class="fas fa-exclamation-triangle"></i>
        <p>Productos Defectuosos</p>
      </div>
      <div class="config-card" onclick="location.href='metodos_pago.jsp'">
        <i class="fas fa-money-bill-wave"></i>
        <p>Métodos de Pago</p>
      </div>
      <div class="config-card" onclick="location.href='roles.jsp'">
        <i class="fas fa-id-badge"></i>
        <p>Roles de Empleados</p>
      </div>
    </div>
  </div>
</body>
</html>
