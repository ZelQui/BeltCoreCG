// session-handler.js
(function () {
    const originalFetch = window.fetch;

    window.fetch = async function (...args) {
        const response = await originalFetch(...args);

        if (response.status === 401) {
            console.warn("Sesión expirada. Redirigiendo al login...");
            window.location.href = 'index.jsp';
        }
        return response;
    };
})();