package development.team.DAO;

import development.team.Models.Proveedor;
import development.team.Models.Rol;
import development.team.Utils.DataBaseUtil;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RolDAO {
    private static final DataSource dataSource = DataBaseUtil.getDataSource();

    public Rol obtenerRolPorId(int idRol) {
        Rol rol = null;
        String sql = "SELECT id_rol, nombre_rol, descripcion FROM roles WHERE id_rol = ?";

        try (Connection con = dataSource.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, idRol);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    rol = new Rol(
                            rs.getInt("id_rol"),
                            rs.getString("nombre_rol"),
                            rs.getString("descripcion")
                    );
                }
            }

        } catch (SQLException e) {
            System.err.println("Error al obtener rol por ID: " + e.getMessage());
        }

        return rol;
    }
    /**
     * Obtiene un rol usando una conexión ya proporcionada.
     * No crea una nueva conexión.
     */
    public Rol obtenerRolPorId(int idRol, Connection con) throws SQLException {
        Rol rol = null;
        String sql = "SELECT id_rol, nombre_rol, descripcion FROM roles WHERE id_rol = ?";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, idRol);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    rol = new Rol(
                            rs.getInt("id_rol"),
                            rs.getString("nombre_rol"),
                            rs.getString("descripcion")
                    );
                }
            }
        }
        return rol;
    }

    // LISTAR ROLES
    public static List<Rol> obtenerRols() {
        String sql = "SELECT * FROM roles";
        List<Rol> rolesList = new ArrayList<>();

        try (Connection con = dataSource.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                int idRol = rs.getInt("id_rol");
                String nombreRol = rs.getString("nombre_rol");
                String descripcion = rs.getString("descripcion");

                rolesList.add(new Rol(idRol, nombreRol, descripcion));
            }

        } catch (SQLException e) {
            System.err.println("Error SQLException al obtener roles: " + e.getMessage());
        }

        return rolesList;
    }
}
