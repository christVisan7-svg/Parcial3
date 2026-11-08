package model.core;

import model.producto.Producto;

public class DetallePedido {

    private Producto producto;
    private int cantidad;
    private double precioVentaAplicado;

    public DetallePedido(Producto producto, int cantidad) {
        this.producto = producto;
        this.cantidad = cantidad;
        this.precioVentaAplicado = producto.calcularPrecioVenta();
    }

    public DetallePedido(Producto producto, int cantidad, double precioVentaAplicado) {
        this.producto = producto;
        this.cantidad = cantidad;
        this.precioVentaAplicado = precioVentaAplicado;
    }

    public double calcularSubtotal() {
        double subtotal = precioVentaAplicado * cantidad;
        return subtotal;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public double getPrecioVentaAplicado() {
        return precioVentaAplicado;
    }

    public void setPrecioVentaAplicado(double precioVentaAplicado) {
        this.precioVentaAplicado = precioVentaAplicado;
    }
}

