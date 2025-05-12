package development.team.DAO;

import development.team.Models.Proveedor;
import development.team.Models.ProveedorCuenta;
import development.team.Utils.DataBaseUtil;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ProveedorCuentaDAO {
    private static final DataSource dataSource = DataBaseUtil.getDataSource();

    // REGISTRAR PROVEEDOR CUENTA
    public static boolean registrarProveedorCuenta(ProveedorCuenta proveedorCuenta) {
        String sql = "INSERT INTO proveedores_cuentas (id_proveedor, id_cuenta_bancaria) VALUES (?, ?)";
        boolean result = false;

        try (Connection con = dataSource.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, proveedorCuenta.getIdProveedor());
            ps.setInt(2, proveedorCuenta.getIdCuentaBancaria());

            int rowsAffected = ps.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Proveedor Cuenta Bancaria registrado correctamente.");
                result = true;
            } else {
                System.err.println("Error al registrar el Proveedor Cuenta Bancaria");
            }
        } catch (SQLException e) {
            System.err.println("Error SQLException al registrar Proveedor Cuenta Bancaria: " + e.getMessage());
        }

        return result;
    }
}
