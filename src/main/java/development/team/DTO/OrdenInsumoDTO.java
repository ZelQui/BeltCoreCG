package development.team.DTO;

public class OrdenInsumoDTO {
    private int idDetalleCompra;
    private int cantidad;
    private int idInsumo;
    private String nombreInsumo;

    public OrdenInsumoDTO() {
    }

    public OrdenInsumoDTO(int idDetalleCompra, int cantidad, int idInsumo, String nombreInsumo) {
        this.idDetalleCompra = idDetalleCompra;
        this.cantidad = cantidad;
        this.idInsumo = idInsumo;
        this.nombreInsumo = nombreInsumo;
    }

    public int getIdDetalleCompra() {
        return idDetalleCompra;
    }

    public void setIdDetalleCompra(int idDetalleCompra) {
        this.idDetalleCompra = idDetalleCompra;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public int getIdInsumo() {
        return idInsumo;
    }

    public void setIdInsumo(int idInsumo) {
        this.idInsumo = idInsumo;
    }

    public String getNombreInsumo() {
        return nombreInsumo;
    }

    public void setNombreInsumo(String nombreInsumo) {
        this.nombreInsumo = nombreInsumo;
    }

    @Override
    public String toString() {
        return "OrdenInsumoDTO{" +
                "idDetalleCompra=" + idDetalleCompra +
                ", cantidad=" + cantidad +
                ", idInsumo=" + idInsumo +
                ", nombreInsumo='" + nombreInsumo + '\'' +
                '}';
    }
}
