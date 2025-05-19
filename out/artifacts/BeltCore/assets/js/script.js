// Función para cargar el contenido sin actualizar el sidebar
function cargarPagina(pagina) {
    fetch(pagina)
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
                // Página de inicio (gráfica)
                if (pagina === "/views/menu.jsp") {
                    iniciarGrafica();
                }

                // Página de productos
                if (pagina === "productos.jsp") {
                    inicializarEventosProductos();
                }

                // Buscar y cargar scripts externos/inlines que vengan con el HTML
                const scripts = document.getElementById('contenido').querySelectorAll('script');
                scripts.forEach(script => {
                    const nuevoScript = document.createElement('script');

                    if (script.src) {
                        nuevoScript.src = script.src;
                    } else if (script.textContent) {
                        nuevoScript.textContent = script.textContent;
                    }

                    document.head.appendChild(nuevoScript);
                });
            }, 100);
        })
        .catch(error => {
            console.error('Error:', error);
            document.getElementById('contenido').innerHTML = '<div class="alert alert-danger">No se pudo cargar el contenido.</div>';
        });
}
