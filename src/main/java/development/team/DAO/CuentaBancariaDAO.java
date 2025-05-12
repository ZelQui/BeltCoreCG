package development.team.DAO;

import development.team.Models.CuentaBancaria;
import development.team.Models.Rol;
import development.team.Utils.DataBaseUtil;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CuentaBancariaDAO {

    private static final DataSource dataSource = DataBaseUtil.getDataSource();

    // LISTAR CUENTAS
    public static List<CuentaBancaria> obtenerCuentasBancarias() {
        String sql = "SELECT * FROM cuentas_bancarias";
        List<CuentaBancaria> cuentaBancariaListList = new ArrayList<>();

        try (Connection con = dataSource.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                int idCuentaBancaria = rs.getInt("id_cuenta_bancaria");
                String tipoCuentaBancaria = rs.getString("tipo_cuenta_bancaria");

                cuentaBancariaListList.add(new CuentaBancaria(idCuentaBancaria, tipoCuentaBancaria));
            }

        } catch (SQLException e) {
            System.err.println("Error SQLException al obtener cuentas: " + e.getMessage());
        }

        return cuentaBancariaListList;
    }
}
