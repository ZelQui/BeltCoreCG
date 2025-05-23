package development.team.DTO;

public class InsumoCantidadUnidad {
    private String insumo;
    private double cantidad;
    private String unidad;

    public InsumoCantidadUnidad(String insumo, double cantidad, String unidad) {
        this.insumo = insumo;
        this.cantidad = cantidad;
        this.unidad = unidad;
    }

    public String getInsumo() { return insumo; }
    public double getCantidad() { return cantidad; }
    public String getUnidad() { return unidad; }

    public void setInsumo(String insumo) { this.insumo = insumo; }
    public void setCantidad(double cantidad) { this.cantidad = cantidad; }
    public void setUnidad(String unidad) { this.unidad = unidad; }
}

