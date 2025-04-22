package development.team.Models;

public class EstadosCuenta {
    private int idEstadoCuenta;
    private String nombre;

    public EstadosCuenta() {}

    public EstadosCuenta(int idEstadoCuenta, String nombre) {
        this.idEstadoCuenta = idEstadoCuenta;
        this.nombre = nombre;
    }

    public int getIdEstadoCuenta() {
        return idEstadoCuenta;
    }

    public void setIdEstadoCuenta(int idEstadoCuenta) {
        this.idEstadoCuenta = idEstadoCuenta;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public String toString() {
        return "EstadosCuenta{" +
                "idEstadoCuenta=" + idEstadoCuenta +
                ", nombre='" + nombre + '\'' +
                '}';
    }
}
