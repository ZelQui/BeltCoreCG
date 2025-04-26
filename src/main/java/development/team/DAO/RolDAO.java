package development.team.DAO;

import development.team.Models.Rol;
import development.team.Utils.DataBaseUtil;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RolDAO {
    private static final DataSource dataSource = DataBaseUtil.getDataSource();

    /**
     * Obtiene un rol usando una conexión ya proporcionada.
     * No crea una nueva conexión.
     */
    public Rol obtenerRolPorId(int idRol, Connection con) throws SQLException {
        Rol rol = null;
        String sql = "SELECT id_rol, nombre_rol, descripcion FROM roles WHERE id_rol = ?";
        try (PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            ps.setInt(1, idRol);
            if (rs.next()) {
                rol = new Rol(
                        rs.getInt("id_rol"),
                        rs.getString("nombre_rol"),
                        rs.getString("descripcion")
                );
            }
        }
        return rol;
    }
}
