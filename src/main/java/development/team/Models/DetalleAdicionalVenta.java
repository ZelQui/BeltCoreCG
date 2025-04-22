package development.team.Models;

public class DetalleAdicionalVenta {
    private int idDetalleAdicional;
    private int idVenta;
    private int idConceptoAdicional;
    private double monto;
    private String comentario;

    public DetalleAdicionalVenta() {}

    public DetalleAdicionalVenta(int idDetalleAdicional, int idVenta, int idConceptoAdicional, double monto, String comentario) {
        this.idDetalleAdicional = idDetalleAdicional;
        this.idVenta = idVenta;
        this.idConceptoAdicional = idConceptoAdicional;
        this.monto = monto;
        this.comentario = comentario;
    }

    public int getIdDetalleAdicional() {
        return idDetalleAdicional;
    }

    public void setIdDetalleAdicional(int idDetalleAdicional) {
        this.idDetalleAdicional = idDetalleAdicional;
    }

    public int getIdVenta() {
        return idVenta;
    }

    public void setIdVenta(int idVenta) {
        this.idVenta = idVenta;
    }

    public int getIdConceptoAdicional() {
        return idConceptoAdicional;
    }

    public void setIdConceptoAdicional(int idConceptoAdicional) {
        this.idConceptoAdicional = idConceptoAdicional;
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
                ", idVenta=" + idVenta +
                ", idConceptoAdicional=" + idConceptoAdicional +
                ", monto=" + monto +
                ", comentario='" + comentario + '\'' +
                '}';
    }
}
