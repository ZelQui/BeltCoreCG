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

    // Cerrar modal de editar
    closeEditBtn.addEventListener("click", () => editModal.style.display = "none");

    // Botón Cancelar de agregar
    cancelAddBtn.addEventListener("click", () => addModal.style.display = "none");

    // Botón Cancelar de editar
    cancelEditBtn.addEventListener("click", () => editModal.style.display = "none");

    // Abrir modal de edición con datos
    document.querySelectorAll("[id^='editBtn_']").forEach(btn => {
        btn.addEventListener("click", () => {
            const row = btn.closest("tr");

            // Limpiar y llenar el formulario de edición con los datos del usuario
            editForm.reset();
            editForm.querySelector("#fullNameEdit").value = row.children[1].textContent.trim();
            editForm.querySelector("#emailEdit").value = row.children[2].textContent.trim();
            editForm.querySelector("#roleEdit").value = row.querySelector(".role-badge").textContent.toLowerCase().trim();

            // Mostrar el modal de edición
            editModal.style.display = "block";
            editForm.querySelector("input").focus();
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
