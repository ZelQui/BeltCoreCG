package development.team.DTO;

public class ProveedorCuentasBancarias {

    private int idProveedor;
    private String nombreRazonSocial;
    private String telefono;
    private String domicilioFiscal;
    private String domicilioAlterna;
    private String estadoContribuyente;
    private String numeroRuc;
    private String numeroCuenta;
    private int idCuentaBancaria;
    private String tipoCuentaBancaria;

    public ProveedorCuentasBancarias() {
    }

    public ProveedorCuentasBancarias(int idProveedor, String nombreRazonSocial, String telefono, String domicilioFiscal, String domicilioAlterna, String estadoContribuyente, String numeroRuc, String numeroCuenta, int idCuentaBancaria, String tipoCuentaBancaria) {
        this.idProveedor = idProveedor;
        this.nombreRazonSocial = nombreRazonSocial;
        this.telefono = telefono;
        this.domicilioFiscal = domicilioFiscal;
        this.domicilioAlterna = domicilioAlterna;
        this.estadoContribuyente = estadoContribuyente;
        this.numeroRuc = numeroRuc;
        this.numeroCuenta = numeroCuenta;
        this.idCuentaBancaria = idCuentaBancaria;
        this.tipoCuentaBancaria = tipoCuentaBancaria;
    }

    public int getIdProveedor() {
        return idProveedor;
    }

    public void setIdProveedor(int idProveedor) {
        this.idProveedor = idProveedor;
    }

    public String getNombreRazonSocial() {
        return nombreRazonSocial;
    }

    public void setNombreRazonSocial(String nombreRazonSocial) {
        this.nombreRazonSocial = nombreRazonSocial;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getDomicilioFiscal() {
        return domicilioFiscal;
    }

    public void setDomicilioFiscal(String domicilioFiscal) {
        this.domicilioFiscal = domicilioFiscal;
    }

    public String getDomicilioAlterna() {
        return domicilioAlterna;
    }

    public void setDomicilioAlterna(String domicilioAlterna) {
        this.domicilioAlterna = domicilioAlterna;
    }

    public String getEstadoContribuyente() {
        return estadoContribuyente;
    }

    public void setEstadoContribuyente(String estadoContribuyente) {
        this.estadoContribuyente = estadoContribuyente;
    }

    public String getNumeroRuc() {
        return numeroRuc;
    }

    public void setNumeroRuc(String numeroRuc) {
        this.numeroRuc = numeroRuc;
    }

    public String getNumeroCuenta() {
        return numeroCuenta;
    }

    public void setNumeroCuenta(String numeroCuenta) {
        this.numeroCuenta = numeroCuenta;
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

    @Override
    public String toString() {
        return "ProveedorCuentasBancarias{" +
                "idProveedor=" + idProveedor +
                ", nombreRazonSocial='" + nombreRazonSocial + '\'' +
                ", telefono='" + telefono + '\'' +
                ", domicilioFiscal='" + domicilioFiscal + '\'' +
                ", domicilioAlterna='" + domicilioAlterna + '\'' +
                ", estadoContribuyente='" + estadoContribuyente + '\'' +
                ", numeroRuc='" + numeroRuc + '\'' +
                ", numeroCuenta='" + numeroCuenta + '\'' +
                '}';
    }
}
