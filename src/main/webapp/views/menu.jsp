<%@ page import="development.team.Models.Modulo" %>
<%@ page import="java.util.List" %>
<%@ page import="org.mindrot.jbcrypt.BCrypt" %>
<%@ page import="development.team.Models.Usuario" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>BeltCoreGC</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.2/css/all.min.css"/>
    <link rel="stylesheet" href="<%= request.getContextPath() %>/assets/css/menu.css" />
</head>
<%
    HttpSession sessionObj = request.getSession(false);
    if (sessionObj == null || sessionObj.getAttribute("usuario") == null) {
        response.sendRedirect("../"); //Mensaje: Inicia sesión primero
        return;
    }

    String contenidoAttr = (String) request.getAttribute("contenido");
    if (contenidoAttr == null) {
        contenidoAttr = "inicio.jsp";
    }
/*
    Usuario usuario = (Usuario) sessionObj.getAttribute("usuario");
    boolean mostrarModal = BCrypt.checkpw("123456", usuario.getContrasena());*/
%>
<body>
<!-- Sidebar -->
<div class="sidebar">
    <div class="logo">
        <img src="<%= request.getContextPath() %>/assets/img/LogoEmpresa.png" alt="Logo Empresa" style="width: 150px; height: auto;" />
    </div>

    <div class="nav-menu">
        <%-- Lista de módulos: modulos_usuario --%>
        <%
            List<Modulo> modulos = (List<Modulo>) session.getAttribute("modulos_usuario");
            if (modulos != null) {
                for (Modulo m : modulos) {
                    // Mostrar solo los módulos principales (cuando moduloPadreId es null)
                    if (m.getModuloPadreId() == null) {
        %>
        <a href="#" class="nav-item" onclick="cargarContenido('<%= m.getRuta() %>')">
            <i class="<%= m.getIcono() %>"></i>
            <span><%= m.getNombre() %></span>
        </a>
        <%
                    }
                }
            }
        %>
        <a href="<%= request.getContextPath() %>" class="nav-item logout">
            <i class="fas fa-sign-out-alt"></i>
            <span>Cerrar Sesión</span>
        </a>
    </div>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-ENjdO4Dr2bkBIFxQpeoTz1HIcje39Wm4jDKdf19U8gI4ddQ3GYNS7NTKfAdVQSZe"
        crossorigin="anonymous"></script>

<!-- Modal para cambiar contraseña -->
<%--<div class="modal" id="modalCambiarPassword" tabindex="-1" aria-labelledby="modalLabel" aria-hidden="true">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title">Cambiar Contraseña</h5>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Cerrar"></button>
            </div>
            <div class="modal-body">
                <p>Tu contraseña actual es la predeterminada. Por seguridad, cámbiala ahora.</p>
                <form id="formCambiarPassword" >
                    <input type="hidden" name="accion" value="updatePassword">
                    <div class="mb-3">
                        <label for="nuevaPassword">Nueva Contraseña</label>
                        <input type="password" class="form-control" name="newPassword" id="nuevaPassword" required>
                    </div>
                    <div class="mb-3">
                        <label for="confirmarPassword">Confirmar Contraseña</label>
                        <input type="password" class="form-control" id="confirmarPassword" required>
                    </div>
                    <div id="errorMensaje" class="alert alert-danger mt-3" role="alert" style="display: none;">
                        Las contraseñas no coinciden.
                    </div>
                    <button type="button" class="btn secondary" >Cancelar</button>
                    <button type="submit" class="btn primary">Actualizar Contraseña</button>
                </form>
            </div>
        </div>
    </div>
</div>--%>
<!-- contenedor principal -->
<div class="main-content" id="contenido">
    <jsp:include page="<%= contenidoAttr %>" />
</div>

<script>
    // Obtener el contexto dinámicamente (ej: /BeltCoreCG_war_exploded)
    const contextPath = window.location.pathname.split('/')[1];
    const baseApp = '/' + contextPath + '/app/';

    // Función modificada para redirigir al usuario a la página completa
    function cargarContenido(pagina) {
        //console.log("Redirigiendo a: " + baseApp + pagina);
        // Redirige a la URL completa para que la página se recargue
        window.location.href = baseApp + pagina;
    }

</script>

</body>
</html>