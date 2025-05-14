//BUSCAR API DNI
document.getElementById('buscarDniBtn').addEventListener('click', function () {
    const dni = document.getElementById('dni').value;

    if (dni.length !== 8 || isNaN(dni)) {
        alert("Ingrese un DNI válido de 8 dígitos.");
        return;
    }

    fetch(BASE_URL + "/consultarDni?dni=" + dni)
        .then(response => {
            if (!response.ok) {
                throw new Error('Error al consultar el DNI');
            }
            return response.json();
        })
        .then(data => {
            console.log('Resultado de la API:', data); // Solo consola por ahora

            // Opcional: rellenar campo nombre completo
            var fullName = data.nombres;
            var ApePaterno = data.apellidoPaterno;
            var ApeMaterno = data.apellidoMaterno;
            document.getElementById('fullName').value = fullName;
            document.getElementById('ApePaterno').value = ApePaterno;
            document.getElementById('ApeMaterno').value = ApeMaterno;
            // Hacer los campos solo lectura
            document.getElementById('fullName').readOnly = true;
            document.getElementById('ApePaterno').readOnly = true;
            document.getElementById('ApeMaterno').readOnly = true;
        })
        .catch(error => {
            console.error('Error al consultar DNI:', error);
            alert('No se pudo obtener los datos del DNI.');
        });
});

