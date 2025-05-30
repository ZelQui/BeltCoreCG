<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Inicio de Sesión | BeltCoreGC</title>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/assets/css/login.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.2/css/all.min.css">
    <link rel="icon" href="<%=request.getContextPath()%>/assets/img/favicon.ico">
    <script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>
</head>
<body>
<div class="circles">
    <li></li>
    <li></li>
    <li></li>
    <li></li>
    <li></li>
    <li></li>
    <li></li>
    <li></li>
    <li></li>
    <li></li>
</div>

<div class="login-container">
    <div class="login-form">
        <div class="login-header">
            <div class="logo-container">
                <img src="<%=request.getContextPath()%>/assets/img/LogoEmpresa.png" alt="Logo Empresa" class="logo">
            </div>
            <h1>BIENVENIDO</h1>
            <p>Ingresa tus credenciales para acceder</p>
        </div>

        <form method="post" action="login">
            <div class="input-group">
                <div class="input-icon">
                    <i class="fas fa-user"></i>
                </div>
                <input type="text" placeholder="Ingrese su usuario" name="userLogin" required>
            </div>

            <div class="input-group">
                <div class="input-icon">
                    <i class="fas fa-lock"></i>
                </div>
                <input type="password" id="passwordField" name="contrasena" placeholder="Ingrese su contraseña" required>
                <div class="password-toggle" id="togglePassword">
                    <i class="far fa-eye" id="eyeIcon"></i>
                </div>
            </div>

            <div class="link-container">
                <a href="resetpass.jsp" class="link-reset">¿Olvidaste tu contraseña?</a>
            </div>

            <button  type="submit" class="login-button" >
                Iniciar Sesión
                <div class="button-icon">
                    <i class="fas fa-arrow-right"></i>
                </div>
            </button>
        </form>

        <div class="login-footer">
            <p>&copy; 2025 BeltCoreCG. Todos los derechos reservados.</p>
        </div>
    </div>
</div>

<script src="<%=request.getContextPath()%>/assets/js/login.js"></script>

<script>
    window.history.replaceState(null, "", "<%=request.getContextPath()%>/");
</script>

<%-- Solo se ejecuta si existe atributos de Error --%>
<%
    String icon = (String) request.getAttribute("alertIcon");
    String title = (String) request.getAttribute("alertTitle");
    String message = (String) request.getAttribute("alertMessage");

    if (icon != null && title != null && message != null) {
%>
<script>
    Swal.fire({
        icon: '<%= icon %>',
        title: '<%= title.replace("'", "\\'") %>',
        text: '<%= message.replace("'", "\\'") %>'
    });
</script>
<% } %>

</body>
</html>

