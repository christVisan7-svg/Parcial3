package model.core;

import model.domicilio.EmpresaDomicilio;
import model.enums.EstadoPedido;
import model.enums.ModalidadEntrega;
import model.producto.Producto;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

    public class Pedido {

        private String codigo;
        private LocalDateTime fecha;
        private Cliente cliente;
        private ModalidadEntrega modalidadEntrega;
        private EstadoPedido estado;
        private List<DetallePedido> listaDetallePedido;
        private EmpresaDomicilio empresaDomicilio;
        private double distanciaKm;
        private double costoDomicilio;
        private double valorTotal;

        public Pedido(String codigo, LocalDateTime fecha, Cliente cliente,
                      ModalidadEntrega modalidadEntrega, double distanciaKm,
                      EmpresaDomicilio empresaDomicilio) {

            this.codigo = codigo;
            this.fecha = fecha;
            this.cliente = cliente;
            this.modalidadEntrega = modalidadEntrega;
            this.distanciaKm = distanciaKm;
            this.empresaDomicilio = empresaDomicilio;
            this.estado = EstadoPedido.PENDIENTE;
            this.listaDetallePedido = new ArrayList<>();
            this.costoDomicilio = 0;
            this.valorTotal = 0;
        }

        public String getCodigo() {
            return codigo;
        }

        public void setCodigo(String codigo) {
            this.codigo = codigo;
        }

        public LocalDateTime getFecha() {
            return fecha;
        }

        public void setFecha(LocalDateTime fecha) {
            this.fecha = fecha;
        }

        public Cliente getCliente() {
            return cliente;
        }

        public void setCliente(Cliente cliente) {
            this.cliente = cliente;
        }

        public ModalidadEntrega getModalidadEntrega() {
            return modalidadEntrega;
        }

        public void setModalidadEntrega(ModalidadEntrega modalidadEntrega) {
            this.modalidadEntrega = modalidadEntrega;
        }

        public EstadoPedido getEstado() {
            return estado;
        }

        public void setEstado(EstadoPedido estado) {
            this.estado = estado;
        }

        public List<DetallePedido> getListaDetallePedido() {
            return listaDetallePedido;
        }

        public void setListaDetallePedido(List<DetallePedido> listaDetallePedido) {
            this.listaDetallePedido = listaDetallePedido;
        }

        public EmpresaDomicilio getEmpresaDomicilio() {
            return empresaDomicilio;
        }

        public void setEmpresaDomicilio(EmpresaDomicilio empresaDomicilio) {
            this.empresaDomicilio = empresaDomicilio;
        }

        public double getDistanciaKm() {
            return distanciaKm;
        }

        public void setDistanciaKm(double distanciaKm) {
            this.distanciaKm = distanciaKm;
        }

        public double getCostoDomicilio() {
            return costoDomicilio;
        }

        public void setCostoDomicilio(double costoDomicilio) {
            this.costoDomicilio = costoDomicilio;
        }

        public double getValorTotal() {
            return valorTotal;
        }

        public void setValorTotal(double valorTotal) {
            this.valorTotal = valorTotal;
        }


        public boolean agregarDetalle(Producto producto, int cantidad) {
            boolean sePuedeAgregar = true;

            if (producto == null) {
                sePuedeAgregar = false;
            }
            if (cantidad <= 0) {
                sePuedeAgregar = false;
            }
            if (estado != EstadoPedido.PENDIENTE) {
                sePuedeAgregar = false;
            }

            if (sePuedeAgregar) {
                listaDetallePedido.add(new DetallePedido(producto, cantidad));
            }
            return sePuedeAgregar;
        }

        public double calcularValorProductos() {
            double valorProductos = 0;

            for (DetallePedido detalle : listaDetallePedido) {
                valorProductos += detalle.calcularSubtotal();
            }
            return valorProductos;
        }

        public double calcularValorTotal() {
            double total = calcularValorProductos();
            costoDomicilio = 0;

            if (modalidadEntrega == ModalidadEntrega.DOMICILIO && empresaDomicilio != null && empresaDomicilio.aceptaServicio(distanciaKm)) {
                costoDomicilio = empresaDomicilio.calcularCostoEnvio(distanciaKm);
                total += costoDomicilio;
            }

            valorTotal = total;
            return valorTotal;
        }

        public boolean actualizarEstado(EstadoPedido nuevoEstado) {
            boolean sePuedeActualizar = true;

            if (nuevoEstado == null) {
                sePuedeActualizar = false;

            } else if (estado == EstadoPedido.PENDIENTE) {
                if (nuevoEstado != EstadoPedido.CONFIRMADO && nuevoEstado != EstadoPedido.CANCELADO) {
                    sePuedeActualizar = false;
                }

            } else if (estado == EstadoPedido.CONFIRMADO) {
                if (nuevoEstado != EstadoPedido.ENTREGADO && nuevoEstado != EstadoPedido.CANCELADO) {
                    sePuedeActualizar = false;
                }
            } else {
                sePuedeActualizar = false;
            }

            if (sePuedeActualizar) {
                estado = nuevoEstado;
            }
            return sePuedeActualizar;
        }
    }