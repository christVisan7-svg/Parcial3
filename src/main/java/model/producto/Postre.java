package model.producto;

public class Postre extends Producto {

    private double peso;
    private double temperaturaAlmacenamiento;
    private boolean requiereRefrigeracion;

    public Postre(String codigo, String nombre, String descripcion, double precioBase, int tiempoEstimadoPreparacion,
                  double peso, double temperaturaAlmacenamiento, boolean requiereRefrigeracion) {
        super(codigo, nombre, descripcion, precioBase, tiempoEstimadoPreparacion);
        this.peso = peso;
        this.temperaturaAlmacenamiento = temperaturaAlmacenamiento;
        this.requiereRefrigeracion = requiereRefrigeracion;
    }

    @Override
    public double calcularPrecioVenta() {
        double porcentaje = 0.15;
        if (requiereRefrigeracion) {
            porcentaje += 0.05;
        }
        return getPrecioBase() * (1 + porcentaje);
    }

    @Override
    public boolean cumpleCondicionesCalidad() {
        if (peso < 80 || peso > 500) {
            return false;
        }
        if (requiereRefrigeracion) {
            return temperaturaAlmacenamiento >= 0 && temperaturaAlmacenamiento <= 6;
        } else {
            return temperaturaAlmacenamiento >= 15 && temperaturaAlmacenamiento <= 30;
        }
    }

    public double getPeso() {
        return peso;
    }

    public void setPeso(double peso) {
        this.peso = peso;
    }

    public double getTemperaturaAlmacenamiento() {
        return temperaturaAlmacenamiento;
    }

    public void setTemperaturaAlmacenamiento(double temperaturaAlmacenamiento) {
        this.temperaturaAlmacenamiento = temperaturaAlmacenamiento;
    }

    public boolean isRequiereRefrigeracion() {
        return requiereRefrigeracion;
    }

    public void setRequiereRefrigeracion(boolean requiereRefrigeracion) {
        this.requiereRefrigeracion = requiereRefrigeracion;
    }
}