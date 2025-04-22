package development.team.Models;

import java.math.BigDecimal;

public class Receta {
    private int idReceta;
    private int idInsumo;
    private BigDecimal cantidadRequerida;
    private int idProducto;

    public Receta() {
    }

    public Receta(int idReceta, int idInsumo, BigDecimal cantidadRequerida, int idProducto) {
        this.idReceta = idReceta;
        this.idInsumo = idInsumo;
        this.cantidadRequerida = cantidadRequerida;
        this.idProducto = idProducto;
    }

    public int getIdReceta() {
        return idReceta;
    }

    public void setIdReceta(int idReceta) {
        this.idReceta = idReceta;
    }

    public int getIdInsumo() {
        return idInsumo;
    }

    public void setIdInsumo(int idInsumo) {
        this.idInsumo = idInsumo;
    }

    public BigDecimal getCantidadRequerida() {
        return cantidadRequerida;
    }

    public void setCantidadRequerida(BigDecimal cantidadRequerida) {
        this.cantidadRequerida = cantidadRequerida;
    }

    public int getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(int idProducto) {
        this.idProducto = idProducto;
    }

    @Override
    public String toString() {
        return "Receta{" +
                "idReceta=" + idReceta +
                ", idInsumo=" + idInsumo +
                ", cantidadRequerida=" + cantidadRequerida +
                ", idProducto=" + idProducto +
                '}';
    }
}
