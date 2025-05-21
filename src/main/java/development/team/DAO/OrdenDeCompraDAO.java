package development.team.DAO;

import development.team.Models.Compra;
import development.team.Models.MetodoPago;
import development.team.Models.Proveedor;
import development.team.Models.Usuario;
import development.team.Utils.DataBaseUtil;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.Timestamp;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class OrdenDeCompraDAO {

    private static final DataSource dataSource = DataBaseUtil.getDataSource();

    // CREATE
    public static int registrarCompra(Compra compra) {
        String sql = "INSERT INTO compras (id_usuario, estado, fecha_solicitud) VALUES (?, ?, ?)";
        int idCompra = -1;

        try (Connection con = dataSource.getConnection()) {

            // Ejecuta el insert normalmente
            try (PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
                ps.setInt(1, compra.getUsuario().getIdUsuario());
                compra.setEstado("PENDIENTE");
                ps.setString(2, compra.getEstado());
                ps.setTimestamp(3, Timestamp.valueOf(compra.getFechaSolicitud()));

                int rowsAffected = ps.executeUpdate();
                if (rowsAffected > 0) {
                    try (ResultSet rs = ps.getGeneratedKeys()) {
                        if (rs.next()) {
                            idCompra = rs.getInt(1);
                        }
                    }
                }
            }

        } catch (SQLException e) {
            System.err.println("Error SQLException al registrar la Compra: " + e.getMessage());
        }

        return idCompra;
    }

    // LISTAR ORDENES
    public static List<Compra> obtenerCompras() {
        String sql = "SELECT * FROM compras";
        List<Compra> compras = new ArrayList<>();

        try (Connection con = dataSource.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Compra compra = new Compra();
                compra.setIdCompra(rs.getInt("id_compra"));
                compra.setFechaSolicitud(rs.getTimestamp("fecha_solicitud").toLocalDateTime());

                // Inicializamos los objetos relacionados
                Proveedor proveedor = new Proveedor();
                proveedor.setIdProveedor(rs.getInt("id_proveedor"));
                compra.setProveedor(proveedor);

                Usuario usuario = new Usuario();
                usuario.setIdUsuario(rs.getInt("id_usuario"));
                compra.setUsuario(usuario);

                MetodoPago metodoPago = new MetodoPago();
                metodoPago.setIdMetodoPago(rs.getInt("id_metodo_pago"));
                compra.setMetodoPago(metodoPago);

                compra.setTotalCompra(rs.getInt("total_compra"));
                compra.setEstado(rs.getString("estado"));

                compras.add(compra);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return compras;
    }

    // ANULAR COMPRA
    public static boolean anularCompra (int idCompra) {
        String sql = "UPDATE compras SET estado = ? WHERE id_compra = ?";

        try (Connection con = dataSource.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)){
             ps.setString(1, "ANULADA");
             ps.setInt(2, idCompra);
            int rowsAffected = ps.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Compra " + idCompra + " anular correctamente.");
                return true;
            } else {
                System.err.println("Error al anular compra con ID: " + idCompra);
                return false;
            }

        } catch (SQLException e) {
            System.err.println("Error SQLException al anular compra " + idCompra + ": " + e.getMessage());
            return false;
        }
    }

}
