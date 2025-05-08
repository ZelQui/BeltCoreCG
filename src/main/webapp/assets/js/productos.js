document.addEventListener("DOMContentLoaded", function () {
    // ----- Modal Producto -----
    const btnProducto = document.getElementById("btnProducto");
    const modalProducto = document.getElementById("modal-producto");
    const cerrarProducto = modalProducto.querySelector(".cerrar-modal");
    const cancelarProducto = modalProducto.querySelector(".cancelar");
    const formProducto = document.getElementById("form-producto");

    btnProducto.addEventListener("click", () => {
        modalProducto.classList.remove("oculto");
        formProducto.reset();
        modalProducto.querySelector("h2").textContent = "Nuevo Producto";
        modalProducto.querySelector("button[type='submit']").textContent = "Agregar";
    });

    cerrarProducto.addEventListener("click", () => modalProducto.classList.add("oculto"));
    cancelarProducto.addEventListener("click", () => modalProducto.classList.add("oculto"));

    // ----- Ver Producto -----
    document.querySelectorAll(".btn.view").forEach(btn => {
        btn.addEventListener("click", function () {
            const row = this.closest("tr");
            const nombre = row.cells[2].textContent;
            alert(`Visualizando producto: ${nombre}`);
        });
    });

    // ----- Editar Producto -----
    document.querySelectorAll(".btn.edit").forEach(btn => {
        btn.addEventListener("click", function () {
            const row = this.closest("tr");
            const nombre = row.cells[2].textContent.trim();
            const categoria = row.cells[3].textContent.trim();
            const precio = row.cells[4].textContent.replace("S/ ", "").trim();

            // Rellenar formulario
            formProducto.querySelector('input[type="text"]').value = nombre;
            formProducto.querySelector('select').value = categoria;
            formProducto.querySelector('input[type="number"]').value = precio;

            // Cambiar título y botón
            modalProducto.querySelector("h2").textContent = "Editar Producto";
            modalProducto.querySelector("button[type='submit']").textContent = "Actualizar";

            modalProducto.classList.remove("oculto");
        });
    });

    // ----- Activar/Desactivar Producto -----
    function attachActivateHandlers() {
        document.querySelectorAll(".btn.activate").forEach(btn => {
            btn.addEventListener("click", function () {
                const row = this.closest("tr");
                const estado = row.querySelector(".role-badge");
                estado.textContent = "En almacén";
                estado.classList.remove("role-red");
                estado.classList.add("role-green");

                this.outerHTML = `<button class="btn deactivate" title="Desactivar"><i class="fas fa-ban"></i></button>`;
                attachDeactivateHandlers();
            });
        });
    }

    function attachDeactivateHandlers() {
        document.querySelectorAll(".btn.deactivate").forEach(btn => {
            btn.addEventListener("click", function () {
                const row = this.closest("tr");
                const estado = row.querySelector(".role-badge");
                estado.textContent = "Agotado";
                estado.classList.remove("role-green");
                estado.classList.add("role-red");

                this.outerHTML = `<button class="btn activate" title="Activar"><i class="fas fa-check-circle"></i></button>`;
                attachActivateHandlers();
            });
        });
    }

    attachActivateHandlers();
    attachDeactivateHandlers();

    // ----- Filtrar Productos -----
    function applyFilters() {
        const roleFilterValue = document.getElementById("roleFilter").value.toLowerCase();
        const statusFilterValue = document.getElementById("statusFilter").value.toLowerCase();
        const searchValue = document.querySelector(".search-provider").value.toLowerCase();

        const rows = document.querySelectorAll(".user-table tbody tr");

        rows.forEach(row => {
            const categoria = row.cells[3].textContent.toLowerCase();
            const estado = row.querySelector(".role-badge").textContent.toLowerCase();
            const nombre = row.cells[2].textContent.toLowerCase();

            // Filtrado por categoría, estado y búsqueda
            const matchesCategory = !roleFilterValue || categoria === roleFilterValue;
            const matchesStatus = !statusFilterValue || estado === statusFilterValue;
            const matchesSearch = !searchValue || nombre.includes(searchValue);

            // Mostrar o ocultar según el filtro
            if (matchesCategory && matchesStatus && matchesSearch) {
                row.style.display = ""; // Mostrar fila
            } else {
                row.style.display = "none"; // Ocultar fila
            }
        });
    }

    // Evento para aplicar los filtros en tiempo real
    document.getElementById("roleFilter").addEventListener("change", applyFilters);
    document.getElementById("statusFilter").addEventListener("change", applyFilters);
    document.querySelector(".search-provider").addEventListener("input", applyFilters);

    // ----- Buscar con el botón (opcional) -----
    const applyFiltersBtn = document.getElementById("applyFilters");

    applyFiltersBtn.addEventListener("click", function() {
        applyFilters(); // Vuelve a aplicar los filtros si se hace clic en el botón
    });

});
