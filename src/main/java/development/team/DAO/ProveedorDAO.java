package development.team.DAO;

import development.team.DTO.ProveedorCuentasBancarias;
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
        String sql = "INSERT INTO proveedores (id_tipo_documento, numero_ruc, nombre_razon_social, estado_contribuyente, domicilio_fiscal, domicilio_alterna, telefono, numero_cuenta) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        int proveedorId = -1;

        try (Connection con = dataSource.getConnection()) {

            // Ejecuta el insert normalmente
            try (PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
                ps.setInt(1, proveedor.getTipoDocumento().getIdTipoDocumento());
                ps.setString(2, proveedor.getRuc());
                ps.setString(3, proveedor.getNombre());
                ps.setString(4, proveedor.getEstadoContribuyente());
                ps.setString(5, proveedor.getDomicilioFiscal());
                ps.setString(6, proveedor.getDomicilioAlterna());
                ps.setString(7, proveedor.getTelefono());
                ps.setString(8, proveedor.getNumeroCuenta());

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
        String sql = "UPDATE proveedores SET telefono = ?, domicilio_alterna = ? WHERE id_proveedor = ?";
        boolean result = false;

        try (Connection con = dataSource.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, nuevoProveedor.getTelefono());
            ps.setString(2, nuevoProveedor.getDomicilioAlterna());
            ps.setInt(3, nuevoProveedor.getIdProveedor());

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

    // LISTAR PROVEEDORES
    public static List<ProveedorCuentasBancarias> obtenerProveedores() {
        String sql = "SELECT \n" +
                "    p.id_proveedor,\n" +
                "    p.numero_ruc,\n" +
                "    p.nombre_razon_social,\n" +
                "    p.estado_contribuyente,\n" +
                "    p.domicilio_fiscal,\n" +
                "    p.telefono,\n" +
                "    p.domicilio_alterna,\n" +
                "    p.numero_cuenta,\n" +
                "    cb.id_cuenta_bancaria,\n" +
                "    cb.tipo_cuenta_bancaria \n" +
                "FROM proveedores p\n" +
                "JOIN proveedores_cuentas pc ON p.id_proveedor = pc.id_proveedor\n" +
                "JOIN cuentas_bancarias cb ON pc.id_cuenta_bancaria = cb.id_cuenta_bancaria;\n";
        List<ProveedorCuentasBancarias> proveedoresList = new ArrayList<>();

        try (Connection con = dataSource.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                int idProveedor = rs.getInt("id_proveedor");
                String nombreRazonSocial = rs.getString("nombre_razon_social");
                String telefono = rs.getString("telefono");
                String domicilioFiscal = rs.getString("domicilio_fiscal");
                String domicilioAlterna = rs.getString("domicilio_alterna");
                String estadoContribuyente = rs.getString("estado_contribuyente");
                String numeroRuc = rs.getString("numero_ruc");
                String numeroCuenta = rs.getString("numero_cuenta");

                int idCuentaBancaria = rs.getInt("id_cuenta_bancaria");
                String tipoCuentaBancaria = rs.getString("tipo_cuenta_bancaria");

                proveedoresList.add(new ProveedorCuentasBancarias(idProveedor, nombreRazonSocial, telefono, domicilioFiscal, domicilioAlterna, estadoContribuyente, numeroRuc, numeroCuenta, idCuentaBancaria, tipoCuentaBancaria));
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
                proveedor.setNombre(rs.getString("nombre_razon_social"));
                proveedor.setTelefono(rs.getString("telefono"));
                proveedor.setDomicilioFiscal(rs.getString("domicilio_fiscal"));
                proveedor.setDomicilioAlterna(rs.getString("domicilio_alterna"));
                proveedor.setRuc(rs.getString("numero_ruc"));
                proveedor.setNumeroCuenta(rs.getString("numero_cuenta"));

                TipoDocumento tipoDocumento = new TipoDocumento();
                int idTipoDocumento = rs.getInt("id_tipo_documento");
                tipoDocumento.setIdTipoDocumento(idTipoDocumento);
                proveedor.setTipoDocumento(tipoDocumento);

                return proveedor;
            }

        } catch (SQLException e) {
            System.err.println("Error SQLException al obtener el proveedor: " + e.getMessage());
        }

        return null;
    }


}
