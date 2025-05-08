package development.team.Controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/app/*")
public class MenuController extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String path = request.getPathInfo(); // ej: "/inicio"
        if (path == null || path.equals("/")) {
            path = "/inicio";
        }

        String vista = path.substring(1) + ".jsp"; // ej: "inicio.jsp"
        String rutaVista = "/views/" + vista;

        if (getServletContext().getResource(rutaVista) != null) {
            request.setAttribute("contenido", vista); // ej: "inicio.jsp"
            request.getRequestDispatcher("/views/menu.jsp").forward(request, response);
        } else {
            // Establece el estado 404
            //response.setStatus(404);
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            // Redirige internamente a la página 404.jsp
            request.getRequestDispatcher("/views/404.jsp").forward(request, response);
        }
    }
}