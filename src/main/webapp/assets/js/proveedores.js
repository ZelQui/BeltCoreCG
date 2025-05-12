
function buscarProveedorSUNAT() {
  const inputElement = document.getElementById('nuevoRuc');

  if (!inputElement) {
    console.error("Input 'nuevoRuc' no encontrado");
    return;
  }

  const ruc = inputElement.value.trim();

  if (!ruc || !/^\d{11}$/.test(ruc)) {
    alert('Por favor ingrese un RUC válido de 11 dígitos');
    return;
  }

  
  const url = BASE_URL + "/apiController?ruc=" + ruc;

  console.log("RUC enviado:", ruc);
  console.log("URL final para la consulta:", url);

  fetch(url)
      .then(response => {
        if (!response.ok) throw new Error('Error en la consulta');
        return response.json();
      })
      .then(data => {
        if (!data || data.error || !data.razonSocial) throw new Error('RUC no encontrado');

        document.getElementById('nuevoNombre').value = data.razonSocial || '';
        document.getElementById('nuevaDireccion').value = data.direccion || '';
        document.getElementById('estadoRuc').value = data.estado || '';
      })
      .catch(error => {
        console.error('Error:', error);
        alert('No se pudo obtener información para el RUC ingresado');
        document.getElementById('nuevoNombre').value = '';
        document.getElementById('nuevaDireccion').value = '';
        document.getElementById('estadoRuc').value = 'No encontrado';
      });
}

function habilitarCuenta() {
  const select = document.getElementById("tipoCuenta");
  const inputCuenta = document.getElementById("nuevaCuenta");

  if (select.value !== "") {
    inputCuenta.disabled = false;
  } else {
    inputCuenta.disabled = true;
    inputCuenta.value = "";
  }
}


document.addEventListener("DOMContentLoaded", () => {
  // Ver proveedor con SweetAlert (mejorado sin mensaje al copiar)
  document.querySelectorAll(".btn.view").forEach((button) => {
    button.addEventListener("click", () => {
      const row = button.closest("tr");
      const nombre = row.children[1].textContent.trim();
      const telefono = row.children[2].textContent.trim();
      const correo = row.children[3].textContent.trim();
      const ruc = row.children[4].textContent.trim();
      const direccion = row.querySelector(".direccion-hidden").value;
      const cci = row.querySelector(".cci-hidden").value;

      Swal.fire({
        title: 'Detalles del Proveedor',
        html: `
        <p><strong>Nombre:</strong> ${nombre}</p>
        <p><strong>Teléfono:</strong> ${telefono}</p>
        <p><strong>Correo:</strong> ${correo}</p>
        <p><strong>Dirección:</strong> ${direccion}</p>
        <p><strong>🧾 RUC:</strong> ${ruc}</p>
        <p>
          <strong>CCI:</strong> 
          <span id="cciText">${cci}</span>
          <button id="copyCCIButton" title="Copiar CCI" class="btn-copy-cci">
            <i class="fas fa-copy"></i>
          </button>
        </p>
      `,
        icon: 'info',
        confirmButtonText: 'Cerrar',
        customClass: {
          popup: 'swal2-border-radius'
        },
        didRender: () => {
          const btnCopy = Swal.getHtmlContainer().querySelector("#copyCCIButton");
          btnCopy.addEventListener("click", () => {
            navigator.clipboard.writeText(cci).catch(() => {});
          });
        }
      });
    });
  });

  // Editar Proveedor
  document.querySelectorAll(".btn.edit").forEach((button) => {
    button.addEventListener("click", () => {
      const row = button.closest("tr");
      const idProveedor = row.dataset.id;

      // Obtener los datos de las celdas
      const ruc = row.children[1].textContent.trim();
      const nombre = row.children[2].textContent.trim();
      const estado = row.children[3].textContent.trim();
      const direccionFiscal = row.children[4].textContent.trim();
      const telefono = row.children[5].textContent.trim();

      // Obtener datos ocultos
      const domicilioAlterna = row.querySelector(".direccion-hidden").value;
      const tipoCuenta = row.querySelector(".tipo-hidden").value;
      const numeroCuenta = row.querySelector(".cci-hidden").value;

      // Llenar los campos del modal
      document.getElementById("providerId").value = idProveedor;
      document.getElementById("editRuc").value = ruc;
      document.getElementById("editName").value = nombre;
      document.getElementById("editEstado").value = estado;
      document.getElementById("editDireccion").value = direccionFiscal;
      document.getElementById("editTelefono").value = telefono;
      document.getElementById("editDireccionAlterna").value = domicilioAlterna;
      document.getElementById("editTipoCuenta").value = tipoCuenta;
      document.getElementById("editNumeroCuenta").value = numeroCuenta;

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

