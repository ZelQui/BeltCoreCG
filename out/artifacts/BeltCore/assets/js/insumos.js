document.addEventListener("DOMContentLoaded", function () {
  // ----- Modal Agregar -----
  const btnProducto = document.getElementById("btnProducto");
  const modalProducto = document.getElementById("modal-producto");
  const cerrarProducto = modalProducto.querySelector(".cerrar-modal");
  const cancelarProducto = modalProducto.querySelector(".cancelar");
  const formProducto = document.getElementById("form-producto");

  btnProducto.addEventListener("click", () => {
    modalProducto.classList.remove("oculto");
    formProducto.reset();
  });

  cerrarProducto.addEventListener("click", () => modalProducto.classList.add("oculto"));
  cancelarProducto.addEventListener("click", () => modalProducto.classList.add("oculto"));

  // ----- Modal Editar -----
  const modalEditar = document.getElementById("modal-editar-producto");
  const formEditar = document.getElementById("form-editar-producto");
  const cerrarEditar = modalEditar.querySelector(".cerrar-modal");
  const cancelarEditar = modalEditar.querySelector(".cancelar");

  let filaActual = null;

  function attachEditHandlers() {
    document.querySelectorAll(".btn.edit").forEach(btn => {
      btn.removeEventListener("click", handleEditClick);
      btn.addEventListener("click", handleEditClick);
    });
  }

  function handleEditClick() {
    filaActual = this.closest("tr");

    document.getElementById("editar-nombre").value = filaActual.cells[1].textContent;
    document.getElementById("editar-descripcion").value = filaActual.cells[2].textContent;
    document.getElementById("editar-precio").value = filaActual.cells[4].textContent.replace("S/ ", "");
    document.getElementById("editar-unidad").value = filaActual.cells[5].textContent;

    modalEditar.classList.remove("oculto");
  }

  cerrarEditar.addEventListener("click", () => modalEditar.classList.add("oculto"));
  cancelarEditar.addEventListener("click", () => modalEditar.classList.add("oculto"));

  formEditar.addEventListener("submit", function (e) {
    e.preventDefault();
    if (!filaActual) return;

    filaActual.cells[1].textContent = document.getElementById("editar-nombre").value;
    filaActual.cells[2].textContent = document.getElementById("editar-descripcion").value;
    filaActual.cells[4].textContent = `S/ ${parseFloat(document.getElementById("editar-precio").value).toFixed(2)}`;
    filaActual.cells[5].textContent = document.getElementById("editar-unidad").value;

    modalEditar.classList.add("oculto");
    filaActual = null;
  });

  // ----- Botón Ver con SweetAlert2 -----
  function attachViewHandlers() {
    document.querySelectorAll(".btn.view").forEach(btn => {
      btn.removeEventListener("click", handleViewClick);
      btn.addEventListener("click", handleViewClick);
    });
  }

  function handleViewClick() {
    const fila = this.closest("tr");

    const id = fila.cells[0].textContent;
    const nombre = fila.cells[1].textContent;
    const descripcion = fila.cells[2].textContent;
    const stock = fila.cells[3].textContent;
    const precio = fila.cells[4].textContent;
    const unidad = fila.cells[5].textContent;
    const estado = fila.querySelector(".role-badge").textContent;

    Swal.fire({
      title: `Detalle del Insumo`,
      html: `

        <p><strong>Nombre:</strong> ${nombre}</p>
        <p><strong>Descripción:</strong> ${descripcion}</p>
        <p><strong>Stock:</strong> ${stock}</p>
        <p><strong>Precio:</strong> ${precio}</p>
        <p><strong>Unidad:</strong> ${unidad}</p>
        <p><strong>Estado:</strong> ${estado}</p>
      `,
      icon: 'info',
      confirmButtonText: 'Cerrar'
    });
  }

  // ----- Activar / Desactivar -----
  function attachActivateHandlers() {
    document.querySelectorAll(".btn.activate").forEach(btn => {
      btn.removeEventListener("click", handleActivateClick);
      btn.addEventListener("click", handleActivateClick);
    });
  }

  function handleActivateClick() {
    const row = this.closest("tr");
    const estado = row.querySelector(".role-badge");
    estado.textContent = "Disponible";
    estado.classList.remove("role-red");
    estado.classList.add("role-green");
    this.outerHTML = `<button class="btn deactivate" title="Desactivar"><i class="fas fa-ban"></i></button>`;
    attachDeactivateHandlers();
  }

  function attachDeactivateHandlers() {
    document.querySelectorAll(".btn.deactivate").forEach(btn => {
      btn.removeEventListener("click", handleDeactivateClick);
      btn.addEventListener("click", handleDeactivateClick);
    });
  }

  function handleDeactivateClick() {
    const row = this.closest("tr");
    const estado = row.querySelector(".role-badge");
    estado.textContent = "Agotado";
    estado.classList.remove("role-green");
    estado.classList.add("role-red");
    this.outerHTML = `<button class="btn activate" title="Activar"><i class="fas fa-check-circle"></i></button>`;
    attachActivateHandlers();
  }

  // ----- Agregar nuevo insumo -----
  formProducto.addEventListener("submit", function (e) {
    e.preventDefault();

    const nombre = document.getElementById("nuevo-nombre").value;
    const precio = parseFloat(document.getElementById("nuevo-precio").value).toFixed(2);
    const unidad = document.getElementById("nuevo-unidad").value;
    const descripcion = document.getElementById("nuevo-descripcion").value;

    if (!nombre || !precio || !unidad || !descripcion) {
      alert("Por favor, complete todos los campos.");
      return;
    }

    const tbody = document.querySelector(".user-table tbody");
    const nuevoId = tbody.rows.length + 1;

    const nuevaFila = document.createElement("tr");
    nuevaFila.innerHTML = `
      <td>${nuevoId}</td>
      <td>${nombre}</td>
      <td>${descripcion}</td>
      <td>0</td>
      <td>S/ ${precio}</td>
      <td>${unidad}</td>
      <td><span class="role-badge role-green">Disponible</span></td>
      <td>
        <button class="btn view" title="Ver"><i class="fas fa-eye"></i></button>
        <button class="btn edit" title="Editar"><i class="fas fa-edit"></i></button>
        <button class="btn deactivate" title="Desactivar"><i class="fas fa-ban"></i></button>
      </td>
    `;

    tbody.appendChild(nuevaFila);

    attachEditHandlers();
    attachViewHandlers();
    attachDeactivateHandlers();
    attachActivateHandlers();

    modalProducto.classList.add("oculto");
  });

  // ----- Filtros -----
  // ----- Filtrar Productos -----
  function applyFilters() {
    const roleFilter = document.getElementById("roleFilter")?.value.toLowerCase() || "";
    const statusFilter = document.getElementById("statusFilter").value.toLowerCase();
    const searchText = document.querySelector(".search-provider").value.toLowerCase();

    document.querySelectorAll(".user-table tbody tr").forEach(row => {
      const unidad = row.cells[5].textContent.trim().toLowerCase(); // Corregido: se utiliza la columna 5 para la unidad
      const estado = row.querySelector(".role-badge").textContent.toLowerCase();
      const nombre = row.cells[1].textContent.toLowerCase();

      const matchCategoria = !roleFilter || unidad === roleFilter;
      const matchEstado = !statusFilter || estado === statusFilter;
      const matchNombre = !searchText || nombre.includes(searchText);

      row.style.display = matchCategoria && matchEstado && matchNombre ? "" : "none";
    });
  }

  document.getElementById("roleFilter")?.addEventListener("change", applyFilters);
  document.getElementById("statusFilter").addEventListener("change", applyFilters);
  document.querySelector(".search-provider").addEventListener("input", applyFilters);
  document.getElementById("applyFilters").addEventListener("click", applyFilters);

  attachEditHandlers();
  attachViewHandlers();
  attachActivateHandlers();
  attachDeactivateHandlers();
});
