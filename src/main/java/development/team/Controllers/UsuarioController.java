package development.team.Controllers;

import com.google.gson.Gson;
import development.team.DAO.RolDAO;
import development.team.DAO.UsuarioDAO;
import development.team.DTO.UsuarioRolDTO;
import development.team.Models.Rol;
import development.team.Models.Usuario;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(name = "user", urlPatterns = {"/user"})
public class UsuarioController extends HttpServlet {

    UsuarioDAO userdao = new UsuarioDAO();
    Usuario user = new Usuario();
    UsuarioRolDTO userolDTO = new UsuarioRolDTO();

    Rol rol = new Rol();
    RolDAO roldao = new RolDAO();
    int IdUsuario = 0;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String accion = req.getParameter("accion");

        if ("get".equals(accion)) {
            IdUsuario = Integer.parseInt(req.getParameter("idUsuario"));
            userolDTO = userdao.obtenerUsuarioPorId(IdUsuario);
            System.out.println(userolDTO.toString());

            resp.setContentType("application/json");
            resp.setCharacterEncoding("UTF-8");
            PrintWriter out = resp.getWriter();
            out.print(new Gson().toJson(userolDTO));
            out.flush();
        }
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    String accion = req.getParameter("accion");

        switch (accion) {
            case "Listar":
                List<UsuarioRolDTO> lista = userdao.obtenerTodosUsuarios();
                req.setAttribute("usuarios", lista);
                break;
            case "Registrar":
                //Para validar datos de inicio de sesión
                String dni = req.getParameter("dni"); //falta en DAO y Model
                String nombre = req.getParameter("fullName");
                String ApePaterno = req.getParameter("ApePaterno");
                String ApeMaterno = req.getParameter("ApeMaterno");
                String telefono = req.getParameter("telefono");
                String correo = req.getParameter("email");
                String password = "123456";
                int idRol = Integer.parseInt(req.getParameter("role"));

                if (userdao.existeUsuario(correo)) {
                    System.out.println("El correo ya está registrado.");
                } else {
                    //AGREGAR A USUARIOS
                    user.setNombre(nombre);
                    user.setApellidoPaterno(ApePaterno);
                    user.setApellidoMaterno(ApeMaterno);
                    user.setTelefono(telefono);
                    user.setCorreo(correo);
                    user.setContrasena(password);
                    //AGREGAR EL ROL ASIGNADO
                    rol = roldao.obtenerRolPorId(idRol);
                    user.setRol(rol);
                    user.setEstado(1);
                    int IdUsuario = userdao.registrarUsuario(user);
                    user.setIdUsuario(IdUsuario);
                    System.out.printf("Se ha registrado el User con ID: " + IdUsuario);
                }
                resp.sendRedirect(req.getContextPath() + "/app/usuarios");
                return;
            case "Actualizar":
                System.out.println("Entra a Actualizar");
                int usuarioId = Integer.parseInt(req.getParameter("idUsuario"));
                String nombreEdit = req.getParameter("fullNameEdit");
                String correoEdit = req.getParameter("emailEdit");
                int idRolEdit = Integer.parseInt(req.getParameter("roleEdit"));

                //ACTUALIZAR USUARIO
                Usuario userEdit = new Usuario();
                userEdit.setIdUsuario(usuarioId);
                userEdit.setNombre(nombreEdit);
                userEdit.setCorreo(correoEdit);
                //ACTUALIZAR EL ROL ASIGNADO
                Rol rolEdit = roldao.obtenerRolPorId(idRolEdit);
                userEdit.setRol(rolEdit);

                if (userdao.actualizarUsuario(userEdit)){
                    System.out.printf("Se ha actualizado correctamente el User con ID: " + usuarioId);
                } else {
                    System.out.printf("Ocurrió un error al actualizar el User con ID: " + usuarioId);
                }

                resp.sendRedirect(req.getContextPath() + "/app/usuarios");
                return;
            case "restartPass":
                int idUser = Integer.parseInt(req.getParameter("idUsuario"));

                //RESTABLECER A PREDETERMINADO
                if(userdao.resetearContrasena(idUser)){
                    System.out.println("Se ha resetado la contraseña del User con ID: " + idUser);
                }else {
                    System.out.printf("Ocurrió un error al restablecer la contrasena");
                }

                resp.sendRedirect(req.getContextPath() + "/app/usuarios");
                return;
            /*case "delete":
                int idUser = Integer.parseInt(req.getParameter("idUser"));
                userdao.updateStatus(idUser, "Inactivo");
                System.out.println("Usuario inactivado: ID: "+idUser);
                resp.sendRedirect("menu.jsp?view=usuarios");
                break;
            case "activate":
                int idU = Integer.parseInt(req.getParameter("idUser"));
                userdao.updateStatus(idU, "Activo");
                System.out.println("Usuario activado: ID: "+idU);
                resp.sendRedirect("menu.jsp?view=usuarios");
                break;
            case "updatePassword": //CAMBIAR CONTRASEÑA A NUEVA
                String newPassword = req.getParameter("newpassword");
                HttpSession sessionActual = req.getSession();
                User userLogin =  (User) sessionActual.getAttribute("usuario");
                userLogin = userdao.updateUserPassword(userLogin, newPassword);
                sessionActual.setAttribute("usuario", userLogin);
                resp.sendRedirect("menu.jsp");
                break;*/
            default:
                break;
        }
    }
}

