
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
    window.addEventListener("click", (e) => {
        if (e.target === modalProducto) modalProducto.classList.add("oculto");
    });

    // ----- Modal Categoría -----
    const btnCategoria = document.getElementById("btnCategoria");
    const modalCategoria = document.getElementById("modal-categoria");
    const cerrarCategoria = modalCategoria.querySelector(".cerrar-modal");
    const cancelarCategoria = modalCategoria.querySelector(".cancelar");

    btnCategoria.addEventListener("click", () => modalCategoria.classList.remove("oculto"));
    cerrarCategoria.addEventListener("click", () => modalCategoria.classList.add("oculto"));
    cancelarCategoria.addEventListener("click", () => modalCategoria.classList.add("oculto"));
    window.addEventListener("click", (e) => {
        if (e.target === modalCategoria) modalCategoria.classList.add("oculto");
    });

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
                estado.textContent = "Activo";
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
                estado.textContent = "Inactivo";
                estado.classList.remove("role-green");
                estado.classList.add("role-red");

                this.outerHTML = `<button class="btn activate" title="Activar"><i class="fas fa-check-circle"></i></button>`;
                attachActivateHandlers();
            });
        });
    }

    attachActivateHandlers();
    attachDeactivateHandlers();
});