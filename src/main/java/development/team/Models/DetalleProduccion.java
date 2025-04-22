package development.team.Models;

public class DetalleProduccion {
    private int idDetalle;
    private int idProduccion;
    private int idInsumo;
    private double cantidadUsada;

    public DetalleProduccion() {
    }

    public DetalleProduccion(int idDetalle, int idProduccion, int idInsumo, double cantidadUsada) {
        this.idDetalle = idDetalle;
        this.idProduccion = idProduccion;
        this.idInsumo = idInsumo;
        this.cantidadUsada = cantidadUsada;
    }

    public int getIdDetalle() {
        return idDetalle;
    }

    public void setIdDetalle(int idDetalle) {
        this.idDetalle = idDetalle;
    }

    public int getIdProduccion() {
        return idProduccion;
    }

    public void setIdProduccion(int idProduccion) {
        this.idProduccion = idProduccion;
    }

    public int getIdInsumo() {
        return idInsumo;
    }

    public void setIdInsumo(int idInsumo) {
        this.idInsumo = idInsumo;
    }

    public double getCantidadUsada() {
        return cantidadUsada;
    }

    public void setCantidadUsada(double cantidadUsada) {
        this.cantidadUsada = cantidadUsada;
    }

    @Override
    public String toString() {
        return "DetalleProduccion{" +
                "idDetalle=" + idDetalle +
                ", idProduccion=" + idProduccion +
                ", idInsumo=" + idInsumo +
                ", cantidadUsada=" + cantidadUsada +
                '}';
    }
}
