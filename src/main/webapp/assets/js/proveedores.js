// -------------------------------------------------------------------------------------------------------------------
function buscarProveedorSUNAT() {
  const inputElement = document.getElementById('nuevoRuc');

  if (!inputElement) {
    console.error("Input 'nuevoRuc' no encontrado");
    return;
  }

  const ruc = inputElement.value.trim();

  function esRucValido(ruc) {
    if (!/^\d{11}$/.test(ruc)) return false;

    const coeficientes = [5, 4, 3, 2, 7, 6, 5, 4, 3, 2];
    const suma = ruc
        .substring(0, 10)
        .split('')
        .reduce((acc, val, i) => acc + (parseInt(val, 10) * coeficientes[i]), 0);

    const resto = suma % 11;
    const digitoVerificador = (11 - resto) === 10 ? 0 : (11 - resto) === 11 ? 1 : 11 - resto;

    return digitoVerificador === parseInt(ruc[10], 10);
  }

  if (!ruc || !/^\d{11}$/.test(ruc) || !esRucValido(ruc)) {
    Swal.fire({
      icon: 'error',
      title: 'RUC inválido',
      text: 'Por favor ingrese un RUC válido de 11 dígitos según SUNAT.',
    });
    return;
  }

  const url = BASE_URL + "/apiController?ruc=" + ruc;

  console.log("RUC enviado:", ruc);
  console.log("URL final para la consulta:", url);

  fetch(url)
      .then(response => response.json())
      .then(data => {
        // Si el proveedor ya está registrado
        if (data.error === "Proveedor ya registrado") {
          Swal.fire({
            icon: 'warning',
            title: 'Proveedor ya registrado',
            text: 'Este proveedor ya está registrado en la base de datos.',
          });
          return;
        }

        // Si el RUC no fue encontrado o está dado de baja
        if (data.success === false) {
          Swal.fire({
            icon: 'error',
            title: 'RUC no encontrado',
            text: data.message || 'No se encontraron resultados para el RUC ingresado.',
          });

          document.getElementById('nuevoNombre').value = '';
          document.getElementById('nuevaDireccion').value = '';
          document.getElementById('estadoRuc').value = 'No encontrado';
          return;
        }

        // Si no hubo errores, llenar los campos con la información de la SUNAT
        document.getElementById('nuevoNombre').value = data.razonSocial || '';
        document.getElementById('nuevaDireccion').value = data.direccion || '';
        document.getElementById('estadoRuc').value = data.estado || '';
      })
      .catch(error => {
        console.error('Error:', error);
        Swal.fire({
          icon: 'error',
          title: 'Error',
          text: 'Ocurrió un error al realizar la consulta.',
        });
      });
}

// -------------------------------------------------------------------------------------------------------------------
// VERIFICAR CAMPOS VACIOS EN REGISTRAR PROVEEDOR
document.getElementById("addProviderForm").addEventListener("submit", function (e) {
  // Obtener los valores de los campos
  const nuevoRuc = document.getElementById("nuevoRuc").value.trim();
  const nuevoNombre = document.getElementById("nuevoNombre").value.trim();
  const estadoRuc = document.getElementById("estadoRuc").value.trim();
  const nuevaDireccion = document.getElementById("nuevaDireccion").value.trim();
  const nuevoTelefono = document.getElementById("nuevoTelefono").value.trim();
  const tipoCuenta = document.getElementById("tipoCuenta").value.trim();
  const nuevaCuenta = document.getElementById("nuevaCuenta").value.trim();

  // Verificar si algún campo está vacío
  if (
      nuevoRuc === "" ||
      nuevoNombre === "" ||
      estadoRuc === "" ||
      nuevaDireccion === "" ||
      nuevoTelefono === "" ||
      tipoCuenta === "" ||
      nuevaCuenta === ""
  ) {
    // Detener el envío del formulario
    e.preventDefault();

    // Mostrar alerta con SweetAlert2
    Swal.fire({
      title: "¡Atención!",
      text: "Por favor, rellene todos los campos.",
      icon: "warning",
      confirmButtonText: "Aceptar"
    });
  }
});


