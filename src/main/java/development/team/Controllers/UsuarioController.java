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
                // Obtener parámetros del formulario
                String dni = req.getParameter("dni");
                String nombres = req.getParameter("fullName");
                String apellidoPaterno = req.getParameter("ApePaterno");
                String apellidoMaterno = req.getParameter("ApeMaterno");
                String telefono = req.getParameter("telefono");
                String correo = req.getParameter("email");
                int idRol = Integer.parseInt(req.getParameter("role"));

                // Generar login automáticamente: inicial + apellido + últimos 3 dígitos del DNI
                String userLogin = generarUserLogin(nombres, apellidoPaterno, dni);

                // Validar si ya existe el usuario
                if (userdao.existeUsuario(correo)) {
                    System.out.println("El correo ya está registrado.");
                } else {
                    // Crear usuario y asignar sus datos
                    Usuario usuario = new Usuario();
                    usuario.setDni(dni);
                    usuario.setNombres(nombres);
                    usuario.setApellidoPaterno(apellidoPaterno);
                    usuario.setApellidoMaterno(apellidoMaterno);
                    usuario.setTelefono(telefono);
                    usuario.setCorreo(correo);
                    usuario.setUserLogin(userLogin);
                    usuario.setContrasena(dni); // Puedes encriptar esto si aún no lo haces
                    usuario.setEstado(1);

                    // Asignar rol
                    Rol rol = roldao.obtenerRolPorId(idRol);
                    usuario.setRol(rol);
                    // Registrar usuario
                    int idUsuario = userdao.registrarUsuario(usuario);
                    usuario.setIdUsuario(idUsuario);

                    System.out.println("Se ha registrado el usuario con ID: " + idUsuario);
                }
                resp.sendRedirect(req.getContextPath() + "/app/usuarios");
                return;
            case "Actualizar":
                IdUsuario = Integer.parseInt(req.getParameter("idUsuario"));
                String telefonoEdit = req.getParameter("telefonoEdit");
                String correoEdit = req.getParameter("emailEdit");
                int idRolEdit = Integer.parseInt(req.getParameter("roleEdit"));

                //ACTUALIZAR USUARIO
                Usuario userEdit = new Usuario();
                userEdit.setIdUsuario(IdUsuario);
                userEdit.setTelefono(telefonoEdit);
                userEdit.setCorreo(correoEdit);
                //ACTUALIZAR EL ROL ASIGNADO
                Rol rolEdit = roldao.obtenerRolPorId(idRolEdit);
                userEdit.setRol(rolEdit);

                if (userdao.actualizarUsuario(userEdit)){
                    System.out.printf("Se ha actualizado correctamente el User con ID: " + IdUsuario);
                } else {
                    System.out.printf("Ocurrió un error al actualizar el User con ID: " + IdUsuario);
                }

                resp.sendRedirect(req.getContextPath() + "/app/usuarios");
                return;
            case "restartPass":
                System.out.println("[INFO] Entra a restartPass");

                try {
                    int idUsuario = Integer.parseInt(req.getParameter("idUsuario"));
                    System.out.println("[DEBUG] ID del usuario recibido: " + idUsuario);

                    UsuarioRolDTO userReset = userdao.obtenerUsuarioPorId(idUsuario);

                    if (userReset == null) {
                        System.out.println("[ERROR] No se encontró al usuario con ID: " + idUsuario);
                    } else {
                        boolean resultado = userdao.resetearContrasena(userReset);
                        if (resultado) {
                            System.out.println("[SUCCESS] Se ha reseteado la contraseña del usuario con ID: " + idUsuario);
                        } else {
                            System.out.println("[ERROR] Falló el restablecimiento de la contraseña.");
                        }
                    }
                } catch (NumberFormatException e) {
                    System.out.println("[ERROR] ID de usuario inválido: " + req.getParameter("idUsuario"));
                    e.printStackTrace();
                } catch (Exception e) {
                    System.out.println("[ERROR] Excepción inesperada en restartPass:");
                    e.printStackTrace();
                }

                resp.sendRedirect(req.getContextPath() + "/app/usuarios");
                return;
            case "inactivate":
                IdUsuario = Integer.parseInt(req.getParameter("idUsuario"));
                boolean inactivado = userdao.bloquearUsuario(IdUsuario);

                if (inactivado) {
                    System.out.println("Usuario inactivado: ID: " + IdUsuario);
                    resp.sendRedirect(req.getContextPath() + "/app/usuarios");
                } else {
                    System.err.println("No se pudo bloquear/inactivar el usuario: ID: " + IdUsuario);
                }
                break;
            case "activate":
                IdUsuario = Integer.parseInt(req.getParameter("idUsuario"));
                boolean activado = userdao.activarUsuario(IdUsuario);

                if (activado) {
                    System.out.println("Usuario activado: ID: " + IdUsuario);
                    resp.sendRedirect(req.getContextPath() + "/app/usuarios");
                } else {
                    System.err.println("No se pudo activar el usuario: ID: " + IdUsuario);
                }
                break;
            /*
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

    private String generarUserLogin(String nombres, String apellidoPaterno, String dni) {
        String inicial = nombres.trim().substring(0, 1).toLowerCase();
        String apellido = apellidoPaterno.trim().toLowerCase();
        String ultimosDni = dni.length() >= 3 ? dni.substring(dni.length() - 3) : dni;
        return inicial + apellido + ultimosDni;
    }
}

