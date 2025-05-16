package development.team.Controllers;

import development.team.DAO.ModuloDAO;
import development.team.Models.Modulo;
import development.team.Models.Usuario;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@WebServlet("/app/*")
public class MenuController extends HttpServlet {
    private static final String VIEWS_BASE     = "/views/";
    private static final String MENU_PAGE      = VIEWS_BASE + "menu.jsp";
    private static final String NOT_FOUND_PAGE = VIEWS_BASE + "404.jsp";
    private static final String FORBIDDEN_PAGE = VIEWS_BASE + "403.jsp";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // 1. Validar sesión
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("usuario") == null) {
            response.sendRedirect(request.getContextPath() + "/");
            return;
        }

        // 2. Determinar ruta solicitada
        String path = request.getPathInfo(); // ej: "/inicio"
        if (path == null || path.equals("/")) {
            path = "/inicio";
        }

        String nombreBase = path.substring(1);          // "inicio"
        String vista      = nombreBase + ".jsp";        // "inicio.jsp"
        String jspPath    = VIEWS_BASE + vista;         // "/views/inicio.jsp"

        // 3. Verificar si la vista existe físicamente
        try (InputStream is = getServletContext().getResourceAsStream(jspPath)) {
            if (is == null) {
                response.setStatus(HttpServletResponse.SC_NOT_FOUND);
                request.getRequestDispatcher(NOT_FOUND_PAGE).forward(request, response);
                return;
            }
        }

        // 4. Verificar si el módulo solicitado está permitido
        Usuario user = (Usuario) session.getAttribute("usuario");
        int idRol = user.getRol().getIdRol();
        ModuloDAO moduloDAO = new ModuloDAO();
        List<Modulo> modulosUsuario = moduloDAO.obtenerModulosPorRol(idRol);
        session.setAttribute("modulos_usuario", modulosUsuario);

        boolean permitido = false;
        if (modulosUsuario != null) {
            for (Modulo m : modulosUsuario) {
                String rutaModulo = m.getRuta();
                if (rutaModulo != null &&
                        (rutaModulo.equalsIgnoreCase(vista) || rutaModulo.equalsIgnoreCase(nombreBase))) {
                    permitido = true;
                    break;
                }
            }
        }

        if (!permitido) {
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
            request.getRequestDispatcher(FORBIDDEN_PAGE).forward(request, response);
            return;
        }

        // 5. Vista válida y acceso permitido → mostrar en plantilla
        request.setAttribute("contenido", vista);
        request.getRequestDispatcher(MENU_PAGE).forward(request, response);
    }
}