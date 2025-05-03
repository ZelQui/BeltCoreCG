<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="eS">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Iniciar Sesion | BeltCoreCG</title>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/assets/css/login.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
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
    <div class="login-header">
        <h1>BIENVENIDO</h1>
        <p>Por favor, inicie sesión en su cuenta</p>
    </div>

    <form id="loginForm" action="login" method="post">
        <div class="input-group">
            <input type="email" id="email" name="correo" placeholder="correo" required>
        </div>

        <div class="input-group">
            <input type="password" id="passwordField" placeholder="Password" name="password">
            <i class="fas fa-eye" id="togglePassword"></i>
        </div>


        <button type="submit" class="login-button">Iniciar Sesion</button>

        <div class="signup-link">
            Te olvidaste tu contraseña? <a href="#">Aqui</a>
        </div>

        <div class="social-login">
            <div class="social-icon facebook">
                <i class="fab fa-facebook-f"></i>
            </div>
            <div class="social-icon google">
                <i class="fab fa-google"></i>
            </div>
            <div class="social-icon twitter">
                <i class="fab fa-twitter"></i>
            </div>
        </div>
    </form>
</div>

<script src="<%=request.getContextPath()%>/assets/js/login.js"></script>
</body>
</html>