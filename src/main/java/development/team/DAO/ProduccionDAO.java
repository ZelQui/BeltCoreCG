package development.team.DAO;

import development.team.Models.*;
import development.team.Utils.DataBaseUtil;

import javax.sql.DataSource;
import java.sql.*;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

public class ProduccionDAO {
    private static final DataSource dataSource = DataBaseUtil.getDataSource();

    //READ
    public static List<Produccion> obtenerProducciones() {
        String sql = "SELECT id_produccion, id_producto, fecha_produccion, fecha_cierre, cantidad_planeada, " +
                "cantidad_producida, cantidad_defectuosa, rendimiento, estado, id_usuario FROM producciones";
        List<Produccion> produccionesList = new ArrayList<>();

        try (Connection con = dataSource.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Producto product = ProductoDAO.obtenerProductoPorId(rs.getInt("id_producto"), con);
                Usuario user = UsuarioDAO.obtenerUsuarioPorId(rs.getInt("id_usuario"), con);

                ZoneId zonaLima = ZoneId.of("America/Lima");
                LocalDateTime fechaProduccion = rs.getTimestamp("fecha_produccion") != null
                        ? rs.getTimestamp("fecha_produccion").toInstant().atZone(zonaLima).toLocalDateTime()
                        : null;

                LocalDateTime fechaCierre = rs.getTimestamp("fecha_cierre") != null
                        ? rs.getTimestamp("fecha_cierre").toInstant().atZone(zonaLima).toLocalDateTime()
                        : null;

                produccionesList.add(new Produccion(
                        rs.getInt("id_produccion"),
                        product,
                        fechaProduccion,
                        fechaCierre,
                        rs.getInt("cantidad_planeada"),
                        rs.getInt("cantidad_producida"),
                        rs.getInt("cantidad_defectuosa"),
                        rs.getBigDecimal("rendimiento"),
                        rs.getInt("estado"),
                        user
                ));
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return produccionesList;
    }

    //CREATE
    public static void insertarProduccion(int idProducto, LocalDateTime fechaProduccion, int cantidadPlaneada, int idUsuario) {
        String sql = "INSERT INTO producciones (id_producto, fecha_produccion, cantidad_planeada, estado, id_usuario) " +
                "VALUES (?, ?, ?, 0, ?)";

        try (Connection con = dataSource.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, idProducto);
            ps.setTimestamp(2, Timestamp.valueOf(fechaProduccion));
            ps.setInt(3, cantidadPlaneada);
            ps.setInt(4, idUsuario);

            ps.executeUpdate();

        } catch (SQLException e) {
            System.err.println("Error al insertar producción: " + e.getMessage());
            throw new RuntimeException(e);
        }
    }
}
