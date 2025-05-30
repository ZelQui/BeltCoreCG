// Función para mostrar paso activo
function mostrarPaso(paso) {
    document.querySelectorAll('.step-form').forEach(form => form.classList.remove('active'));
    document.getElementById('formPaso' + paso).classList.add('active');

    const subtitulos = {
        1: "Paso 1: Verificación de identidad",
        2: "Paso 2: Código de verificación",
        3: "Paso 3: Nueva contraseña"
    };
    document.getElementById('form-subtitle').innerText = subtitulos[paso];
}

// Validar formulario paso 1
document.getElementById('formPaso1').addEventListener('submit', function (e) {
    e.preventDefault();

    if (!this.checkValidity()) {
        this.classList.add('was-validated');
        return;
    }

    const correo = document.getElementById('correo').value.trim();
    const dni = document.getElementById('dni').value.trim();

    Swal.fire({title: "Validando...", didOpen: () => Swal.showLoading()});

    fetch('RestablecerContraController', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/x-www-form-urlencoded'
        },
        body: new URLSearchParams({
            accion: 'verificarUsuario',
            correo: correo,
            dni: dni
        })
    })
        .then(response => response.text())
        .then(data => {
            Swal.close();
            if (data === 'OK') {
                mostrarPaso(2);
            } else {
                Swal.fire("No encontrado", "No se encontró una cuenta con los datos ingresados.", "error");
            }
        })
        .catch(error => {
            Swal.close();
            Swal.fire("Error", "Hubo un problema al validar los datos. Intente nuevamente.", "error");
            console.error('Error en la petición:', error);
        });
});

// Código para manejo de ingreso OTP
const digits = document.querySelectorAll(".codigo-digit");
const btnPaso2 = document.getElementById("btnPaso2");
const errorCodigo = document.getElementById("codigo-error");

digits.forEach((input, index) => {
    input.addEventListener("input", (e) => {
        let value = input.value.replace(/\D/g, ""); // Solo números
        input.value = value;

        // Avanza al siguiente campo
        if (value !== "" && index < digits.length - 1) {
            digits[index + 1].focus();
        }

        validarCodigo();
    });

    input.addEventListener("keydown", (e) => {
        if (e.key === "Backspace" && input.value === "" && index > 0) {
            digits[index - 1].focus();
        }
    });
});

function validarCodigo() {
    const completo = [...digits].every(d => d.value.trim().match(/^\d$/));
    btnPaso2.disabled = !completo;
    errorCodigo.style.display = completo ? "none" : "block";
}

// Validar formulario paso 2
document.getElementById("formPaso2").addEventListener("submit", function (e) {
    e.preventDefault();

    const digits = document.querySelectorAll("#codigo-container .codigo-digit"); // Agregado
    const errorCodigo = document.getElementById("codigo-error"); // Asegura obtenerlo también

    const codigo = [...digits].map(d => d.value.trim()).join("");
    if (codigo.length !== 6 || !/^\d{6}$/.test(codigo)) {
        errorCodigo.style.display = "block";
        return;
    }

    errorCodigo.style.display = "none"; // Oculta si está bien

    Swal.fire({title: "Verificando...", didOpen: () => Swal.showLoading()});

    fetch("RestablecerContraController", {
        method: "POST",
        headers: {"Content-Type": "application/x-www-form-urlencoded"},
        body: "accion=verificarCodigo&codigo=" + encodeURIComponent(codigo)
    })
        .then(res => res.text())
        .then(response => {
            Swal.close();
            if (response === "CODIGO_CORRECTO") {
                mostrarPaso(3); // Avanza al paso 3
            } else {
                Swal.fire("Error", "Código incorrecto.", "error");
            }
        })
        .catch(err => {
            Swal.close();
            Swal.fire("Error", "Ocurrió un error al verificar el código.", "error");
        });
});

// Validar formulario paso 3
document.getElementById("formPaso3").addEventListener("submit", function (e) {
    e.preventDefault();

    const pass1 = document.getElementById("nuevaContra").value.trim();
    const pass2 = document.getElementById("confirmarContra").value.trim();

    if (pass1.length < 6) {
        Swal.fire("Error", "La contraseña debe tener al menos 6 caracteres.", "error");
        return;
    }

    if (pass1 !== pass2) {
        Swal.fire("Error", "Las contraseñas no coinciden.", "error");
        return;
    }

    Swal.fire({ title: "Actualizando...", didOpen: () => Swal.showLoading() });

    fetch("RestablecerContraController", {
        method: "POST",
        headers: { "Content-Type": "application/x-www-form-urlencoded" },
        body: "accion=actualizarPassword&nuevaContra=" + encodeURIComponent(pass1)
    })
        .then(res => res.text())
        .then(response => {
            Swal.close();
            if (response === "actualizado") {
                Swal.fire("Éxito", "Contraseña actualizada correctamente.", "success").then(() => {
                    location.href = "index.jsp"; // o donde desees redirigir
                });
            } else {
                Swal.fire("Error", "No se pudo actualizar la contraseña.", "error");
            }
        })
        .catch(err => {
            Swal.close();
            Swal.fire("Error", "Hubo un problema en el servidor.", "error");
        });
});