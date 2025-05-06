package development.team.DAO;

import development.team.Models.Modulo;
import development.team.Utils.DataBaseUtil;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ModuloDAO {
    private static final DataSource dataSource = DataBaseUtil.getDataSource();

    public static List<Modulo> obtenerModulosPorRol(int idRol) {
        List<Modulo> modulos = new ArrayList<>();

        String sql = "SELECT m.nombre_modulo, m.ruta, m.icono, m.modulo_padre_id " +
                "FROM modulos m " +
                "INNER JOIN rol_modulo rm ON m.id_modulo = rm.id_modulo " +
                "WHERE rm.id_rol = ? " +
                "ORDER BY m.id_modulo"; // Opcional: orden por ID

        try (Connection con = dataSource.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, idRol);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Modulo modulo = new Modulo();
                modulo.setNombre(rs.getString("nombre_modulo"));
                modulo.setRuta(rs.getString("ruta"));
                modulo.setIcono(rs.getString("icono"));
                int padreId = rs.getInt("modulo_padre_id");
                modulo.setModuloPadreId(rs.wasNull() ? null : padreId);
                modulos.add(modulo);
            }

        } catch (SQLException e) {
            System.out.println("Error al obtener módulos por rol: " + e.getMessage());
        }

        return modulos;
    }

    /*
    //CONSULTA PARA VER LOS MODULOS ASIGANDOS A CADA ROL
    SELECT r.id_rol, r.nombre_rol AS nombre_rol,
        GROUP_CONCAT(
            m.nombre_modulo
            ORDER BY m.id_modulo
            SEPARATOR ', '
        ) AS modulos_asignados
    FROM rol_modulo rm
    INNER JOIN roles r ON rm.id_rol = r.id_rol
    INNER JOIN modulos m ON rm.id_modulo = m.id_modulo
    GROUP BY r.id_rol, r.nombre_rol
    ORDER BY r.id_rol;
    */
}