// -------------------------------------------------------------------------------------------------------------------
function habilitarYValidarCuenta() {
  const tipoCuenta = document.getElementById('tipoCuenta');
  const tipoCuentaTexto = tipoCuenta.options[tipoCuenta.selectedIndex].text.trim();
  const numeroCuenta = document.getElementById('nuevaCuenta');
  const labelCuenta = document.getElementById('labelCuenta');

  // Habilitar o deshabilitar el campo según si se ha seleccionado una opción válida
  numeroCuenta.disabled = tipoCuenta.value === "";
  numeroCuenta.value = ''; // Limpiar el valor cada vez que se cambia de tipo

  // Asignar maxlength y texto del label
  let maxLength = 0;
  let labelTexto = "Número de Cuenta";

  if (tipoCuentaTexto === 'Banco de la Nacion') {
    maxLength = 20;
    labelTexto = "N° de Cuenta (Ej: 48753311548862134698)";
  } else if (tipoCuentaTexto === 'BCP' || tipoCuentaTexto === 'BBVA') {
    maxLength = 12;
    labelTexto = "N° de Cuenta (Ej: 486132322789)";
  } else if (tipoCuentaTexto === 'Interbank') {
    maxLength = 13;
    labelTexto = "N° de Cuenta (Ej: 7845131314645)";
  } else if (tipoCuentaTexto === 'Yape/Plin') {
    maxLength = 9;
    labelTexto = "N° de Celular (Ej: 951875964)";
  } else if (tipoCuentaTexto === 'Otro (CCI)') {
    maxLength = 20;
    labelTexto = "N° de CCI (Ej: 54875613215454895262)";
  }

  numeroCuenta.setAttribute('maxlength', maxLength);
  labelCuenta.textContent = labelTexto;
}

// Mostrar error con SweetAlert
function mostrarError(tipo, digitos) {
  Swal.fire({
    icon: 'error',
    title: 'Número no válido',
    text: `El número de cuenta para ${tipo} debe tener ${digitos} dígitos y ser solo numérico.`,
  });
}

// Validar cuenta bancaria antes de enviar el formulario
document.addEventListener('DOMContentLoaded', function () {
  const form = document.getElementById('addProviderForm');

  form.addEventListener('submit', function (event) {
    const tipoCuenta = document.getElementById('tipoCuenta');
    const tipoCuentaTexto = tipoCuenta.options[tipoCuenta.selectedIndex].text;
    const numeroCuenta = document.getElementById('nuevaCuenta');
    const cuenta = numeroCuenta.value.trim();

    // Validar si se seleccionó tipo de cuenta
    if (tipoCuenta.value === "") {
      Swal.fire({
        icon: 'error',
        title: 'Tipo de cuenta no seleccionado',
        text: 'Debe seleccionar un tipo de cuenta antes de continuar.',
      });
      event.preventDefault();
      return;
    }

    // Validaciones según tipo de cuenta
    if (tipoCuentaTexto === 'Banco de la Nacion') {
      if (cuenta.length !== 20 || !/^\d+$/.test(cuenta)) {
        mostrarError('Banco de la Nación', '20');
        event.preventDefault();
      }
    } else if (tipoCuentaTexto === 'BCP' || tipoCuentaTexto === 'BBVA') {
      if (cuenta.length !== 12 || !/^\d+$/.test(cuenta)) {
        mostrarError(tipoCuentaTexto, '12');
        event.preventDefault();
      }
    } else if (tipoCuentaTexto === 'Interbank') {
      if (cuenta.length !== 13 || !/^\d+$/.test(cuenta)) {
        mostrarError('Interbank', '13');
        event.preventDefault();
      }
    } else if (tipoCuentaTexto === 'Yape/Plin') {
      if (cuenta.length !== 9 || !cuenta.startsWith('9') || !/^\d+$/.test(cuenta)) {
        Swal.fire({
          icon: 'error',
          title: 'Número no válido',
          text: 'El número para Yape/Plin debe tener 9 dígitos, empezar con 9 y ser numérico.',
        });
        event.preventDefault();
      }
    } else if (tipoCuentaTexto === 'Otro (CCI)') {
      if (cuenta.length !== 20 || !/^\d+$/.test(cuenta)) {
        mostrarError('CCI', '20');
        event.preventDefault();
      }
    }
  });
});

// -----------------------------------------------------------------------------------------------
// VALIDACIONES - REGISTRAR
const telefonoInput = document.getElementById('nuevoTelefono');
const errorSpan = document.getElementById('telefonoError');

// Solo permitir números mientras se escribe
telefonoInput.addEventListener('input', function () {
  // Eliminar cualquier carácter que no sea número
  this.value = this.value.replace(/\D/g, '');

  const telefono = this.value;

  if (!telefono.startsWith('9')) {
    errorSpan.textContent = 'El número debe comenzar con 9.';
  } else if (telefono.length > 0 && telefono.length < 9) {
    errorSpan.textContent = 'El número debe tener 9 dígitos.';
  } else {
    errorSpan.textContent = ''; // Sin errores
  }
});

