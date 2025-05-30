<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8" />
    <link rel="icon" href="https://cdn-icons-png.flaticon.com/512/2942/2942269.png" />
    <title>Restablecer Contraseña - BeltCore</title>
    <!-- Bootstrap CSS CDN -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet"/>
    <!-- Font Awesome CDN para iconos -->
    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css" rel="stylesheet"/>
    <style>
        body {
            background: linear-gradient(135deg, #0c1d63 0%, #0e58a3 100%);
            min-height: 100vh;
            display: flex;
            justify-content: center;
            align-items: center;
            padding: 20px;
        }
        .login-container {
            background: white;
            padding: 30px 40px;
            border-radius: 10px;
            max-width: 400px;
            width: 100%;
            box-shadow: 0 0 15px rgba(0,0,0,0.1);
        }
        .step-form {
            display: none;
        }
        .step-form.active {
            display: block;
        }
        .input-group > input {
            padding-left: 35px;
        }
        .login-footer {
            text-align: center;
            margin-top: 25px;
            font-size: 0.9rem;
            color: #6c757d;
        }
        .btn-custom {
            width: 100%;
        }
        .codigo-digit {
            width: 50px;
            height: 55px;
            font-size: 24px;
            border-radius: 8px;
            border: 1px solid #ced4da;
        }
        .codigo-digit:focus {
            border-color: #86b7fe;
            box-shadow: 0 0 0 0.2rem rgba(13,110,253,.25);
        }
        .btn:disabled {
            background-color: #778393 !important;
            border-color: #778393 !important;
        }

    </style>
</head>
<body>

<div class="login-container" role="main" aria-label="Formulario para restablecer contraseña">
    <div class="login-header mb-4 text-center">
        <h1 class="h4">RESTABLECER CONTRASEÑA</h1>
        <p id="form-subtitle" class="text-secondary">Paso 1: Verificación de identidad</p>
    </div>

    <!-- Paso 1 -->
    <form id="formPaso1" class="step-form active" 
>
        <div class="mb-3">
            <div class="input-group">
                <span class="input-group-text"><i class="fas fa-envelope"></i></span>
                <input type="email" class="form-control" id="correo" placeholder="Ingrese su correo" required
                       pattern="^[^\s@]+@[^\s@]+\.[^\s@]+$" title="Ingrese un correo válido, ejemplo: usuario@dominio.com"
                       aria-describedby="correoHelp"/>
            </div>
            <div class="invalid-feedback">Por favor, ingrese un correo válido.</div>
        </div>
        <div class="mb-3">
            <div class="input-group">
                <span class="input-group-text"><i class="fas fa-address-card"></i></span>
                <input type="text" class="form-control" id="dni" placeholder="Ingrese su DNI" maxlength="8"
                       minlength="8" pattern="\d{8}" inputmode="numeric" required
                       title="Ingrese un DNI válido de 8 dígitos numéricos" aria-describedby="dniHelp"/>
            </div>
            <div class="invalid-feedback">El DNI debe tener 8 dígitos numéricos.</div>
        </div>
        <button type="submit" class="btn btn-primary btn-custom" id="btnPaso1">Siguiente <i class="fas fa-arrow-right"></i></button>
    </form>

    <!-- Paso 2 -->
    <form id="formPaso2" class="step-form" 
>
        <p>Se ha enviado un código de verificación a su correo.</p>
        <div class="d-flex justify-content-center gap-2 mb-3" id="codigo-container">
            <input type="text" maxlength="1" pattern="\d" class="form-control text-center codigo-digit" required />
            <input type="text" maxlength="1" pattern="\d" class="form-control text-center codigo-digit" required />
            <input type="text" maxlength="1" pattern="\d" class="form-control text-center codigo-digit" required />
            <input type="text" maxlength="1" pattern="\d" class="form-control text-center codigo-digit" required />
            <input type="text" maxlength="1" pattern="\d" class="form-control text-center codigo-digit" required />
            <input type="text" maxlength="1" pattern="\d" class="form-control text-center codigo-digit" required />
        </div>
        <div class="invalid-feedback text-center mb-2" id="codigo-error" style="display: none;">
            El código debe contener 6 dígitos numéricos.
        </div>
        <button type="submit" class="btn btn-primary w-100" id="btnPaso2" disabled>Verificar <i class="fas fa-check"></i></button>
    </form>

    <!-- Paso 3 -->
    <form id="formPaso3" class="step-form" 
>
        <div class="mb-3">
            <div class="input-group">
                <span class="input-group-text"><i class="fas fa-lock"></i></span>
                <input type="password" class="form-control" id="nuevaContra" name="nuevaContra"
                       placeholder="Nueva contraseña"
                       required minlength="10"
                       pattern="^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)(?=.*[@$!%*?&.#+-_=])[A-Za-z\d@$!%*?&.#+-_=]{10,}$"
                       title="Mínimo 10 caracteres, con al menos una mayúscula, una minúscula, un número y un carácter especial." />
            </div>
            <div class="invalid-feedback">La contraseña debe tener al menos 10 caracteres, una mayúscula, una minúscula, un número y un carácter especial.</div>
        </div>

        <div class="mb-3">
            <div class="input-group">
                <span class="input-group-text"><i class="fas fa-lock"></i></span>
                <input type="password" class="form-control" id="confirmarContra" name="confirmarContra"
                       placeholder="Repetir contraseña"
                       required minlength="10"
                       title="Repita la misma contraseña ingresada arriba" />
            </div>
            <div class="invalid-feedback">Las contraseñas deben coincidir.</div>
        </div>

        <button type="submit" class="btn btn-success btn-custom" id="btnGuardar">
            Guardar <i class="fas fa-save"></i>
        </button>
    </form>

    <div class="login-footer mt-4">
        <p>© 2025 BeltCore. Todos los derechos reservados.</p>
    </div>
</div>

<!-- Bootstrap JS Bundle -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
<!-- SweetAlert2 -->
<script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>
<script src="assets/js/ajaxRestablecer.js"></script>

</body>
</html>