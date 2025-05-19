<%@ page import="development.team.Models.Modulo" %>
<%@ page import="java.util.List" %>
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
      <%
        // Recuperamos de sesión la lista completa de módulos permitidos
        @SuppressWarnings("unchecked") //se usa para indicarle al compilador de Java que ignore (suprima) la advertencia
        List<Modulo> modulos = (List<Modulo>) session.getAttribute("modulos_usuario");
        // ID del módulo padre “Configuración”
        final int CONFIG_ID = 9;

        if (modulos != null) {
          for (Modulo m : modulos) {
            Integer padre = m.getModuloPadreId();
            // Mostramos solo los submódulos cuyo padre sea CONFIG_ID
            if (padre != null && padre == CONFIG_ID) {
      %>
      <div class="config-card" onclick="cargarContenido('<%= m.getRuta() %>')">
        <i class="<%= m.getIcono() %>"></i>
        <p><%= m.getNombre() %></p>
      </div>
      <%
          }
        }
      } else {
      %>
      <p>No hay módulos de configuración disponibles para tu rol.</p>
      <%
        }
      %>
    </div>
  </div>
</body>
</html>