// Validación antes de enviar el formulario
document.getElementById('addProviderForm').addEventListener('submit', function(event) {
  const telefono = telefonoInput.value;

  if (telefono.length !== 9 || telefono[0] !== '9') {
    event.preventDefault(); // Prevenir envío si el teléfono no es válido
    Swal.fire({
      icon: 'error',
      title: 'Número no válido',
      text: 'El número de teléfono debe tener 9 dígitos y empezar con 9.',
    });
  }
});

// Permitir solo letras (para campos de texto)
function soloLetrasInput(event) {
  const input = event.target;
  input.value = input.value.replace(/[^a-zA-ZáéíóúÁÉÍÓÚñÑ\s]/g, '');
}

// Permitir solo números (para campos tel o numéricos)
function soloNumerosInput(event) {
  const input = event.target;
  input.value = input.value.replace(/\D/g, ''); // Elimina cualquier cosa que no sea dígito
}

// Campos solo letras
const camposTexto = ['nuevoNombre', 'estadoRuc', 'nuevaDireccion', 'nuevaDireccionAlterna'];
camposTexto.forEach(id => {
  const input = document.getElementById(id);
  if (input) {
    input.addEventListener('input', soloLetrasInput);
  }
});

// Campos solo números
const camposNumericos = ['nuevoRuc', 'nuevoTelefono', 'nuevaCuenta'];
camposNumericos.forEach(id => {
  const input = document.getElementById(id);
  if (input) {
    input.addEventListener('input', soloNumerosInput);
  }
});

// -------------------------------------------------------------------------------------------------------------------
// Validación antes de enviar el formulario - Editar
document.getElementById('providerForm').addEventListener('submit', function(event) {
  const telefono = document.getElementById('editTelefono').value;
  if (telefono.length !== 9 || telefono[0] !== '9') {
    event.preventDefault(); // Prevenir envío si el teléfono no es válido
    Swal.fire({
      icon: 'error',
      title: 'Número no válido',
      text: 'El número de teléfono debe tener 9 dígitos y empezar con 9.',
    });
  }
});

