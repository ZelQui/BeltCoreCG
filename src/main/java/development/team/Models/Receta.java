package development.team.Models;

import java.math.BigDecimal;

public class Receta {
    private int idReceta;
    private Insumo insumo;
    private BigDecimal cantidadRequerida;
    private Producto producto;

    public Receta() {
    }

    public Receta(int idReceta, Insumo insumo, BigDecimal cantidadRequerida, Producto producto) {
        this.idReceta = idReceta;
        this.insumo = insumo;
        this.cantidadRequerida = cantidadRequerida;
        this.producto = producto;
    }

    public int getIdReceta() {
        return idReceta;
    }

    public void setIdReceta(int idReceta) {
        this.idReceta = idReceta;
    }

    public Insumo getInsumo() {
        return insumo;
    }

    public void setInsumo(Insumo insumo) {
        this.insumo = insumo;
    }

    public BigDecimal getCantidadRequerida() {
        return cantidadRequerida;
    }

    public void setCantidadRequerida(BigDecimal cantidadRequerida) {
        this.cantidadRequerida = cantidadRequerida;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    @Override
    public String toString() {
        return "Receta{" +
                "idReceta=" + idReceta +
                ", insumo=" + insumo +
                ", cantidadRequerida=" + cantidadRequerida +
                ", producto=" + producto +
                '}';
    }
}
