package development.team.Models;

public class TipoVenta {
    private int idTipoVenta;
    private String nombre;

    public TipoVenta() {
    }

    public TipoVenta(int idTipoVenta, String nombre) {
        this.idTipoVenta = idTipoVenta;
        this.nombre = nombre;
    }

    public int getIdTipoVenta() {
        return idTipoVenta;
    }

    public void setIdTipoVenta(int idTipoVenta) {
        this.idTipoVenta = idTipoVenta;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public String toString() {
        return "TipoVenta{" +
                "idTipoVenta=" + idTipoVenta +
                ", nombre='" + nombre + '\'' +
                '}';
    }
}
