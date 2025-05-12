package development.team.Models;

public class ProveedorCuenta {

    private int idProveedor;
    private int idCuentaBancaria;

    public ProveedorCuenta() {
    }

    public ProveedorCuenta(int idProveedor, int idCuentaBancaria) {
        this.idProveedor = idProveedor;
        this.idCuentaBancaria = idCuentaBancaria;
    }

    public int getIdProveedor() {
        return idProveedor;
    }

    public void setIdProveedor(int idProveedor) {
        this.idProveedor = idProveedor;
    }

    public int getIdCuentaBancaria() {
        return idCuentaBancaria;
    }

    public void setIdCuentaBancaria(int idCuentaBancaria) {
        this.idCuentaBancaria = idCuentaBancaria;
    }
}
