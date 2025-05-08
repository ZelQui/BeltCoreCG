const purchaseModal = document.getElementById("purchaseModal");
const closeModal = purchaseModal.querySelector(".close");

document.querySelector(".add-provider").addEventListener("click", () => {
    openModal("add");
});

closeModal.addEventListener("click", () => {
    purchaseModal.style.display = "none";
});

/* window.addEventListener("click", (e) => {
    if (e.target === purchaseModal) purchaseModal.style.display = "none";
}); */

document.addEventListener("DOMContentLoaded", () => {
    const modal = document.getElementById("purchaseModal");
    const form = document.getElementById("purchaseForm");
    const closeBtn = modal.querySelector(".close");
    const cancelBtn = modal.querySelector(".cancel");

    // Función para abrir el modal y rellenar si es edición
    function openModal(mode, purchaseData = {}) {
        form.reset();
        form.setAttribute("data-mode", mode);

        if (mode === "edit") {
            document.getElementById("provider").value = purchaseData.provider || "";
            document.getElementById("invoiceType").value = purchaseData.invoiceType || "";
            document.getElementById("invoiceNumber").value = purchaseData.invoiceNumber || "";
            document.getElementById("date").value = purchaseData.date || "";
        }

        modal.style.display = "block";
    }

    // Cerrar modal al hacer clic en "x"
    closeBtn.addEventListener("click", () => {
        modal.style.display = "none";
    });

    // Cerrar modal al hacer clic en "Cancelar"
    cancelBtn.addEventListener("click", () => {
        modal.style.display = "none";
    });

    // Acción: Ver compra
    document.querySelectorAll(".btn.view").forEach(btn => {
        btn.addEventListener("click", () => {
            const row = btn.closest("tr");
            const purchaseInfo = row.children[2].textContent;
            alert("Viendo información de: " + purchaseInfo);
        });
    });

    // Acción: Editar compra
    document.querySelectorAll(".btn.edit").forEach(btn => {
        btn.addEventListener("click", () => {
            const row = btn.closest("tr");
            openModal("edit", {
                provider: row.children[2].textContent,
                invoiceNumber: row.children[3].textContent,
                date: row.children[1].textContent,
                invoiceType: row.children[5].textContent
            });
        });
    });

    // Acción: Eliminar compra
    document.querySelectorAll(".btn.deactivate").forEach(btn => {
        btn.addEventListener("click", () => {
            const row = btn.closest("tr");
            const purchaseId = row.children[0].textContent;
            if (confirm(`¿Deseas eliminar la compra con ID ${purchaseId}?`)) {
                alert("Compra eliminada correctamente.");
                // Aquí iría la lógica para eliminar la compra
            }
        });
    });

    // Acción: Abrir modal de nuevo proveedor
    document.querySelector(".add-provider").addEventListener("click", () => openModal("add"));
});

function applyFilters() {
    const roleFilterValue = document.getElementById("roleFilter").value.toLowerCase(); // proveedor
    const statusFilterValue = document.getElementById("statusFilter").value.toLowerCase(); // método de pago
    const searchValue = document.querySelector(".search-provider").value.toLowerCase(); // texto libre

    const rows = document.querySelectorAll(".provider-table tbody tr");

    rows.forEach(row => {
        const proveedor = row.cells[2].textContent.toLowerCase(); // columna proveedor
        const factura = row.cells[3].textContent.toLowerCase();   // columna N° factura
        const metodo = row.cells[5].textContent.toLowerCase();    // columna método de pago

        const matchesProvider = !roleFilterValue || proveedor === roleFilterValue;
        const matchesPayment = !statusFilterValue || metodo === statusFilterValue;
        const matchesSearch = !searchValue || proveedor.includes(searchValue) || factura.includes(searchValue);

        if (matchesProvider && matchesPayment && matchesSearch) {
            row.style.display = "";
        } else {
            row.style.display = "none";
        }
    });
}

// Eventos para aplicar filtros en tiempo real
document.getElementById("roleFilter").addEventListener("change", applyFilters);
document.getElementById("statusFilter").addEventListener("change", applyFilters);
document.querySelector(".search-provider").addEventListener("input", applyFilters);

// Opcional: botón para volver a aplicar filtros
const applyFiltersBtn = document.getElementById("applyFilters");
applyFiltersBtn.addEventListener("click", applyFilters);
