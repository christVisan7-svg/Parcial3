package model.domicilio;

public abstract class EmpresaDomicilio {

    private String nombre;
    private double tarifaBase;
    private double recargoPorKm;
    private double distanciaMaxima;

    public EmpresaDomicilio(String nombre, double tarifaBase, double recargoPorKm, double distanciaMaxima) {
        this.nombre = nombre;
        this.tarifaBase = tarifaBase;
        this.recargoPorKm = recargoPorKm;
        this.distanciaMaxima = distanciaMaxima;
    }

    public abstract double calcularCostoEnvio(double distanciaKm);
    public abstract boolean aceptaServicio(double distanciaKm);

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getTarifaBase() {
        return tarifaBase;
    }

    public void setTarifaBase(double tarifaBase) {
        this.tarifaBase = tarifaBase;
    }

    public double getRecargoPorKm() {
        return recargoPorKm;
    }

    public void setRecargoPorKm(double recargoPorKm) {
        this.recargoPorKm = recargoPorKm;
    }

    public double getDistanciaMaxima() {
        return distanciaMaxima;
    }

    public void setDistanciaMaxima(double distanciaMaxima) {
        this.distanciaMaxima = distanciaMaxima;
    }
}