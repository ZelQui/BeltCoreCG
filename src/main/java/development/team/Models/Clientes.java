package development.team.Models;

public class Clientes {

    private int idCliente;
    private String nombres;
    private String apellidos;
    private String numeroDocumento;
    private int idTipoDocumento;
    private String direccion;
    private String telefono;
    private String correo;
    private int idTipoCliente;

    public Clientes() {
    }

    public Clientes(int idCliente, String nombres, String apellidos, String numeroDocumento, int idTipoDocumento, String direccion, String telefono, String correo, int idTipoCliente) {
        this.idCliente = idCliente;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.numeroDocumento = numeroDocumento;
        this.idTipoDocumento = idTipoDocumento;
        this.direccion = direccion;
        this.telefono = telefono;
        this.correo = correo;
        this.idTipoCliente = idTipoCliente;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getNumeroDocumento() {
        return numeroDocumento;
    }

    public void setNumeroDocumento(String numeroDocumento) {
        this.numeroDocumento = numeroDocumento;
    }

    public int getIdTipoDocumento() {
        return idTipoDocumento;
    }

    public void setIdTipoDocumento(int idTipoDocumento) {
        this.idTipoDocumento = idTipoDocumento;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
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

    public int getIdTipoCliente() {
        return idTipoCliente;
    }

    public void setIdTipoCliente(int idTipoCliente) {
        this.idTipoCliente = idTipoCliente;
    }

    @Override
    public String toString() {
        return "Clientes{" +
                "idCliente=" + idCliente +
                ", nombres='" + nombres + '\'' +
                ", apellidos='" + apellidos + '\'' +
                ", numeroDocumento='" + numeroDocumento + '\'' +
                ", idTipoDocumento=" + idTipoDocumento +
                ", direccion='" + direccion + '\'' +
                ", telefono='" + telefono + '\'' +
                ", correo='" + correo + '\'' +
                ", idTipoCliente=" + idTipoCliente +
                '}';
    }
}
