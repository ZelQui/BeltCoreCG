package development.team.Controllers;

import com.google.gson.Gson;
import development.team.DAO.RolDAO;
import development.team.Models.Rol;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(name = "rol", urlPatterns = {"/rol"})
public class RolController extends HttpServlet {

    Rol rol = new Rol();
    RolDAO roldao = new RolDAO();
    int IdRol = 0;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String accion = req.getParameter("accion");

        if ("get".equals(accion)) {
            IdRol = Integer.parseInt(req.getParameter("IdRol"));
            Rol rolbusqueda = roldao.obtenerRolPorId(IdRol);
            System.out.println(rolbusqueda.toString());

            resp.setContentType("application/json");
            resp.setCharacterEncoding("UTF-8");
            PrintWriter out = resp.getWriter();
            out.print(new Gson().toJson(rolbusqueda));
            out.flush();
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String accion = req.getParameter("accion");

        switch (accion) {
            case "Listar":
                List<Rol> lista = roldao.obtenerRols();
                req.setAttribute("roles", lista);
                break;
            case "Registrar":
                //Para validar datos de inicio de sesión
                String nombre_rol = req.getParameter("nombre_rol"); //falta en DAO y Model
                String descripcion = req.getParameter("descripcion");

                //AGREGAR A USUARIOS
                rol.setNombreRol(nombre_rol);
                rol.setDescripcion(descripcion);
                //AGREGAR EL ROL ASIGNADO
                roldao.insertarRol(rol);
                System.out.printf("Se ha registrado el Rol: " + rol.getNombreRol());

                resp.sendRedirect(req.getContextPath() + "/app/roles");
                return;
            case "Actualizar":
                IdRol = Integer.parseInt(req.getParameter("roleId"));
                String roleName = req.getParameter("roleName"); //falta en DAO y Model
                String roleDescription = req.getParameter("roleDescription");

                //ACTUALIZAR EL ROL ASIGNADO
                Rol rolEdit = roldao.obtenerRolPorId(IdRol);
                rolEdit.setNombreRol(roleName);
                rolEdit.setDescripcion(roleDescription);

                if (roldao.actualizarRol(rolEdit)){
                    System.out.printf("Se ha actualizado correctamente el Rol con ID: " + IdRol);
                } else {
                    System.out.printf("Ocurrió un error al actualizar el Rol con ID: " + IdRol);
                }
                resp.sendRedirect(req.getContextPath() + "/app/roles");
                return;
            default:
                break;
        }
    }
}
