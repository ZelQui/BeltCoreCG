package development.team.Models;

public class DetalleAdicionalVenta {
    private int idDetalleAdicional;
    private Venta venta;
    private ConceptoAdicional conceptoAdicional;
    private double monto;
    private String comentario;

    public DetalleAdicionalVenta() {}

    public DetalleAdicionalVenta(int idDetalleAdicional, Venta venta, ConceptoAdicional conceptoAdicional, double monto, String comentario) {
        this.idDetalleAdicional = idDetalleAdicional;
        this.venta = venta;
        this.conceptoAdicional = conceptoAdicional;
        this.monto = monto;
        this.comentario = comentario;
    }

    public int getIdDetalleAdicional() {
        return idDetalleAdicional;
    }

    public void setIdDetalleAdicional(int idDetalleAdicional) {
        this.idDetalleAdicional = idDetalleAdicional;
    }

    public Venta getVenta() {
        return venta;
    }

    public void setVenta(Venta venta) {
        this.venta = venta;
    }

    public ConceptoAdicional getConceptoAdicional() {
        return conceptoAdicional;
    }

    public void setConceptoAdicional(ConceptoAdicional conceptoAdicional) {
        this.conceptoAdicional = conceptoAdicional;
    }

    public double getMonto() {
        return monto;
    }

    public void setMonto(double monto) {
        this.monto = monto;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    @Override
    public String toString() {
        return "DetalleAdicionalVenta{" +
                "idDetalleAdicional=" + idDetalleAdicional +
                ", venta=" + venta +
                ", conceptoAdicional=" + conceptoAdicional +
                ", monto=" + monto +
                ", comentario='" + comentario + '\'' +
                '}';
    }
}
