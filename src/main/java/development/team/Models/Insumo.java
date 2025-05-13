package development.team.Models;

import java.math.BigDecimal;

public class Insumo {
    private int idInsumo;
    private String nombre;
    private String descripcion;
    private int cantidadStock;
    private BigDecimal precioUnitario;
    private String unidadMedida;

    public Insumo() {}

    public int getIdInsumo() {
        return idInsumo;
    }

    public void setIdInsumo(int idInsumo) {
        this.idInsumo = idInsumo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getCantidadStock() {
        return cantidadStock;
    }

    public void setCantidadStock(int cantidadStock) {
        this.cantidadStock = cantidadStock;
    }

    public BigDecimal getPrecioUnitario() {return precioUnitario;
    }

    public void setPrecioUnitario(BigDecimal precioUnitario) {this.precioUnitario = precioUnitario;}

    public String getUnidadMedida() {
        return unidadMedida;
    }

    public void setUnidadMedida(String unidadMedida) {
        this.unidadMedida = unidadMedida;
    }

    @Override
    public String toString() {
        return "Insumos{" +
                "idInsumo=" + idInsumo +
                ", nombre='" + nombre + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", cantidadStock=" + cantidadStock +
                ", precioUnitario=" + precioUnitario +
                ", unidadMedida='" + unidadMedida + '\'' +
                '}';
    }
}
