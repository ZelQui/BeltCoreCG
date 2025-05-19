<%@ page import="org.mindrot.jbcrypt.BCrypt" %>
<%@ page import="development.team.Models.Usuario" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<head>
    <script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>
</head>
<div class="error-page">
    <h2>Pagina de Inicio</h2>
    <p>Bienvenido!</p>
</div>

<%
    HttpSession sessionObj = request.getSession(false);
    if (sessionObj == null || sessionObj.getAttribute("usuario") == null) {
        response.sendRedirect("../");
        return;
    }

    Usuario usuario = (Usuario) sessionObj.getAttribute("usuario");
    boolean mostrarModal = BCrypt.checkpw(usuario.getDni(), usuario.getContrasena());

    if (mostrarModal) { %>
    <script>
        Swal.fire({
            title: 'Advertencia',
            text: 'Tu contraseña actual es la predeterminada. Por seguridad, se recomienda cambiarla ahora.',
            icon: 'warning',
            showCancelButton: true,
            confirmButtonText: 'Ir ahora',
            cancelButtonText: 'Después',
            allowOutsideClick: false,
            allowEscapeKey: false
        }).then((result) => {
            if (result.isConfirmed) {
                // Redirige a la página de cambio de contraseña
                window.location.href = '<%=request.getContextPath()%>/app/cambiar_contrasena';
            }
            // Si elige "Después", simplemente no hace nada
        });
    </script>
<%  } %>