document.addEventListener("DOMContentLoaded", function () {
    // ----- Modal Categoria -----
    const btnProducto = document.getElementById("btnProducto");
    const modalProducto = document.getElementById("modal-producto");
    const cerrarProducto = modalProducto.querySelector(".cerrar-modal");
    const cancelarProducto = modalProducto.querySelector(".cancelar");
    const formProducto = document.getElementById("form-producto");

    btnProducto.addEventListener("click", () => {
        modalProducto.classList.remove("oculto");
        formProducto.reset();
        modalProducto.querySelector("h2").textContent = "Nueva categoria";
        modalProducto.querySelector("button[type='submit']").textContent = "Agregar";
    });

    cerrarProducto.addEventListener("click", () => modalProducto.classList.add("oculto"));
    cancelarProducto.addEventListener("click", () => modalProducto.classList.add("oculto"));

    // ----- Ver Categoria -----
    document.querySelectorAll(".btn.view").forEach(btn => {
        btn.addEventListener("click", function () {
            const row = this.closest("tr");
            const nombre = row.cells[1].textContent;
            alert(`Visualizando categoria: ${nombre}`);
        });
    });

    // ----- Editar Categoria -----
    document.querySelectorAll(".btn.edit").forEach(btn => {
        btn.addEventListener("click", function () {
            const row = this.closest("tr");
            const nombre = row.cells[1].textContent.trim();

            // Rellenar formulario
            formProducto.querySelector('input[type="text"]').value = nombre;

            // Cambiar título y botón
            modalProducto.querySelector("h2").textContent = "Editar categoria";
            modalProducto.querySelector("button[type='submit']").textContent = "Actualizar";

            modalProducto.classList.remove("oculto");
        });
    });

    // ----- Eliminar Categoria -----
    document.querySelectorAll(".btn.delete").forEach(btn => {
        btn.addEventListener("click", function () {
            const row = this.closest("tr");
            const nombre = row.cells[1].textContent;
            const confirmacion = confirm(`¿Estás seguro de eliminar la categoria ${nombre}?`);
            if (confirmacion) {
                row.remove(); // Elimina la fila de la tabla
                alert(`Categoria ${nombre} eliminada.`);
            }
        });
    });

    // ----- Funcionalidad de Búsqueda -----
    const searchInput = document.querySelector(".search-provider");
    const searchButton = document.getElementById("applyFilters");

    searchButton.addEventListener("click", () => {
        const searchTerm = searchInput.value.trim().toLowerCase();
        const rows = document.querySelectorAll(".user-table tbody tr");

        rows.forEach(row => {
            const nombre = row.cells[1].textContent.toLowerCase();
            if (nombre.includes(searchTerm)) {
                row.style.display = ""; // Mostrar fila si coincide
            } else {
                row.style.display = "none"; // Ocultar fila si no coincide
            }
        });
    });

    attachActivateHandlers();
    attachDeactivateHandlers();
});
