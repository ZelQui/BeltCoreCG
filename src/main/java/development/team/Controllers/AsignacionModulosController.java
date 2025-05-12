package development.team.Controllers;

import development.team.DAO.ModuloDAO;
import development.team.Models.Modulo;
import development.team.Models.Rol;
import development.team.DAO.RolDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet("/AsignacionModulosController")
public class AsignacionModulosController extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int idRol = Integer.parseInt(request.getParameter("idRol"));

        Rol rolSeleccionado = RolDAO.obtenerRolPorId(idRol);
        List<Modulo> modulos = ModuloDAO.obtenerModulos();
        List<Integer> modulosAsignados = RolDAO.obtenerModulosAsignados(idRol); // IDs asignados

        request.setAttribute("rolSeleccionado", rolSeleccionado);
        request.setAttribute("modulos", modulos);
        request.setAttribute("modulosAsignados", modulosAsignados);

        //response.sendRedirect(request.getContextPath() + "/app/asignarModulos");
        request.getRequestDispatcher("/app/asignarModulos").forward(request, response);
    }
}

