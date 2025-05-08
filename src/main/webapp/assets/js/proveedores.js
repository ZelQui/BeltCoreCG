
document.addEventListener("DOMContentLoaded", () => {
  const modal = document.getElementById("providerModal");
  const openBtn = document.querySelector(".add-provider");
  const closeBtn = modal.querySelector(".close");
  const cancelBtn = modal.querySelector(".cancel");

  // Mostrar modal
  openBtn.addEventListener("click", () => {
    modal.style.display = "block";
  });

  // Cerrar modal al hacer clic en "x"
  closeBtn.addEventListener("click", () => {
    modal.style.display = "none";
  });

  // Cerrar modal al hacer clic en "Cancelar"
  cancelBtn.addEventListener("click", () => {
    modal.style.display = "none";
  });

  // Cerrar modal al hacer clic fuera del contenido
  window.addEventListener("click", (event) => {
    if (event.target === modal) {
      modal.style.display = "none";
    }
  });
});

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

  // Editar proveedor (abre modal con datos)
  document.querySelectorAll(".btn.edit").forEach((button) => {
    button.addEventListener("click", () => {
      const row = button.closest("tr");

      // Llenar el modal con los datos de la fila
      document.getElementById("providerName").value = row.children[1].textContent;
      document.getElementById("phone").value = row.children[2].textContent;
      document.getElementById("email").value = row.children[3].textContent;
      document.getElementById("address").value = row.children[4].textContent;

      // Mostrar el modal
      document.getElementById("providerModal").style.display = "block";
    });
  });

  // Desactivar proveedor
  document.querySelectorAll(".btn.deactivate").forEach((button) => {
    button.addEventListener("click", () => {
      const row = button.closest("tr");
      const nombre = row.children[1].textContent;

      if (confirm(`¿Estás seguro de desactivar al proveedor "${nombre}"?`)) {
        // Aquí puedes ocultar la fila, marcar como desactivado, o llamar a una función backend
        alert(`Proveedor "${nombre}" ha sido desactivado.`);
      }
    });
  });
});
