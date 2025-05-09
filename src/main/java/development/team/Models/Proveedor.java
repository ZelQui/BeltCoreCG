package development.team.Models;

public class Proveedor {
    private int idProveedor;
    private String nombre;
    private String telefono;
    private String correo;
    private String direccion;
    private int estado;
    private TipoDocumento tipoDocumento;
    private String numeroRuc;
    private String cuentaInterbancaria;

    public Proveedor() {
    }

    public Proveedor(String nombre, String telefono, String correo, String direccion) {
        this.nombre = nombre;
        this.telefono = telefono;
        this.correo = correo;
        this.direccion = direccion;
    }

    public Proveedor(int idProveedor, String nombre, String telefono, String correo, String direccion, int estado, TipoDocumento tipoDocumento, String numeroRuc, String cuentaInterbancaria) {
        this.idProveedor = idProveedor;
        this.nombre = nombre;
        this.telefono = telefono;
        this.correo = correo;
        this.direccion = direccion;
        this.estado = estado;
        this.tipoDocumento = tipoDocumento;
        this.numeroRuc = numeroRuc;
        this.cuentaInterbancaria = cuentaInterbancaria;
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

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    public TipoDocumento getTipoDocumento() {
        return tipoDocumento;
    }

    public void setTipoDocumento(TipoDocumento tipoDocumento) {
        this.tipoDocumento = tipoDocumento;
    }

    public String getNumeroRuc() {
        return numeroRuc;
    }

    public void setNumeroRuc(String numeroRuc) {
        this.numeroRuc = numeroRuc;
    }

    public String getCuentaInterbancaria() {
        return cuentaInterbancaria;
    }

    public void setCuentaInterbancaria(String cuentaInterbancaria) {
        this.cuentaInterbancaria = cuentaInterbancaria;
    }

    @Override
    public String toString() {
        return "Proveedor{" +
                "idProveedor=" + idProveedor +
                ", nombre='" + nombre + '\'' +
                ", telefono='" + telefono + '\'' +
                ", correo='" + correo + '\'' +
                ", direccion='" + direccion + '\'' +
                ", estado=" + estado +
                ", tipoDocumento=" + tipoDocumento +
                ", numeroRuc='" + numeroRuc + '\'' +
                ", cuentaInterbancaria='" + cuentaInterbancaria + '\'' +
                '}';
    }
}
