package development.team.Models;

public class TipoDocumento {
    private int idTipoDocumento;
    private String nombre;
    private String siglas;
    private TipoCliente tipoCliente;

    public TipoDocumento() {
    }

    public TipoDocumento(int idTipoDocumento, String nombre, String siglas, TipoCliente tipoCliente) {
        this.idTipoDocumento = idTipoDocumento;
        this.nombre = nombre;
        this.siglas = siglas;
        this.tipoCliente = tipoCliente;
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

    public TipoCliente getTipoCliente() {
        return tipoCliente;
    }

    public void setTipoCliente(TipoCliente tipoCliente) {
        this.tipoCliente = tipoCliente;
    }

    @Override
    public String toString() {
        return "TipoDocumento{" +
                "idTipoDocumento=" + idTipoDocumento +
                ", nombre='" + nombre + '\'' +
                ", siglas='" + siglas + '\'' +
                ", tipoCliente=" + tipoCliente +
                '}';
    }
}
