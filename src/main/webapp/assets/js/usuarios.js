document.addEventListener("DOMContentLoaded", () => {
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
    closeAddBtn.addEventListener("click", () => addModal.style.display = "none");
    // Botón Cancelar de agregar
    cancelAddBtn.addEventListener("click", () => addModal.style.display = "none");

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
            const email = row.querySelector(".user-email").textContent.trim();
            const rolId = row.querySelector(".user-rol").dataset.rolId;

            // Asignar valores al formulario
            editForm.querySelector("#idUsuarioEdit").value = idUsuario;
            editForm.querySelector("#fullNameEdit").value = fullName;
            editForm.querySelector("#emailEdit").value = email;
            editForm.querySelector("#roleEdit").value = rolId;

            // Mostrar el modal
            editModal.style.display = "block";
        });
    });

    // Visualizar detalles del usuario con SweetAlert
    document.querySelectorAll(".btn.view").forEach(btn => {
        btn.addEventListener("click", () => {
            const row = btn.closest("tr");

            const idUsuario = row.children[0].textContent.trim();
            const nombreCompleto = row.children[1].textContent.trim();
            const email = row.children[2].textContent.trim();
            const rol = row.querySelector(".role-badge").textContent.trim();
            const estado = row.querySelectorAll(".role-badge")[1]?.textContent.trim() || "Desconocido";

            // Mostrar SweetAlert con los detalles del usuario
            Swal.fire({
                title: `Detalles del Usuario:`,
                html: `
                    <strong># ${idUsuario}</strong><br>
                    <strong>Nombre Completo:</strong> ${nombreCompleto} <br>
                    <strong>Email:</strong> ${email} <br>
                    <strong>Contraseña:</strong> ***** <br>
                    <strong>Rol:</strong> ${rol} <br>
                    <strong>Estado:</strong> ${estado} <br>
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

    // Filtros de tabla
    function applyFilters() {
        const roleFilterValue = document.getElementById("roleFilter").value.toLowerCase();
        const statusFilterValue = document.getElementById("statusFilter").value.toLowerCase();
        const searchValue = document.querySelector(".search-provider").value.toLowerCase();

        const rows = document.querySelectorAll(".user-table tbody tr");

        rows.forEach(row => {
            const rol = row.cells[3].textContent.toLowerCase();
            const estado = row.cells[4].textContent.toLowerCase();
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
