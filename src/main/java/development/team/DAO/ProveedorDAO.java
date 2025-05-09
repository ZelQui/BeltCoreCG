package development.team.DAO;

import development.team.Models.Proveedor;
import development.team.Models.TipoDocumento;
import development.team.Utils.DataBaseUtil;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProveedorDAO {
    private static final DataSource dataSource = DataBaseUtil.getDataSource();

    // CREATE
    public static int registrarProveedor(Proveedor proveedor) {
        String sql = "INSERT INTO proveedores (nombre, telefono, correo, direccion, id_tipo_documento, numero_ruc, cuenta_interbancaria) VALUES (?, ?, ?, ?, ?, ?, ?)";
        int proveedorId = -1;

        try (Connection con = dataSource.getConnection()) {

            // Ejecuta el insert normalmente
            try (PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
                ps.setString(1, proveedor.getNombre());
                ps.setString(2, proveedor.getTelefono());
                ps.setString(3, proveedor.getCorreo());
                ps.setString(4, proveedor.getDireccion());
                ps.setInt(5, proveedor.getTipoDocumento().getIdTipoDocumento());
                ps.setString(6, proveedor.getNumeroRuc());
                ps.setString(7, proveedor.getCuentaInterbancaria());

                int rowsAffected = ps.executeUpdate();
                if (rowsAffected > 0) {
                    try (ResultSet rs = ps.getGeneratedKeys()) {
                        if (rs.next()) {
                            proveedorId = rs.getInt(1);
                        }
                    }
                }
            }

        } catch (SQLException e) {
            System.err.println("Error SQLException al registrar proveedor: " + e.getMessage());
        }

        return proveedorId;
    }

    // UPDATE
    public static boolean actualizarProveedor(Proveedor nuevoProveedor) {
        String sql = "UPDATE proveedores SET nombre = ?, telefono = ?, correo = ?, direccion = ? WHERE id_proveedor = ?";
        boolean result = false;

        try (Connection con = dataSource.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, nuevoProveedor.getNombre());
            ps.setString(2, nuevoProveedor.getTelefono());
            ps.setString(3, nuevoProveedor.getCorreo());
            ps.setString(4, nuevoProveedor.getDireccion());
            ps.setInt(5, nuevoProveedor.getIdProveedor());

            int rowsAffected = ps.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Proveedor " + nuevoProveedor.getIdProveedor() + " actualizado correctamente.");
                result = true;
            } else {
                System.err.println("Error al actualizar proveedor con ID: " + nuevoProveedor.getIdProveedor());
            }
        } catch (SQLException e) {
            System.err.println("Error SQLException al actualizar proveedor " + nuevoProveedor.getIdProveedor() + ": " + e.getMessage());
        }

        return result;
    }

    // ESTADOS PROVEEDOR
    public static void cambiarEstadoProveedor(int proveedorId, int estado) {
        String sql = "UPDATE proveedores SET estado = ? WHERE id_proveedor = ?";

        try (Connection con = dataSource.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, estado);
            ps.setInt(2, proveedorId);

            int rowsAffected = ps.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Proveedor " + proveedorId + " cambiado correctamente.");
            } else {
                System.err.println("Error al cambiar estado al proveedor con ID: " + proveedorId);
            }
        } catch (SQLException e) {
            System.err.println("Error SQLException al cambiar el estado al proveedor " + proveedorId + ": " + e.getMessage());
        }
    }

    // LISTAR PROVEEDORES
    public static List<Proveedor> obtenerProveedores() {
        String sql = "SELECT * FROM proveedores";
        List<Proveedor> proveedoresList = new ArrayList<>();

        try (Connection con = dataSource.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                int idProveedor = rs.getInt("id_proveedor");
                String nombre = rs.getString("nombre");
                String telefono = rs.getString("telefono");
                String correo = rs.getString("correo");
                String direccion = rs.getString("direccion");
                int estado = rs.getInt("estado");
                int idTipoDocumento = rs.getInt("id_tipo_documento");
                TipoDocumento tipoDocumento = new TipoDocumento();
                tipoDocumento.setIdTipoDocumento(idTipoDocumento);

                String numeroRuc = rs.getString("numero_ruc");
                String cuentaInterbancaria = rs.getString("cuenta_interbancaria");

                proveedoresList.add(new Proveedor(idProveedor, nombre, telefono, correo, direccion, estado, tipoDocumento, numeroRuc, cuentaInterbancaria));
            }

        } catch (SQLException e) {
            System.err.println("Error SQLException al obtener proveedores: " + e.getMessage());
        }

        return proveedoresList;
    }

    // BUSCAR PROVEEDOR POR ID
    public static Proveedor buscarProveedor(int idProveedor) {
        String sql = "SELECT * FROM proveedores WHERE id_proveedor = ?";

        try (Connection con = dataSource.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, idProveedor);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Proveedor proveedor = new Proveedor();
                proveedor.setIdProveedor(rs.getInt("id_proveedor"));
                proveedor.setNombre(rs.getString("nombre"));
                proveedor.setTelefono(rs.getString("telefono"));
                proveedor.setCorreo(rs.getString("correo"));
                proveedor.setDireccion(rs.getString("direccion"));
                proveedor.setEstado(rs.getInt("estado"));

                TipoDocumento tipoDocumento = new TipoDocumento();
                int idTipoDocumento = rs.getInt("id_tipo_documento");
                tipoDocumento.setIdTipoDocumento(idTipoDocumento);
                proveedor.setTipoDocumento(tipoDocumento);

                proveedor.setNumeroRuc(rs.getString("numero_ruc"));
                proveedor.setCuentaInterbancaria(rs.getString("cuenta_interbancaria"));
                return proveedor;
            }

        } catch (SQLException e) {
            System.err.println("Error SQLException al obtener el proveedor: " + e.getMessage());
        }

        return null;
    }


}
