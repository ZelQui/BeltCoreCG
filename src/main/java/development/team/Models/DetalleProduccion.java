package development.team.Models;

public class DetalleProduccion {
    private int idDetalle;
    private Produccion produccion;
    private Insumo insumo;
    private double cantidadUsada;

    public DetalleProduccion() {
    }

    public DetalleProduccion(int idDetalle, Produccion produccion, Insumo insumo, double cantidadUsada) {
        this.idDetalle = idDetalle;
        this.produccion = produccion;
        this.insumo = insumo;
        this.cantidadUsada = cantidadUsada;
    }

    public int getIdDetalle() {
        return idDetalle;
    }

    public void setIdDetalle(int idDetalle) {
        this.idDetalle = idDetalle;
    }

    public Produccion getProduccion() {
        return produccion;
    }

    public void setProduccion(Produccion produccion) {
        this.produccion = produccion;
    }

    public Insumo getInsumo() {
        return insumo;
    }

    public void setInsumo(Insumo insumo) {
        this.insumo = insumo;
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
                ", produccion=" + produccion +
                ", insumo=" + insumo +
                ", cantidadUsada=" + cantidadUsada +
                '}';
    }
}
