package development.team.Models;

public class CuentaBancaria {

    private int idCuentaBancaria;
    private String tipoCuentaBancaria;

    public CuentaBancaria() {
    }

    public CuentaBancaria(int idCuentaBancaria, String tipoCuentaBancaria) {
        this.idCuentaBancaria = idCuentaBancaria;
        this.tipoCuentaBancaria = tipoCuentaBancaria;
    }

    public int getIdCuentaBancaria() {
        return idCuentaBancaria;
    }

    public void setIdCuentaBancaria(int idCuentaBancaria) {
        this.idCuentaBancaria = idCuentaBancaria;
    }

    public String getTipoCuentaBancaria() {
        return tipoCuentaBancaria;
    }

    public void setTipoCuentaBancaria(String tipoCuentaBancaria) {
        this.tipoCuentaBancaria = tipoCuentaBancaria;
    }
}
