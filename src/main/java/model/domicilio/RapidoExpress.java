package model.domicilio;

public class RapidoExpress extends EmpresaDomicilio {

    public RapidoExpress() {
        super("RápidoExpress", 5000.0, 1500.0, 12.0);
    }

    @Override
    public double calcularCostoEnvio(double distanciaKm) {
        return getTarifaBase() + (getRecargoPorKm() * distanciaKm);
    }

    @Override
    public boolean aceptaServicio(double distanciaKm) {
        return distanciaKm > 0 && distanciaKm <= getDistanciaMaxima();
    }
}