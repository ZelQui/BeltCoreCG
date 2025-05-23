package development.team.Models;

import java.math.BigDecimal;

public class Receta {
    private int idReceta;
    private Producto producto;
    private Insumo insumo;
    private BigDecimal cantidadRequerida;

    public Receta() {
    }

    public Receta(int idReceta, Producto producto, Insumo insumo, BigDecimal cantidadRequerida) {
        this.idReceta = idReceta;
        this.producto = producto;
        this.insumo = insumo;
        this.cantidadRequerida = cantidadRequerida;
    }

    public int getIdReceta() {
        return idReceta;
    }

    public void setIdReceta(int idReceta) {
        this.idReceta = idReceta;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
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

    @Override
    public String toString() {
        return "Receta{" +
                "idReceta=" + idReceta +
                ", producto=" + producto +
                ", insumo=" + insumo +
                ", cantidadRequerida=" + cantidadRequerida +
                '}';
    }
}
