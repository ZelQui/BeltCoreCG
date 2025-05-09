


document.addEventListener("DOMContentLoaded", () => {
  // Ver proveedor
  document.querySelectorAll(".btn.view").forEach((button) => {
    button.addEventListener("click", () => {
      const row = button.closest("tr");
      const nombre = row.children[1].textContent;
      const telefono = row.children[2].textContent;
      const correo = row.children[3].textContent;
      const direccion = row.children[4].textContent;

      alert(`👁️ Ver proveedor:\n\nNombre: ${nombre}\nTeléfono: ${telefono}\nCorreo: ${correo}\nDirección: ${direccion}`);
    });
  });

  // Editar Proveedor
  document.querySelectorAll(".btn.edit").forEach((button) => {
    button.addEventListener("click", () => {
      const row = button.closest("tr");

      // Obtener ID del proveedor (ya sea desde el <td> o atributo data-id)
      const idProveedor = row.dataset.id; // si usas <tr data-id="...">

      // Llenar el modal con los datos de la fila
      document.getElementById("providerId").value = idProveedor;
      document.getElementById("providerName").value = row.children[1].textContent;
      document.getElementById("phone").value = row.children[2].textContent;
      document.getElementById("email").value = row.children[3].textContent;
      document.getElementById("address").value = row.children[4].textContent;

      // Mostrar el modal
      document.getElementById("providerModal").style.display = "block";
    });
  });

  // Cambiar Estados Proveedor
  document.querySelectorAll(".btn.toggle").forEach((button) => {
    button.addEventListener("click", function (e) {
      e.preventDefault();  // Evita que el formulario se envíe antes de confirmar

      const form = this.closest("form");  // Obtén el formulario contenedor
      const icon = this.querySelector("i");  // El ícono que cambia
      const estadoSpan = this.querySelector("span");  // El texto del estado
      const esActivo = icon.classList.contains("fa-user-check");  // Si el proveedor está activo
      const accion = esActivo ? "desactivar" : "activar";  // Acción según el estado
      const nombreProveedor = this.closest("tr").children[1].textContent;  // Nombre del proveedor

      Swal.fire({
        title: `¿Deseas ${accion} al proveedor "${nombreProveedor}"?`,
        icon: "question",
        showCancelButton: true,
        confirmButtonText: `Sí, ${accion}`,
        cancelButtonText: "Cancelar",
      }).then((result) => {
        if (result.isConfirmed) {
          // Actualiza el estado visual en el frontend
          icon.classList.toggle("fa-user-check");
          icon.classList.toggle("fa-user-slash");
          estadoSpan.classList.toggle("text-success");
          estadoSpan.classList.toggle("text-danger");
          estadoSpan.textContent = esActivo ? "Inactivo" : "Activo";

          // Envía el formulario después de la confirmación
          form.submit();
        }
      });
    });
  });

});
document.addEventListener("DOMContentLoaded", () => {
  // Buscar proveedores por nombre
  function applyFilters() {
    const searchValue = document.querySelector(".search-provider").value.toLowerCase();
    const rows = document.querySelectorAll(".provider-table tbody tr");

    rows.forEach(row => {
      const nombre = row.children[1].textContent.toLowerCase();
      const matchesSearch = !searchValue || nombre.includes(searchValue);

      row.style.display = matchesSearch ? "" : "none";
    });
  }

  // Evento en tiempo real al escribir
  document.querySelector(".search-provider").addEventListener("input", applyFilters);

  // Evento al presionar botón "Buscar"
  document.getElementById("applyFilters").addEventListener("click", applyFilters);
});

// MODALS
document.addEventListener("DOMContentLoaded", () => {
  // ----- MODAL PARA AÑADIR PROVEEDOR -----
  const addModal = document.getElementById("addProviderModal");
  const addOpenBtn = document.querySelector(".add-provider");
  const addCloseBtn = addModal?.querySelector(".close");
  const addCancelBtn = addModal?.querySelector(".cancel");

  if (addOpenBtn) {
    addOpenBtn.addEventListener("click", () => {
      addModal.style.display = "block";
    });
  }

  if (addCloseBtn) {
    addCloseBtn.addEventListener("click", () => {
      addModal.style.display = "none";
    });
  }

  if (addCancelBtn) {
    addCancelBtn.addEventListener("click", () => {
      addModal.style.display = "none";
    });
  }

  window.addEventListener("click", (event) => {
    if (event.target === addModal) {
      addModal.style.display = "none";
    }
  });

  // ----- MODAL PARA EDITAR/VER PROVEEDOR -----
  const editModal = document.getElementById("providerModal");
  const editCloseBtn = editModal?.querySelector(".close");
  const editCancelBtn = editModal?.querySelector(".cancel");

  // Asignar evento a todos los botones de editar
  const editBtns = document.querySelectorAll(".btn.edit");
  editBtns.forEach((btn) => {
    btn.addEventListener("click", () => {
      editModal.style.display = "block";
      // Aquí puedes cargar los datos al formulario si es necesario
    });
  });

  if (editCloseBtn) {
    editCloseBtn.addEventListener("click", () => {
      editModal.style.display = "none";
    });
  }

  if (editCancelBtn) {
    editCancelBtn.addEventListener("click", () => {
      editModal.style.display = "none";
    });
  }

  window.addEventListener("click", (event) => {
    if (event.target === editModal) {
      editModal.style.display = "none";
    }
  });
});

function closeAddProviderModal() {
  document.getElementById('addProviderModal').style.display = 'none';
}
