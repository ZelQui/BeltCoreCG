package development.team.Controllers;

import development.team.DAO.ProduccionDAO;
import development.team.Models.Usuario;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.time.LocalDateTime;

@WebServlet(name = "produccion", urlPatterns = {"/produccion"})
public class ProduccionController extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(false);
        if (session == null || session.getAttribute("usuario") == null) {
            resp.sendError(HttpServletResponse.SC_UNAUTHORIZED);
            return;
        }
        String accion = req.getParameter("accion");

        switch (accion) {
            case "generar":
                int idProducto = Integer.parseInt(req.getParameter("producto_id"));
                LocalDateTime fechaProduccion = LocalDateTime.now();
                int cantidadPlaneada = Integer.parseInt(req.getParameter("cantidad"));
                Usuario usuario = (Usuario) session.getAttribute("usuario");
                int idUsuario = usuario.getIdUsuario();

                ProduccionDAO.insertarProduccion(idProducto, fechaProduccion, cantidadPlaneada, idUsuario);
                resp.sendRedirect(req.getContextPath() + "/app/producciones");
            return;
            default:
                break;
        }
    }
}
