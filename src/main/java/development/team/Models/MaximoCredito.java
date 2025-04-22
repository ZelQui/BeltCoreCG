package development.team.Models;

public class MaximoCredito {
    private int idMaximo;
    private double maxSaldoPendiente;
    private double maxCredito;

    public MaximoCredito() {
    }

    public MaximoCredito(int idMaximo, double maxCredito, double maxSaldoPendiente) {
        this.idMaximo = idMaximo;
        this.maxCredito = maxCredito;
        this.maxSaldoPendiente = maxSaldoPendiente;
    }

    public int getIdMaximo() {
        return idMaximo;
    }

    public void setIdMaximo(int idMaximo) {
        this.idMaximo = idMaximo;
    }

    public double getMaxSaldoPendiente() {
        return maxSaldoPendiente;
    }

    public void setMaxSaldoPendiente(double maxSaldoPendiente) {
        this.maxSaldoPendiente = maxSaldoPendiente;
    }

    public double getMaxCredito() {
        return maxCredito;
    }

    public void setMaxCredito(double maxCredito) {
        this.maxCredito = maxCredito;
    }

    @Override
    public String toString() {
        return "MaximoCredito{" +
                "idMaximo=" + idMaximo +
                ", maxSaldoPendiente=" + maxSaldoPendiente +
                ", maxCredito=" + maxCredito +
                '}';
    }
}
