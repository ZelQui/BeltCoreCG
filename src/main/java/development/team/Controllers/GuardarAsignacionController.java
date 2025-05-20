package development.team.Controllers;

import development.team.DAO.RolDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "GuardarAsignacionController", urlPatterns = {"/GuardarAsignacionController"})
public class GuardarAsignacionController extends HttpServlet{
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // No hace nada y retorna un estado 204 (sin contenido)
        response.setStatus(HttpServletResponse.SC_NO_CONTENT);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("usuario") == null) {
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED);
            return;
        }
        int idRol = Integer.parseInt(request.getParameter("idRol"));
        String[] modulosSeleccionados = request.getParameterValues("modulos"); // Puede ser null si no selecciona nada

        // Lógica para guardar
        if (modulosSeleccionados != null) {
            List<Integer> idsModulos = new ArrayList<>();
            for (String idModuloStr : modulosSeleccionados) {
                idsModulos.add(Integer.parseInt(idModuloStr));
            }

            // Si es rol 1, siempre agregar el módulo 23 si no está ya
            if (idRol == 1 && !idsModulos.contains(23)) {
                idsModulos.add(23);
            }

            // Aquí llamas al DAO para guardar
            RolDAO dao = new RolDAO();
            dao.actualizarAsignacionModulos(idRol, idsModulos);
        }

        response.sendRedirect(request.getContextPath() + "/app/roles"); // Redirige luego de guardar
    }

}

