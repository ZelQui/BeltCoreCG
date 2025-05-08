package development.team.Models;

public class EstadoProducto {
    private int idEstadoProducto;
    private String nombre;

    public EstadoProducto() {}

    public EstadoProducto(int idEstadoProducto, String nombre) {
        this.idEstadoProducto = idEstadoProducto;
        this.nombre = nombre;
    }

    public int getIdEstadoProducto() {
        return idEstadoProducto;
    }

    public void setIdEstadoProducto(int idEstadoProducto) {
        this.idEstadoProducto = idEstadoProducto;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
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
