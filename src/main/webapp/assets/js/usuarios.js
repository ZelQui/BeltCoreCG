document.addEventListener("DOMContentLoaded", () => {
    const modal = document.getElementById("userModal");
    const form = document.getElementById("userForm");
    const openBtn = document.querySelector(".add-provider");
    const closeBtn = modal.querySelector(".close");
    const cancelBtn = modal.querySelector(".cancel");

    // Función para abrir el modal (add/edit)
    function openModal(mode, userData = {}) {
        form.reset();
        form.setAttribute("data-mode", mode);

        if (mode === "edit") {
            document.getElementById("firstName").value = userData.firstName || "";
            document.getElementById("lastName").value = userData.lastName || "";
            document.getElementById("email").value = userData.email || "";
            document.getElementById("username").value = userData.username || "";
            document.getElementById("role").value = userData.role || "";
            document.getElementById("status").value = userData.status || "";
        }

        modal.style.display = "block";
        document.getElementById("firstName").focus();
    }

    // Abrir modal para nuevo usuario
    openBtn.addEventListener("click", () => openModal("add"));

    // Cerrar modal con botón "x", "Cancelar" o clic fuera
    closeBtn.addEventListener("click", () => (modal.style.display = "none"));
    cancelBtn.addEventListener("click", () => (modal.style.display = "none"));
    window.addEventListener("click", (e) => {
        if (e.target === modal) modal.style.display = "none";
    });

    // Acción: Ver usuario
    document.querySelectorAll(".btn.view").forEach((btn) => {
        btn.addEventListener("click", () => {
            const row = btn.closest("tr");
            const userName = row.children[1].textContent;
            alert(`Viendo información de: ${userName}`);
        });
    });

    // Acción: Editar usuario
    document.querySelectorAll(".btn.edit").forEach(btn => {
        btn.addEventListener("click", () => {
            const row = btn.closest("tr");
            openModal("edit", {
                firstName: row.children[1].textContent.split(" ")[0],
                lastName: row.children[1].textContent.split(" ").slice(1).join(" "),
                email: row.children[2].textContent,
                username: row.children[2].textContent.split("@")[0],
                role: row.querySelector(".role-badge").textContent.toLowerCase().trim(),
                status: row.querySelectorAll(".role-badge")[1].textContent.toLowerCase().trim()
            });
        });
    });

    // Acción: Resetear contraseña
    document.querySelectorAll(".btn.reset-password").forEach((btn) => {
        btn.addEventListener("click", () => {
            const email = btn.closest("tr").children[2].textContent;
            if (confirm(`¿Deseas resetear la contraseña de ${email}?`)) {
                alert("Contraseña reseteada correctamente.");
            }
        });
    });

    // Acción: Activar / Desactivar usuario
    document.querySelectorAll(".btn.activate, .btn.deactivate").forEach((btn) => {
        btn.addEventListener("click", () => {
            const row = btn.closest("tr");
            const name = row.children[1].textContent;
            const action = btn.classList.contains("activate") ? "activado" : "desactivado";
            alert(`${name} ha sido ${action}.`);
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
            const estado = row.cells[4].textContent.toLowerCase(); // ✅ aquí está el fix
            const nombre = row.cells[1].textContent.toLowerCase(); // ✅ nombre completo

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