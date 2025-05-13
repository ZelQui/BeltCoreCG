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
                "ORDER BY " +
                "    CASE " +
                "        WHEN m.id_modulo = 9 THEN 2 " +
                "        WHEN m.modulo_padre_id = 9 THEN 3 " +
                "        ELSE 1 " +
                "    END, " +
                "    m.id_modulo;"; // Opcional: orden por ID

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

    // CREATE
    public static int registrarModulo(Modulo modulo) {
        String sql = "INSERT INTO modulos (nombre_modulo, ruta, icono) VALUES (?, ?, ?)";
        int moduloID = -1;

        try (Connection con = dataSource.getConnection();
             PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) { // Importante para obtener ID generado

            ps.setString(1, modulo.getNombre());
            ps.setString(2, modulo.getRuta());
            ps.setString(3, modulo.getIcono());

            int rowsAffected = ps.executeUpdate();
            if (rowsAffected > 0) {
                try (ResultSet rs = ps.getGeneratedKeys()) {
                    if (rs.next()) {
                        moduloID = rs.getInt(1); // Obtener el ID generado
                    }
                }
            }
        } catch (SQLException e) {
            System.err.println("Error SQLException al registrar modulo: " + e.getMessage());
        }
        return moduloID; //Retorna el ID que ha insertado
    }

    // UPDATE
    private static boolean actualizarModulo(Modulo nuevoModulo) {
        String sql = "UPDATE modulos SET nombre_modulo = ?, ruta = ?, icono = ? WHERE id_modulo = ?";
        boolean result = false;

        try (Connection con = dataSource.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, nuevoModulo.getNombre());
            ps.setString(2, nuevoModulo.getRuta());
            ps.setString(3, nuevoModulo.getIcono());
            ps.setInt(4, nuevoModulo.getIdModulo());

            int rowsAffected = ps.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Modulo " + nuevoModulo.getIdModulo() + " actualizado correctamente.");
                result = true;
            } else {
                System.err.println("Error al actualizar modulos con ID: " + nuevoModulo.getIdModulo());
            }
        } catch (SQLException e) {
            System.err.println("Error SQLException al actualizar modulos " + nuevoModulo.getIdModulo() + ": " + e.getMessage());
        }

        return result;
    }

    // LISTAR
    public static List<Modulo> obtenerModulos() {
        String sql = "SELECT * FROM modulos";
        List<Modulo> modulosList = new ArrayList<>();

        try (Connection con = dataSource.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                int idModulo = rs.getInt("id_modulo");
                String nombre = rs.getString("nombre_modulo");
                String ruta = rs.getString("ruta");
                String icono = rs.getString("icono");
                int padreId = rs.getInt("modulo_padre_id");


                modulosList.add(new Modulo(idModulo, nombre, ruta, icono, padreId));
            }

        } catch (SQLException e) {
            System.err.println("Error SQLException al obtener modulos: " + e.getMessage());
        }

        return modulosList;
    }


}

