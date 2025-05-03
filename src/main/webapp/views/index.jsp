<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Inicio de sesión</title>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/assets/css/login.css">
    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css" rel="stylesheet">
</head>
<body>
<div class="container">
    <div class="login-container">
        <div class="image-container">
            <img src="<%=request.getContextPath()%>/assets/img/ImagenCorreas.png" alt="Correas de cuero">
        </div>
        <form method="post" action="login" class="form-container">
            <div class="login-form">
                <h1>Inicio de sesión</h1>

                <div class="input-group">
                    <div class="icon-container">
                        <i class="fas fa-user"></i>
                    </div>
                    <input type="text" placeholder="Correo" name="correo">
                </div>

                <div class="input-group">
                    <div class="icon-container">
                        <i class="fas fa-lock"></i>
                    </div>
                    <input type="password" id="passwordField" placeholder="Password" name="password">
                    <div class="eye-icon" id="togglePassword">
                        <i class="fas fa-eye" id="eyeIcon"></i>
                    </div>
                </div>

                <button class="login-button" type="submit">Iniciar Sesión</button>

            </div>
        </form>
    </div>
</div>

<script src="<%=request.getContextPath()%>/assets/js/login.js"></script>
</body>
</html>