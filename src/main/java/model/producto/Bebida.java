package model.producto;

import model.enums.TipoTemperatura;

public class Bebida extends Producto {

    private double volumen;
    private double temperaturaServicio;
    private TipoTemperatura tipoTemperatura;
    private boolean preparacionEspecial;

    public Bebida(String codigo, String nombre, String descripcion, double precioBase, int tiempoEstimadoPreparacion,
                  double volumen, double temperaturaServicio, TipoTemperatura tipoTemperatura, boolean preparacionEspecial) {
        super(codigo, nombre, descripcion, precioBase, tiempoEstimadoPreparacion);
        this.volumen = volumen;
        this.temperaturaServicio = temperaturaServicio;
        this.tipoTemperatura = tipoTemperatura;
        this.preparacionEspecial = preparacionEspecial;
    }

    @Override
    public double calcularPrecioVenta() {
        double precio = getPrecioBase() * 1.10;
        if (preparacionEspecial) {
            precio += 3000.0;
        }
        return precio;
    }

    @Override
    public boolean cumpleCondicionesCalidad() {
        if (volumen < 150 || volumen > 1500) {
            return false;
        }
        if (tipoTemperatura == TipoTemperatura.FRIA) {
            return temperaturaServicio >= 0 && temperaturaServicio <= 10;
        } else {
            return temperaturaServicio >= 55 && temperaturaServicio <= 85;
        }
    }

    public double getVolumen() {
        return volumen;
    }

    public void setVolumen(double volumen) {
        this.volumen = volumen;
    }

    public double getTemperaturaServicio() {
        return temperaturaServicio;
    }

    public void setTemperaturaServicio(double temperaturaServicio) {
        this.temperaturaServicio = temperaturaServicio;
    }

    public TipoTemperatura getTipoTemperatura() {
        return tipoTemperatura;
    }

    public void setTipoTemperatura(TipoTemperatura tipoTemperatura) {
        this.tipoTemperatura = tipoTemperatura;
    }

    public boolean isPreparacionEspecial() {
        return preparacionEspecial;
    }

    public void setPreparacionEspecial(boolean preparacionEspecial) {
        this.preparacionEspecial = preparacionEspecial;
    }
}