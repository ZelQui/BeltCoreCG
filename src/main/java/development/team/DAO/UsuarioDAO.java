package development.team.DAO;

import development.team.Models.*;
import development.team.Utils.DataBaseUtil;
import org.mindrot.jbcrypt.BCrypt;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UsuarioDAO {
    private static final DataSource dataSource = DataBaseUtil.getDataSource();
    private static final RolDAO rolDAO = new RolDAO();

    public static boolean validarCredenciales(String correo, String contrasena) {
        String sql = "SELECT contrasena FROM usuarios WHERE correo = ?";

        try (Connection con = dataSource.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, correo);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    String storedHash = rs.getString("contrasena");
                    storedHash = storedHash.trim();
                    return BCrypt.checkpw(contrasena, storedHash);
                }
            }
        } catch (SQLException e) {
            System.err.println("Error al validar las credenciales para el usuario: " + correo + " - " + e.getMessage());
        }
        return false;
    }

    public static Usuario obtenerUsuarioSesion(String correo) {
        Usuario usuario = null;
        String sql = "SELECT id_usuario, nombre, correo, contrasena, id_rol FROM usuarios WHERE correo = ?";

        try (Connection con = dataSource.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, correo);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    // Pasamos la MISMA conexión al rolDAO
                    Rol rol = rolDAO.obtenerRolPorId(rs.getInt("id_rol"), con);
                    usuario = new Usuario(
                            rs.getInt("id_usuario"),
                            rs.getString("nombre"),
                            rs.getString("correo"),
                            rs.getString("contrasena"),
                            rol
                    );
                    System.out.println(usuario.getNombre() + " inició sesión.");
                }
            }
        } catch (SQLException e) {
            System.err.println("Error al obtener el usuario: " + e.getMessage());
        }
        return usuario;
    }
}