document.addEventListener("DOMContentLoaded", () => {

    // Función para validar solo letras y convertir a mayúsculas
    function soloLetras(input) {
        input.addEventListener('input', function () {
            const valor = this.value.normalize("NFD").replace(/[^A-Za-zÁÉÍÓÚáéíóúÑñ\s]/g, '');
            this.value = valor.toUpperCase();
            validarFormulario();
        });
    }

    // Función para validar teléfono
    function soloNumerosYValidarTelefono(input) {
        input.addEventListener('input', function () {
            this.value = this.value.replace(/\D/g, '').slice(0, 9);

            const telefonoValido = /^9\d{8}$/;
            const errorSpan = document.getElementById('telefonoError');

            if (this.value.length === 9 && !telefonoValido.test(this.value)) {
                errorSpan.textContent = "El teléfono debe empezar con 9 y tener 9 dígitos.";
            } else {
                errorSpan.textContent = "";
            }

            validarFormulario();
        });
    }

    // Función para validar el DNI
    const dniInput = document.getElementById('dni');
    const buscarBtn = document.getElementById('buscarDniBtn');

    dniInput.addEventListener('input', function () {
        this.value = this.value.replace(/\D/g, '').slice(0, 8);
        buscarBtn.disabled = this.value.length !== 8;
        validarFormulario();
    });

    // Función para validar todos los campos
    function validarFormulario() {
        const fullName = document.getElementById('fullName').value.trim();
        const apePaterno = document.getElementById('ApePaterno').value.trim();
        const apeMaterno = document.getElementById('ApeMaterno').value.trim();
        const telefono = document.getElementById('telefono').value.trim();
        const dni = dniInput.value.trim();

        const telefonoValido = /^9\d{8}$/;

        const botonGuardar = document.getElementById('GuardarNuevoUsuario');

        if (
            fullName !== '' &&
            apePaterno !== '' &&
            apeMaterno !== '' &&
            telefonoValido.test(telefono) &&
            dni.length === 8
        ) {
            botonGuardar.disabled = false;
        } else {
            botonGuardar.disabled = true;
        }
    }

    // Inicializa las validaciones
    soloLetras(document.getElementById('fullName'));
    soloLetras(document.getElementById('ApePaterno'));
    soloLetras(document.getElementById('ApeMaterno'));
    soloNumerosYValidarTelefono(document.getElementById('telefono'));

    // MODALES y FORMULARIOS
    const addModal = document.getElementById("addUserModal");
    const editModal = document.getElementById("editUserModal");

    const addForm = document.getElementById("addUserForm");
    const editForm = document.getElementById("editUserForm");

    const openAddBtn = document.getElementById("openAddUserBtn"); // Botón de agregar usuario
    const closeAddBtn = document.getElementById("closeAdd");
    const closeEditBtn = document.getElementById("closeEdit");

    const cancelAddBtn = document.getElementById("cancelAdd");
    const cancelEditBtn = document.getElementById("cancelEdit");

    /*window.addEventListener("click", (e) => {
        if (e.target === addModal) addModal.style.display = "none";
        if (e.target === editModal) editModal.style.display = "none";
    });*/

    // Abrir modal de agregar
    openAddBtn.addEventListener("click", () => {
        addForm.reset();
        addModal.style.display = "block";
        addForm.querySelector("input").focus();
    });

    // Cerrar modal de agregar
    closeAddBtn.addEventListener("click", () => {
        addModal.style.display = "none";
        document.getElementById('fullName').readOnly = false;
        document.getElementById('ApePaterno').readOnly = false;
        document.getElementById('ApeMaterno').readOnly = false;
    });

    // Botón Cancelar de agregar
    cancelAddBtn.addEventListener("click", () => {
        addModal.style.display = "none";
        document.getElementById('fullName').readOnly = false;
        document.getElementById('ApePaterno').readOnly = false;
        document.getElementById('ApeMaterno').readOnly = false;
    });

    // Cerrar modal de editar
    closeEditBtn.addEventListener("click", () => editModal.style.display = "none");
    // Botón Cancelar de editar
    cancelEditBtn.addEventListener("click", () => editModal.style.display = "none");

    // Abrir modal de edición con datos
    document.querySelectorAll("[id^='editBtn_']").forEach(btn => {
        btn.addEventListener("click", () => {
            const row = btn.closest("tr");

            // Obtener valores de la fila
            const idUsuario = btn.dataset.id; // <- Aquí obtenemos el ID desde el data-id
            const fullName = row.querySelector(".user-fullname").textContent.trim();
            const apePaterno = row.querySelector(".user-apepaterno").textContent.trim();
            const apeMaterno = row.querySelector(".user-apematerno").textContent.trim();
            const dni = row.querySelector(".user-dni").value;
            const telefono = row.querySelector(".user-telefono").value;
            const correo = row.querySelector(".user-correo").value;
            const rolId = row.querySelector(".user-rol").dataset.rolId;

            // Asignar valores al formulario
            editForm.querySelector("#idUsuarioEdit").value = idUsuario;
            editForm.querySelector("#dniEdit").value = dni;
            editForm.querySelector("#fullNameEdit").value = fullName;
            editForm.querySelector("#ApePaternoEdit").value = apePaterno;
            editForm.querySelector("#ApeMaternoEdit").value = apeMaterno;
            editForm.querySelector("#telefonoEdit").value = telefono;
            editForm.querySelector("#emailEdit").value = correo;
            editForm.querySelector("#roleEdit").value = rolId;

            // Mostrar el modal
            editModal.style.display = "block";
        });
    });

    // Visualizar detalles del usuario con SweetAlert
    document.querySelectorAll(".btn.view").forEach(btn => {
        btn.addEventListener("click", () => {
            const row = btn.closest("tr");

            // Obtener datos visibles
            const nombre = row.querySelector(".user-fullname").textContent.trim();
            const apePaterno = row.querySelector(".user-apepaterno").textContent.trim();
            const apeMaterno = row.querySelector(".user-apematerno").textContent.trim();
            const nombreCompleto = `${nombre} ${apePaterno} ${apeMaterno}`;
            const dni = row.querySelector(".user-dni")?.value || "No registrado";
            const telefono = row.querySelector(".user-telefono")?.value || "No registrado";
            const rol = row.querySelector(".user-rol").textContent.trim();
            const estado = row.querySelector(".user-estado")?.textContent.trim() || "Desconocido";

            // Obtener datos ocultos
            const email = row.querySelector(".user-correo")?.value || "No registrado";
            const userLogin = row.querySelector(".user-login")?.value || "No registrado";

            // Mostrar SweetAlert con los detalles del usuario
            Swal.fire({
                title: `Detalles del Usuario`,
                html: `
                <p><strong>Nombre Completo:</strong> ${nombreCompleto}</p>
                <p><strong>DNI:</strong> ${dni}</p>
                <p><strong>Usuario:</strong> ${userLogin}</p>
                <p><strong>Email:</strong> ${email}</p>
                <p><strong>Telefono:</strong> ${telefono}</p>
                <p><strong>Rol:</strong> ${rol}</p>
                <p><strong>Estado:</strong> ${estado}</p>
            `,
                icon: 'info',
                confirmButtonText: 'Cerrar',
                confirmButtonColor: '#3085d6',
            });
        });
    });

    // Accion Resetear Contraseña
    document.querySelectorAll(".btn.reset-password").forEach(btn => {
        btn.addEventListener("click", function () {
            const row = this.closest("tr");
            const idUsuario = row.dataset.id;
            console.log("el idUsuario es: "+idUsuario);

            Swal.fire({
                title: "¿Estás seguro?",
                text: "Se restablecerá la contraseña a su valor predeterminado.",
                icon: "warning",
                showCancelButton: true,
                confirmButtonText: "Sí, resetear",
                cancelButtonText: "Cancelar"
            }).then((result) => {
                if (result.isConfirmed) {
                    fetch(`${location.origin}/BeltCoreCG_war_exploded/user`, {
                        method: "POST",
                        headers: {
                            "Content-Type": "application/x-www-form-urlencoded"
                        },
                        body: `accion=restartPass&idUsuario=${encodeURIComponent(idUsuario)}`
                    })
                        .then(response => {
                            if (response.ok) {
                                Swal.fire("¡Éxito!", "Contraseña reseteada correctamente.", "success")
                                    .then(() => location.reload());
                            } else {
                                Swal.fire("Error", "No se pudo resetear la contraseña.", "error");
                            }
                        })
                        .catch(() => {
                            Swal.fire("Error", "Ocurrió un error en la solicitud.", "error");
                        });
                }
            });
        });
    });

    // Acción Activar o Desactivar Usuario
    document.querySelectorAll(".btn-toggle-status").forEach(btn => {
        btn.addEventListener("click", function () {
            const row = this.closest("tr");
            const idUsuario = row.dataset.id;
            const esActivo = row.dataset.activo === "true"; // true o false
            const accion = esActivo ? "inactivate" : "activate"; // Acción que se envía

            Swal.fire({
                title: `¿Estás seguro de ${esActivo ? "desactivar" : "activar"} este usuario?`,
                icon: "question",
                showCancelButton: true,
                confirmButtonText: esActivo ? "Sí, desactivar" : "Sí, activar",
                cancelButtonText: "Cancelar"
            }).then((result) => {
                if (result.isConfirmed) {
                    fetch(`${location.origin}/BeltCoreCG_war_exploded/user`, {
                        method: "POST",
                        headers: {
                            "Content-Type": "application/x-www-form-urlencoded"
                        },
                        body: `accion=${accion}&idUsuario=${encodeURIComponent(idUsuario)}`
                    })
                        .then(response => {
                            if (response.ok) {
                                Swal.fire("¡Éxito!", `Usuario ${esActivo ? "desactivado" : "activado"} correctamente.`, "success")
                                    .then(() => location.reload());
                            } else {
                                Swal.fire("Error", "No se pudo completar la acción.", "error");
                            }
                        })
                        .catch(() => {
                            Swal.fire("Error", "Ocurrió un error en la solicitud.", "error");
                        });
                }
            });
        });
    });

    // Filtros de tabla
    function applyFilters() {
        const roleFilterValue = document.getElementById("roleFilter").value.toLowerCase();
        const statusFilterValue = document.getElementById("statusFilter").value.toLowerCase();
        const searchValue = document.querySelector(".search-provider").value.toLowerCase();

        const rows = document.querySelectorAll(".user-table tbody tr");

        rows.forEach(row => {
            const rol = row.querySelector(".user-rol").textContent.toLowerCase().trim();
            const estado = row.querySelector(".user-estado").textContent.toLowerCase().trim();
            const nombre = row.cells[1].textContent.toLowerCase();

            const matchesRole = !roleFilterValue || rol === roleFilterValue;
            const matchesStatus = !statusFilterValue || estado === statusFilterValue;
            const matchesSearch = !searchValue || nombre.includes(searchValue);

            row.style.display = (matchesRole && matchesStatus && matchesSearch) ? "" : "none";
        });
    }

    document.getElementById("applyFilters").addEventListener("click", applyFilters);
    document.querySelector(".search-provider").addEventListener("input", applyFilters);
    document.getElementById("roleFilter").addEventListener("change", applyFilters);
    document.getElementById("statusFilter").addEventListener("change", applyFilters);

});