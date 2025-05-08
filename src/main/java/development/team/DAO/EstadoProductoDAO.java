package development.team.DAO;

import development.team.Models.EstadoProducto;
import development.team.Utils.DataBaseUtil;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EstadoProductoDAO {

    private static final DataSource dataSource = DataBaseUtil.getDataSource();

    // CREATE
    public static int registrarEstadoProducto(EstadoProducto estadoProducto) {
        String sql = "INSERT INTO estadosproducto (nombre) VALUES (?)";
        int estadoProductoID = -1;

        try (Connection con = dataSource.getConnection();
             PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) { // Importante para obtener ID generado

            ps.setString(1, estadoProducto.getNombre());

            int rowsAffected = ps.executeUpdate();
            if (rowsAffected > 0) {
                try (ResultSet rs = ps.getGeneratedKeys()) {
                    if (rs.next()) {
                        estadoProductoID = rs.getInt(1); // Obtener el ID generado
                    }
                }
            }
        } catch (SQLException e) {
            System.err.println("Error SQLException al registrar estadoProducto: " + e.getMessage());
        }
        return estadoProductoID; //Retorna el ID que ha insertado
    }

    // UPDATE
    private static boolean actualizarEstadoProducto(EstadoProducto nuevaEstadoProducto) {
        String sql = "UPDATE estadosproducto SET nombre = ? WHERE id_estado_producto = ?";
        boolean result = false;

        try (Connection con = dataSource.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, nuevaEstadoProducto.getNombre());
            ps.setInt(2, nuevaEstadoProducto.getIdEstadoProducto());

            int rowsAffected = ps.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("EstadoProducto " + nuevaEstadoProducto.getIdEstadoProducto() + " actualizado correctamente.");
                result = true;
            } else {
                System.err.println("Error al actualizar estadoProducto con ID: " + nuevaEstadoProducto.getIdEstadoProducto());
            }
        } catch (SQLException e) {
            System.err.println("Error SQLException al actualizar estadoProducto " + nuevaEstadoProducto.getIdEstadoProducto() + ": " + e.getMessage());
        }

        return result;
    }

    // LISTAR
    public static List<EstadoProducto> obtenerEstadoProductos() {
        String sql = "SELECT * FROM estadosproducto";
        List<EstadoProducto> estadoProductoList = new ArrayList<>();

        try (Connection con = dataSource.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                int idEstadoProducto = rs.getInt("id_estado_producto");
                String nombre = rs.getString("nombre");

                estadoProductoList.add(new EstadoProducto(idEstadoProducto, nombre));
            }

        } catch (SQLException e) {
            System.err.println("Error SQLException al obtener estadoProductos: " + e.getMessage());
        }

        return estadoProductoList;
    }
}
