package development.team.Models;

public class EstadosProducto {
    private int idEstadoProducto;
    private int nombre;

    public EstadosProducto() {}

    public EstadosProducto(int idEstadoProducto, int nombre) {
        this.idEstadoProducto = idEstadoProducto;
        this.nombre = nombre;
    }

    public int getIdEstadoProducto() {
        return idEstadoProducto;
    }

    public void setIdEstadoProducto(int idEstadoProducto) {
        this.idEstadoProducto = idEstadoProducto;
    }

    public int getNombre() {
        return nombre;
    }

    public void setNombre(int nombre) {
        this.nombre = nombre;
    }

    @Override
    public String toString() {
        return "EstadosProducto{" +
                "idEstadoProducto=" + idEstadoProducto +
                ", nombre=" + nombre +
                '}';
    }
}
