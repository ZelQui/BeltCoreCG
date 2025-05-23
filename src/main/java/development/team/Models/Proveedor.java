package development.team.Models;

public class Proveedor {
    private int idProveedor;
    private String nombre;
    private String ruc;
    private String telefono;
    private String domicilioFiscal;
    private String domicilioAlterna;
    private String estadoContribuyente;
    private TipoDocumento tipoDocumento;
    private String numeroCuenta;

    public Proveedor() {
    }

    public Proveedor(int idProveedor, String telefono, String domicilioAlterna) {
        this.idProveedor = idProveedor;
        this.telefono = telefono;
        this.domicilioAlterna = domicilioAlterna;
    }

    public Proveedor(int idProveedor, String nombre, String ruc, String telefono, String domicilioFiscal, String domicilioAlterna, String estadoContribuyente, TipoDocumento tipoDocumento, String numeroCuenta) {
        this.idProveedor = idProveedor;
        this.nombre = nombre;
        this.ruc = ruc;
        this.telefono = telefono;
        this.domicilioFiscal = domicilioFiscal;
        this.domicilioAlterna = domicilioAlterna;
        this.estadoContribuyente = estadoContribuyente;
        this.tipoDocumento = tipoDocumento;
        this.numeroCuenta = numeroCuenta;
    }

    public Proveedor(String nombre, String ruc, String telefono, String domicilioFiscal, String domicilioAlterna, String estadoContribuyente, TipoDocumento tipoDocumento, String numeroCuenta) {
        this.nombre = nombre;
        this.ruc = ruc;
        this.telefono = telefono;
        this.domicilioFiscal = domicilioFiscal;
        this.domicilioAlterna = domicilioAlterna;
        this.estadoContribuyente = estadoContribuyente;
        this.tipoDocumento = tipoDocumento;
        this.numeroCuenta = numeroCuenta;
    }

    public String getRuc() {
        return ruc;
    }

    public void setRuc(String ruc) {
        this.ruc = ruc;
    }

    public int getIdProveedor() {
        return idProveedor;
    }

    public void setIdProveedor(int idProveedor) {
        this.idProveedor = idProveedor;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
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

    public TipoDocumento getTipoDocumento() {
        return tipoDocumento;
    }

    public void setTipoDocumento(TipoDocumento tipoDocumento) {
        this.tipoDocumento = tipoDocumento;
    }

    public String getNumeroCuenta() {
        return numeroCuenta;
    }

    public void setNumeroCuenta(String numeroCuenta) {
        this.numeroCuenta = numeroCuenta;
    }

    @Override
    public String toString() {
        return "Proveedor{" +
                "idProveedor=" + idProveedor +
                ", nombre='" + nombre + '\'' +
                ", telefono='" + telefono + '\'' +
                ", domicilioFiscal='" + domicilioFiscal + '\'' +
                ", domicilioAlterna='" + domicilioAlterna + '\'' +
                ", estadoContribuyente='" + estadoContribuyente + '\'' +
                ", tipoDocumento=" + tipoDocumento +
                ", numeroCuenta='" + numeroCuenta + '\'' +
                '}';
    }
}
