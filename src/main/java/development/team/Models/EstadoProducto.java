package development.team.Models;

public class EstadoProducto {
    private int idEstadoProducto;
    private int nombre;

    public EstadoProducto() {}

    public EstadoProducto(int idEstadoProducto, int nombre) {
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
