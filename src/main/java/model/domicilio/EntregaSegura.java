package model.domicilio;

public class EntregaSegura extends EmpresaDomicilio {

    private double recargoAdicional;
    private double umbralKm;

    public EntregaSegura() {
        super("EntregaSegura", 7000.0, 1000.0, 20.0);
        this.recargoAdicional = 3000.0;
        this.umbralKm = 5.0;
    }

    @Override
    public double calcularCostoEnvio(double distanciaKm) {
        double costo = getTarifaBase() + (getRecargoPorKm() * distanciaKm);
        if (distanciaKm > umbralKm) {
            costo += recargoAdicional;
        }
        return costo;
    }

    @Override
    public boolean aceptaServicio(double distanciaKm) {
        return distanciaKm > 0 && distanciaKm <= getDistanciaMaxima();
    }

    public double getRecargoAdicional() {
        return recargoAdicional;
    }

    public void setRecargoAdicional(double recargoAdicional) {
        this.recargoAdicional = recargoAdicional;
    }

    public double getUmbralKm() {
        return umbralKm;
    }

    public void setUmbralKm(double umbralKm) {
        this.umbralKm = umbralKm;
    }
}