// -------------------------------------------------------------------------------------------------------------------
// VER PROVEEDOR
document.addEventListener("DOMContentLoaded", () => {
  document.querySelectorAll(".btn.view").forEach((button) => {
    button.addEventListener("click", () => {
      const row = button.closest("tr");
      const ruc = row.children[1].textContent.trim();
      const razonSocial  = row.children[2].textContent.trim();
      const estadoContribuyente = row.children[3].textContent.trim();
      const domicilioFiscal = row.children[4].textContent.trim();
      const telefono = row.children[5].textContent.trim();
      const direccionAlterna = row.querySelector(".direccionAlterna-hidden").value;
      const tipoCuenta = row.querySelector(".tipo-hidden").value.trim().toLowerCase();;
      const numeroCuenta = row.querySelector(".cuenta-hidden").value;

      let cuentaLabel = "Número de Cuenta:";

      if (tipoCuenta === "banco de la nacion") {
        cuentaLabel = "N° de Cuenta:";
      } else if (tipoCuenta === "bcp") {
        cuentaLabel = "N° de Cuenta:";
      } else if (tipoCuenta === "bbva") {
        cuentaLabel = "N° de Cuenta:";
      } else if (tipoCuenta === "interbank") {
        cuentaLabel = "N° de Cuenta:";
      } else if (tipoCuenta === "yape/plin") {
        cuentaLabel = "N° de Celular:";
      } else if (tipoCuenta === "otro (cci)") {
        cuentaLabel = "N° de CCI:";
      }

      Swal.fire({
        title: 'Detalles del Proveedor',
        html: `
        <p><strong>Ruc:</strong> ${ruc}</p>
        <p><strong>Nombre o Razon Social:</strong> ${razonSocial}</p>
        <p><strong>Estado Contribuyente:</strong> ${estadoContribuyente}</p>
        <p><strong>Domicilio Fiscal:</strong> ${domicilioFiscal}</p>
        <p><strong>Telefono:</strong> ${telefono}</p>
        <p><strong>Domicilio Alterna:</strong> ${direccionAlterna}</p>
        <p><strong>Tipo de Cuenta:</strong> ${tipoCuenta}</p>
        <p>
          <strong>${cuentaLabel}</strong> 
          <span id="cuentaText">${numeroCuenta}</span>
        </p>
      `,
        icon: 'info',
        confirmButtonText: 'Cerrar',
        customClass: {
          popup: 'swal2-border-radius'
        }
      });
    });
  });

  // -------------------------------------------------------------------------------------------------------------------
  // EDITAR PROVEEDOR
  let valoresOriginales = {};

  function actualizarLabelEditCuenta(tipoCuentaTexto) {
    const numeroCuenta = document.getElementById('editNumeroCuenta');
    const labelCuenta = document.getElementById('editLabelCuenta');

    let maxLength = 0;
    let labelTexto = "Número de Cuenta";

    if (tipoCuentaTexto === 'Banco de la Nacion') {
      maxLength = 20;
      labelTexto = "N° de Cuenta";
    } else if (tipoCuentaTexto === 'BCP' || tipoCuentaTexto === 'BBVA') {
      maxLength = 12;
      labelTexto = "N° de Cuenta";
    } else if (tipoCuentaTexto === 'Interbank') {
      maxLength = 13;
      labelTexto = "N° de Cuenta";
    } else if (tipoCuentaTexto === 'Yape/Plin') {
      maxLength = 9;
      labelTexto = "N° de Celular";
    } else if (tipoCuentaTexto === 'Otro (CCI)') {
      maxLength = 20;
      labelTexto = "N° de CCI";
    }

    numeroCuenta.setAttribute('maxlength', maxLength);
    labelCuenta.textContent = labelTexto;
  }

  document.querySelectorAll(".btn.edit").forEach((button) => {
    button.addEventListener("click", () => {
      const row = button.closest("tr");
      const idProveedor = row.dataset.id;

      const ruc = row.children[1].textContent.trim();
      const nombre = row.children[2].textContent.trim();
      const estado = row.children[3].textContent.trim();
      const direccionFiscal = row.children[4].textContent.trim();
      const telefono = row.children[5].textContent.trim();

      const domicilioAlterna = row.querySelector(".direccionAlterna-hidden").value;
      const tipoCuenta = row.querySelector(".tipo-hidden").value;
      const numeroCuenta = row.querySelector(".cuenta-hidden").value;

      // Guardar valores originales
      valoresOriginales = {
        telefono,
        domicilioAlterna
      };

      // Llenar campos del modal
      document.getElementById("providerId").value = idProveedor;
      document.getElementById("editRuc").value = ruc;
      document.getElementById("editName").value = nombre;
      document.getElementById("editEstado").value = estado;
      document.getElementById("editDireccion").value = direccionFiscal;
      document.getElementById("editTelefono").value = telefono;
      document.getElementById("editDireccionAlterna").value = domicilioAlterna;
      document.getElementById("editTipoCuenta").value = tipoCuenta;
      document.getElementById("editNumeroCuenta").value = numeroCuenta;

      // Actualizar label y maxlength en base al tipo de cuenta
      actualizarLabelEditCuenta(tipoCuenta);

      // Mostrar el modal
      document.getElementById("providerModal").style.display = "block";
    });
  });

// Interceptar envío del formulario
  document.getElementById("providerForm").addEventListener("submit", function (e) {
    const nuevoTelefono = document.getElementById("editTelefono").value.trim();
    const nuevaDireccion = document.getElementById("editDireccionAlterna").value.trim();

    // Verificar si los campos están vacíos
    if (nuevoTelefono === "") {
      e.preventDefault(); // Detener el envío

      Swal.fire({
        title: "Campos Vacíos",
        text: "El telefono no puede estar vacio.",
        icon: "warning",
        confirmButtonText: "Aceptar"
      });

      return;
    }

    // Comparar con los valores originales
    if (
        nuevoTelefono === valoresOriginales.telefono &&
        nuevaDireccion === valoresOriginales.domicilioAlterna
    ) {
      e.preventDefault(); // Detener el envío

      Swal.fire({
        title: "Sin cambios detectados",
        text: "No se editó ningún campo.",
        icon: "warning",
        confirmButtonText: "Aceptar"
      });

      return;
    }

    // Si se ha editado algo, se envía el formulario normalmente
  });
});

