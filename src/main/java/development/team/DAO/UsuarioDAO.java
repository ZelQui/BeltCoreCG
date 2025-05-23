package development.team.DAO;

import development.team.DTO.UsuarioRolDTO;
import development.team.Models.*;
import development.team.Utils.DataBaseUtil;
import org.mindrot.jbcrypt.BCrypt;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UsuarioDAO {
    private static final DataSource dataSource = DataBaseUtil.getDataSource();
    private static final RolDAO rolDAO = new RolDAO();

    public static boolean SettearUsuario( int usuarioId) {
        String setUsuarioSql = "SET @usuario_id = ?";

        try (Connection con = dataSource.getConnection();
             PreparedStatement psSetUsuario = con.prepareStatement(setUsuarioSql)) {
                psSetUsuario.setInt(1, usuarioId);
                psSetUsuario.execute();
                return true;
            }
            catch (SQLException e) {
                System.err.println("Error SQLException al registrar proveedor: " + e.getMessage());
            }
        return false;
    }

    public static boolean validarCredenciales(String userLogin, String contrasena) {
        String sql = "SELECT contrasena FROM usuarios WHERE user_login = ?";

        try (Connection con = dataSource.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, userLogin);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    String storedHash = rs.getString("contrasena");
                    storedHash = storedHash.trim();
                    return BCrypt.checkpw(contrasena, storedHash);
                }
            }
        } catch (SQLException e) {
            System.err.println("Error SQLException al validar las credenciales para el usuario: " + userLogin + " - " + e.getMessage());
        }
        return false;
    }

    public static boolean verificarEstadoActivo(Usuario usuario) {
        boolean estadoActivo = false;
        String sql = "SELECT estado FROM usuarios WHERE id_usuario = ?";

        try (Connection con = dataSource.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, usuario.getIdUsuario());
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                int estado = rs.getInt("estado");
                estadoActivo = (estado == 1);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return estadoActivo;
    }

    public static Usuario obtenerUsuarioSesion(String userLogin) {
        Usuario usuario = null;
        String sql = "SELECT id_usuario, dni, nombres, apellido_paterno, apellido_materno, telefono, " +
                " correo, user_login, contrasena, id_rol, estado FROM usuarios WHERE user_login = ?";

        try (Connection con = dataSource.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, userLogin);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    // Pasamos la MISMA conexión al rolDAO
                    Rol rol = rolDAO.obtenerRolPorId(rs.getInt("id_rol"), con);
                    usuario = new Usuario(
                            rs.getInt("id_usuario"),
                            rs.getString("dni"),
                            rs.getString("nombres"),
                            rs.getString("apellido_paterno"),
                            rs.getString("apellido_materno"),
                            rs.getString("telefono"),
                            rs.getString("correo"),
                            rs.getString("user_login"),
                            rs.getString("contrasena"),
                            rol,
                            rs.getInt("estado")
                    );
                    System.out.println(usuario.getNombres() + " inició sesión.");
                }
            }
        } catch (SQLException e) {
            System.err.println("Error SQLException al obtener el usuario: " + e.getMessage());
        }
        return usuario;
    }

    //METODOS CRUD
    //CREATE
    public static int registrarUsuario(Usuario usuario) {
        String sql = "INSERT INTO usuarios (dni, nombres, apellido_paterno, apellido_materno, " +
                "telefono, correo, user_login, contrasena, id_rol) VALUES (?,?,?,?,?,?,?,?,?)";
        int usuarioId = -1;

        try (Connection con = dataSource.getConnection();
             PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) { // Importante para obtener ID generado

            ps.setString(1, usuario.getDni());
            ps.setString(2, usuario.getNombres());
            ps.setString(3, usuario.getApellidoPaterno());
            ps.setString(4, usuario.getApellidoMaterno());
            ps.setString(5, usuario.getTelefono());
            ps.setString(6, usuario.getCorreo());
            ps.setString(7, usuario.getUserLogin());
            String hashedPassword = BCrypt.hashpw(usuario.getContrasena(), BCrypt.gensalt());
            ps.setString(8, hashedPassword);
            ps.setInt(9, usuario.getRol().getIdRol());

            int rowsAffected = ps.executeUpdate();
            if (rowsAffected > 0) {
                try (ResultSet rs = ps.getGeneratedKeys()) {
                    if (rs.next()) {
                        usuarioId = rs.getInt(1); // Obtener el ID generado
                    }
                }
            }
        } catch (SQLException e) {
            System.err.println("Error SQLException al registrar usuario: " + e.getMessage());
        }
        return usuarioId; //Retorna el ID que ha insertado
    }

    //READ
    public static List<UsuarioRolDTO> obtenerTodosUsuarios() {
        String sql = "SELECT u.id_usuario, u.dni, u.nombres, u.apellido_paterno, u.apellido_materno, " +
                "u.telefono, u.correo, u.user_login, u.estado, r.id_rol, r.nombre_rol, r.descripcion " +
                "FROM usuarios AS u " +
                "JOIN roles AS r ON u.id_rol = r.id_rol " +
                "ORDER BY u.estado DESC, u.id_rol ASC, u.nombres ASC;";
        List<UsuarioRolDTO> usuariosList = new ArrayList<>();

        try (Connection con = dataSource.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                usuariosList.add(new UsuarioRolDTO(
                        rs.getInt("id_usuario"),
                        rs.getString("dni"),
                        rs.getString("nombres"),
                        rs.getString("apellido_paterno"),
                        rs.getString("apellido_materno"),
                        rs.getString("telefono"),
                        rs.getString("correo"),
                        rs.getString("user_login"),
                        "***",
                        rs.getInt("id_rol"),
                        rs.getString("nombre_rol"),
                        rs.getString("descripcion"),
                        rs.getInt("estado")
                ));
            }

        } catch (SQLException e) {
            System.err.println("Error SQLException al obtener usuarios: " + e.getMessage());
        }

        return usuariosList;
    }

    public static UsuarioRolDTO obtenerUsuarioPorId(int usuarioId) {
        String sql = "SELECT u.id_usuario, u.dni, u.nombres, u.apellido_paterno, u.apellido_materno, " +
                " u.telefono, u.correo, u.user_login, u.estado, r.id_rol, r.nombre_rol, r.descripcion " +
                " FROM usuarios AS u " +
                " JOIN roles AS r ON u.id_rol = r.id_rol " +
                " WHERE u.id_usuario = ?";

        UsuarioRolDTO usuarioDTO = null;

        try (Connection con = dataSource.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, usuarioId);
            ResultSet rs = ps.executeQuery();
            System.out.println("[DEBUG] Ejecutando consulta para ID: " + usuarioId);

            if (rs.next()) {
                usuarioDTO = new UsuarioRolDTO(
                        rs.getInt("id_usuario"),
                        rs.getString("dni"),
                        rs.getString("nombres"),
                        rs.getString("apellido_paterno"),
                        rs.getString("apellido_materno"),
                        rs.getString("telefono"),
                        rs.getString("correo"),
                        rs.getString("user_login"),
                        "***",  // Contraseña oculta
                        rs.getInt("id_rol"),
                        rs.getString("nombre_rol"),
                        rs.getString("descripcion"),
                        rs.getInt("estado")
                );
            }

        } catch (SQLException e) {
            System.err.println("Error al obtener usuario por ID (" + usuarioId + "): " + e.getMessage());
            e.printStackTrace();
        }

        return usuarioDTO;
    }

    // Obtener usuario por ID (solo datos de Usuario)
    public static Usuario obtenerUsuarioPorId(int usuarioId, Connection con) {
        String sql = "SELECT id_usuario, dni, nombres, apellido_paterno, apellido_materno, telefono, correo, estado FROM usuarios WHERE id_usuario = ?";
        Usuario usuario = null;

        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, usuarioId);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                usuario = new Usuario();
                usuario.setIdUsuario(rs.getInt("id_usuario"));
                usuario.setDni(rs.getString("dni"));
                usuario.setNombres(rs.getString("nombres"));
                usuario.setApellidoPaterno(rs.getString("apellido_paterno"));
                usuario.setApellidoMaterno(rs.getString("apellido_materno"));
                usuario.setTelefono(rs.getString("telefono"));
                usuario.setCorreo(rs.getString("correo"));
                usuario.setEstado(rs.getInt("estado"));
            }

        } catch (SQLException e) {
            System.err.println("Error al obtener usuario por ID (" + usuarioId + "): " + e.getMessage());
            e.printStackTrace();
        }
        return usuario;
    }

    public static boolean existeUsuarioPorDni(String dni) {
        String sql = "SELECT COUNT(*) FROM usuarios WHERE dni = ?";
        boolean existe = false;

        try (Connection con = dataSource.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, dni);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    existe = rs.getInt(1) > 0;
                }
            }
        } catch (SQLException e) {
            System.err.println("Error SQLException al verificar si existe el usuario por DNI: " + dni + " - " + e.getMessage());
        }

        return existe;
    }

    public static boolean existeUsuario(String correo) {
        String sql = "SELECT COUNT(*) FROM usuarios WHERE correo = ?";
        boolean existe = false;

        try (Connection con = dataSource.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, correo);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    existe = rs.getInt(1) > 0;
                }
            }
        } catch (SQLException e) {
            System.err.println("Error SQLException al verificar si existe el usuario: " + correo + " - " + e.getMessage());
        }

        return existe;
    }

    //UPDATE
    public static boolean actualizarUsuario(Usuario nuevoUsuario) {
        String sql = "UPDATE usuarios SET telefono = ?, correo = ? , id_rol = ? WHERE id_usuario = ?";
        boolean result = false;

        try (Connection con = dataSource.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, nuevoUsuario.getTelefono());
            ps.setString(2, nuevoUsuario.getCorreo());
            ps.setInt(3, nuevoUsuario.getRol().getIdRol());
            ps.setInt(4, nuevoUsuario.getIdUsuario());

            int rowsAffected = ps.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("User " + nuevoUsuario.getIdUsuario() + " actualizado correctamente.");
                result = true;
            } else {
                System.err.println("Error al actualizar usuario con ID: " + nuevoUsuario.getIdUsuario());
            }
        } catch (SQLException e) {
            System.err.println("Error SQLException al actualizar usuario " + nuevoUsuario.getIdUsuario() + ": " + e.getMessage());
        }

        return result;
    }

    public boolean resetearContrasena(UsuarioRolDTO userReset) {
        String sql = "UPDATE usuarios SET contrasena = ? WHERE id_usuario = ?";

        //Por defecto es DNI, hasta que lo restablezca
        String nuevaContrasenaPorDefecto = userReset.getDni();
        int usuarioId = userReset.getIdUsuario();

        try (Connection con = dataSource.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            String hashPassword = BCrypt.hashpw(nuevaContrasenaPorDefecto, BCrypt.gensalt());
            ps.setString(1, hashPassword);
            ps.setInt(2, usuarioId);

            int rowsAffected = ps.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Contraseña reseteada para el usuario ID: " + usuarioId);
                return true;
            } else {
                System.err.println("No se encontró usuario con ID: " + usuarioId);
            }

        } catch (SQLException e) {
            System.err.println("SQLException al resetear contraseña del usuario ID " + usuarioId + ": " + e.getMessage());
        }

        return false;
    }

    public static Usuario actualizarContrasena(Usuario usuario, String nuevaContrasena) {
        String sql = "UPDATE usuarios SET contrasena = ? WHERE id_usuario = ?";

        Usuario usuarioActualizado = usuario;

        try (Connection con = dataSource.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            String hashPassword = BCrypt.hashpw(nuevaContrasena, BCrypt.gensalt());
            ps.setString(1, hashPassword);
            ps.setInt(2, usuario.getIdUsuario());

            int rowsAffected = ps.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Usuario " + usuario.getIdUsuario() + " actualizado correctamente.");
                usuarioActualizado.setContrasena(hashPassword);
            } else {
                System.err.println("Error al actualizar usuario con ID: " + usuario.getIdUsuario());
            }
        } catch (SQLException e) {
            System.err.println("Error SQLException al actualizar usuario " + usuario.getIdUsuario() + ": " + e.getMessage());
        }

        return usuarioActualizado;
    }

    public static boolean activarUsuario(int usuarioId) {
        String sql = "UPDATE usuarios SET estado = 1 WHERE id_usuario = ?";

        try (Connection con = dataSource.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, usuarioId);
            int rowsAffected = ps.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Usuario " + usuarioId + " actualizado correctamente.");
                return true;
            } else {
                System.err.println("Error al activar usuario con ID: " + usuarioId);
                return false;
            }
        } catch (SQLException e) {
            System.err.println("Error SQLException al activar usuario " + usuarioId + ": " + e.getMessage());
            return false;
        }
    }

    public static boolean bloquearUsuario(int usuarioId) {
        String sql = "UPDATE usuarios SET estado = 0 WHERE id_usuario = ?";

        try (Connection con = dataSource.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, usuarioId);

            int rowsAffected = ps.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Usuario " + usuarioId + " bloqueado correctamente.");
                return true;
            } else {
                System.err.println("No se pudo bloquear el usuario con ID: " + usuarioId);
                return false;
            }
        } catch (SQLException e) {
            System.err.println("Error SQLException al bloquear usuario " + usuarioId + ": " + e.getMessage());
            return false;
        }
    }
}

