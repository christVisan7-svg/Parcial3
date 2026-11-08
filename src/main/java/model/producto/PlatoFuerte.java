package model.producto;

import model.enums.TipoPreparacion;

public class PlatoFuerte extends Producto {

    private double peso;
    private TipoPreparacion tipoPreparacion;
    private boolean controlCoccionSuperado;

    public PlatoFuerte(String codigo, String nombre, String descripcion, double precioBase, int tiempoEstimadoPreparacion,
                       double peso, TipoPreparacion tipoPreparacion, boolean controlCoccionSuperado) {
        super(codigo, nombre, descripcion, precioBase, tiempoEstimadoPreparacion);
        this.peso = peso;
        this.tipoPreparacion = tipoPreparacion;
        this.controlCoccionSuperado = controlCoccionSuperado;
    }

    @Override
    public double calcularPrecioVenta() {
        return getPrecioBase() * 1.20;
    }

    @Override
    public boolean cumpleCondicionesCalidad() {
        return peso >= 200 && peso <= 900 &&
                getTiempoEstimadoPreparacion() >= 10 && getTiempoEstimadoPreparacion() <= 60 &&
                controlCoccionSuperado;
    }

    public double getPeso() {
        return peso;
    }

    public void setPeso(double peso) {
        this.peso = peso;
    }

    public TipoPreparacion getTipoPreparacion() {
        return tipoPreparacion;
    }

    public void setTipoPreparacion(TipoPreparacion tipoPreparacion) {
        this.tipoPreparacion = tipoPreparacion;
    }

    public boolean isControlCoccionSuperado() {
        return controlCoccionSuperado;
    }

    public void setControlCoccionSuperado(boolean controlCoccionSuperado) {
        this.controlCoccionSuperado = controlCoccionSuperado;
    }
}