// -------------------------------------------------------------------------------------------------------------------
// Buscar proveedores por razón social o RUC y/o por estado
document.addEventListener("DOMContentLoaded", () => {
  function applyFilters() {
    const searchValue = document.querySelector(".search-provider").value.toLowerCase().trim();
    const statusFilterValue = document.getElementById("statusFilter").value.toLowerCase().trim();
    const rows = document.querySelectorAll(".provider-table tbody tr");

    rows.forEach(row => {
      const ruc = row.children[1].textContent.toLowerCase();
      const nombre = row.children[2].textContent.toLowerCase();
      const estado = row.querySelector(".proveedor-estado").textContent.toLowerCase().trim();

      const matchesSearch = !searchValue || ruc.includes(searchValue) || nombre.includes(searchValue);
      const matchesStatus = !statusFilterValue || estado === statusFilterValue;

      // Mostrar si cumple con cualquiera de los filtros activos
      if (matchesSearch && matchesStatus) {
        row.style.display = "";
      } else {
        row.style.display = "none";
      }
    });
  }

  // Eventos para activar filtro en tiempo real
  document.querySelector(".search-provider").addEventListener("input", applyFilters);
  document.getElementById("statusFilter").addEventListener("change", applyFilters);
});

// -------------------------------------------------------------------------------------------------------------------
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

/*  window.addEventListener("click", (event) => {
    if (event.target === addModal) {
      addModal.style.display = "none";
    }
  });*/

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


// Acción Activar o Desactivar Proveedor
document.querySelectorAll(".btn-toggle-status").forEach(btn => {
  btn.addEventListener("click", function () {
    const row = this.closest("tr");
    const idProveedor = row.dataset.id;
    const esActivo = row.dataset.activo === "true";
    const accion = esActivo ? "inactivate" : "activate";

    Swal.fire({
      title: `¿Estás seguro de ${esActivo ? "desactivar" : "activar"} este proveedor?`,
      icon: "question",
      showCancelButton: true,
      confirmButtonText: esActivo ? "Sí, desactivar" : "Sí, activar",
      cancelButtonText: "Cancelar"
    }).then((result) => {
      if (result.isConfirmed) {
        fetch(`${location.origin}/BeltCoreCG_war_exploded/ProveedorController`, {
          method: "POST",
          headers: {
            "Content-Type": "application/x-www-form-urlencoded"
          },
          body: `accion=${accion}&idProveedor=${encodeURIComponent(idProveedor)}`
        })
            .then(response => {
              if (response.ok) {
                Swal.fire("¡Éxito!", `Proveedor ${esActivo ? "desactivado" : "activado"} correctamente.`, "success")
                    .then(() => location.reload());
              } else {
                Swal.fire("Error", "No se pudo completar la acción.", "error");
              }
            })
            .catch(() => {
              Swal.fire("Error", "Ocurrió un error en la solicitud.", "error");
            });
      }
    });
  });
});

// --------------------------------------------------------------------------------------------------------------------
// PAGINACION
document.addEventListener("DOMContentLoaded", function () {
  const table = document.querySelector(".provider-table tbody");
  const rows = Array.from(table.querySelectorAll("tr"));
  const rowsPerPage = 5;
  let currentPage = 1;

  function displayTablePage(page) {
    const start = (page - 1) * rowsPerPage;
    const end = start + rowsPerPage;

    rows.forEach((row, index) => {
      row.style.display = index >= start && index < end ? "" : "none";
    });

    renderPagination();
  }

  function renderPagination() {
    const totalPages = Math.ceil(rows.length / rowsPerPage);
    const pagination = document.getElementById("pagination");
    pagination.innerHTML = "";

    const prevBtn = document.createElement("button");
    prevBtn.textContent = "Anterior";
    prevBtn.disabled = currentPage === 1;
    prevBtn.className = currentPage === 1 ? "disabled" : "";
    prevBtn.onclick = () => {
      if (currentPage > 1) {
        currentPage--;
        displayTablePage(currentPage);
      }
    };
    pagination.appendChild(prevBtn);

    for (let i = 1; i <= totalPages; i++) {
      const pageBtn = document.createElement("button");
      pageBtn.textContent = i;
      pageBtn.className = i === currentPage ? "active" : "";
      pageBtn.onclick = () => {
        currentPage = i;
        displayTablePage(currentPage);
      };
      pagination.appendChild(pageBtn);
    }

    const nextBtn = document.createElement("button");
    nextBtn.textContent = "Siguiente";
    nextBtn.disabled = currentPage === totalPages;
    nextBtn.className = currentPage === totalPages ? "disabled" : "";
    nextBtn.onclick = () => {
      if (currentPage < totalPages) {
        currentPage++;
        displayTablePage(currentPage);
      }
    };
    pagination.appendChild(nextBtn);
  }

  displayTablePage(currentPage);
});
