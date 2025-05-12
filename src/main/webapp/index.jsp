<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Inicio de Sesión | BeltCoreGC</title>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/assets/css/login.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.2/css/all.min.css">
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
<%--    <div class="login-image">
        <div class="brand-overlay">
            <img src="<%=request.getContextPath()%>/assets/img/ImagenCorreas.png" alt="Correas de cuero" class="product-image">
        </div>
    </div>--%>
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
                <input type="text" placeholder="Correo" name="correo" required>
            </div>

            <div class="input-group">
                <div class="input-icon">
                    <i class="fas fa-lock"></i>
                </div>
                <input type="password" id="passwordField" name="password" placeholder="Contraseña" required>
                <div class="password-toggle" id="togglePassword">
                    <i class="far fa-eye" id="eyeIcon"></i>
                </div>
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
</body>
</html>

