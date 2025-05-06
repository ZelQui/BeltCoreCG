// Función para cargar el contenido sin actualizar el sidebar
function cargarPagina(pagina) {
  fetch(pagina)  // Asegúrate de que 'pagina' ya contiene la ruta correcta (por ejemplo: 'html/loquesea.jsp')
      .then(response => {
          if (!response.ok) {
              throw new Error('Error al cargar la página: ' + pagina);
          }
          return response.text();
      })
      .then(html => {
          document.getElementById('contenido').innerHTML = html;

          // Esperar a que el DOM del nuevo contenido esté disponible
          setTimeout(() => {
              if (pagina === "/views/menu.jsp") {
                  iniciarGrafica(); // Ejecutar solo si es la página de inicio
              }
              
              // Si es la página de productos, inicializar sus eventos
              if (pagina === "productos.jsp") {
                  inicializarEventosProductos();
              }
              
              // Si es la página de usuarios, inicializar sus eventos
              if (pagina === "usuarios.jsp" || pagina.includes("usuarios.jsp") || pagina.includes("/usuarios")) {
                  if (typeof window.initializeUserFunctions === 'function') {
                      window.initializeUserFunctions();
                      console.log('Funciones de usuarios inicializadas después de cargar la página');
                  } else {
                      console.error('La función initializeUserFunctions no está disponible');
                  }
              }
              
              // Buscar y cargar scripts externos
              const scripts = document.getElementById('contenido').querySelectorAll('script');
              scripts.forEach(script => {
                  if (script.src) {
                      const scriptNuevo = document.createElement('script');
                      scriptNuevo.src = script.src;
                      document.head.appendChild(scriptNuevo);
                  } else if (script.textContent) {
                      // También ejecutar scripts inline
                      const scriptNuevo = document.createElement('script');
                      scriptNuevo.textContent = script.textContent;
                      document.head.appendChild(scriptNuevo);
                  }
              });
          }, 100);
      })
      .catch(error => {
          console.error('Error:', error);
          document.getElementById('contenido').innerHTML = '<div class="alert alert-danger">No se pudo cargar el contenido.</div>';
      });
}

// Función para inicializar eventos de productos
function inicializarEventosProductos() {
  const btnProducto = document.getElementById('btnProducto');
  const btnCategoria = document.getElementById('btnCategoria');

  const modalProducto = document.getElementById('modal-producto');
  const modalCategoria = document.getElementById('modal-categoria');
  
  const formProducto = document.getElementById('form-producto');
  const formCategoria = document.getElementById('form-categoria');
  
  // Botones cerrar modal (X)
  const cerrarBotones = document.querySelectorAll('.cerrar-modal');
  
  // Verificar si los elementos existen antes de agregar event listeners
  if (!btnProducto || !btnCategoria || !modalProducto || !modalCategoria || !formProducto || !formCategoria) {
      console.error('No se encontraron todos los elementos necesarios para inicializar eventos de productos');
      return;
  }
  
  // Función para ocultar todos los modales
  const ocultarModales = () => {
    modalProducto.classList.add('oculto');
    modalCategoria.classList.add('oculto');
  };

  // Mostrar modales
  btnProducto.addEventListener('click', () => {
    ocultarModales(); // Primero oculta todos
    modalProducto.classList.remove('oculto'); // Luego muestra el modal de producto
  });

  btnCategoria.addEventListener('click', () => {
    ocultarModales(); // Primero oculta todos
    modalCategoria.classList.remove('oculto'); // Luego muestra el modal de categoría
  });

  // Botones X para cerrar modales
  cerrarBotones.forEach(boton => {
    boton.addEventListener('click', ocultarModales);
  });

  // Botones cancelar
  const cancelarProducto = formProducto.querySelector('.cancelar');
  const cancelarCategoria = formCategoria.querySelector('.cancelar');

  if (cancelarProducto) {
      cancelarProducto.addEventListener('click', ocultarModales);
  }
  
  if (cancelarCategoria) {
      cancelarCategoria.addEventListener('click', ocultarModales);
  }

  // Cerrar modal al hacer clic fuera del contenido
  window.addEventListener('click', (e) => {
    if (e.target === modalProducto) {
      modalProducto.classList.add('oculto');
    }
    if (e.target === modalCategoria) {
      modalCategoria.classList.add('oculto');
    }
  });

  // Opcional: ocultar modales al enviar formularios
  formProducto.addEventListener('submit', (e) => {
    e.preventDefault();
    alert('Producto agregado');
    ocultarModales();
  });

  formCategoria.addEventListener('submit', (e) => {
    e.preventDefault();
    alert('Categoría agregada');
    ocultarModales();
  });
  
  console.log('Eventos de productos inicializados correctamente');
}

// Al cargar la página principal, inicializar eventos de navegación
document.addEventListener('DOMContentLoaded', function () {
  // Seleccionar todos los elementos con clase 'nav-item'
  const navItems = document.querySelectorAll('.nav-item');

  navItems.forEach(item => {
      item.addEventListener('click', function () {
          // Quitar 'active' de todos
          navItems.forEach(i => i.classList.remove('active'));
          // Agregar 'active' solo al que se hizo clic
          this.classList.add('active');
      });
  });
});