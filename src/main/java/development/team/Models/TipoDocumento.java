package development.team.Models;

public class TipoDocumento {
    private int idTipoDocumento;
    private String nombre;
    private String siglas;
    private int idTipoCliente;

    public TipoDocumento() {
    }

    public TipoDocumento(int idTipoDocumento, String nombre, String siglas, int idTipoCliente) {
        this.idTipoDocumento = idTipoDocumento;
        this.nombre = nombre;
        this.siglas = siglas;
        this.idTipoCliente = idTipoCliente;
    }

    public int getIdTipoDocumento() {
        return idTipoDocumento;
    }

    public void setIdTipoDocumento(int idTipoDocumento) {
        this.idTipoDocumento = idTipoDocumento;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getSiglas() {
        return siglas;
    }

    public void setSiglas(String siglas) {
        this.siglas = siglas;
    }

    public int getIdTipoCliente() {
        return idTipoCliente;
    }

    public void setIdTipoCliente(int idTipoCliente) {
        this.idTipoCliente = idTipoCliente;
    }

    @Override
    public String toString() {
        return "TipoDocumento{" +
                "idTipoDocumento=" + idTipoDocumento +
                ", nombre='" + nombre + '\'' +
                ", siglas='" + siglas + '\'' +
                ", idTipoCliente=" + idTipoCliente +
                '}';
    }
}
