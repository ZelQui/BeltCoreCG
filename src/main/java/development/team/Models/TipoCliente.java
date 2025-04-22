package development.team.Models;

public class TipoCliente {
    private int idTipoCliente;
    private String nombre;

    public TipoCliente() {
    }

    public TipoCliente(int idTipoCliente, String nombre) {
        this.idTipoCliente = idTipoCliente;
        this.nombre = nombre;
    }

    public int getIdTipoCliente() {
        return idTipoCliente;
    }

    public void setIdTipoCliente(int idTipoCliente) {
        this.idTipoCliente = idTipoCliente;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public String toString() {
        return "TipoCliente{" +
                "idTipoCliente=" + idTipoCliente +
                ", nombre='" + nombre + '\'' +
                '}';
    }
}
