package model.core;

import model.domicilio.EmpresaDomicilio;
import model.domicilio.EntregaSegura;
import model.domicilio.RapidoExpress;
import model.enums.EstadoPedido;
import model.enums.ModalidadEntrega;
import model.producto.Bebida;
import model.producto.PlatoFuerte;
import model.producto.Postre;
import model.producto.Producto;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Restaurante {

    private String nombreComercial;
    private String nit;
    private String direccion;

    private List<Producto> listaProductos;
    private List<Cliente> listaClientes;
    private List<Pedido> listaPedidos;
    private List<EmpresaDomicilio> listaEmpresasDomicilio;

    public Restaurante(String nombreComercial, String nit, String direccion) {
        this.nombreComercial = nombreComercial;
        this.nit = nit;
        this.direccion = direccion;
        this.listaProductos = new ArrayList<>();
        this.listaClientes = new ArrayList<>();
        this.listaPedidos = new ArrayList<>();
        this.listaEmpresasDomicilio = new ArrayList<>();

        this.listaEmpresasDomicilio.add(new RapidoExpress());
        this.listaEmpresasDomicilio.add(new EntregaSegura());
    }

    public boolean registrarProducto(Producto nuevoProducto) {
        if (nuevoProducto == null || buscarProductoPorCodigo(nuevoProducto.getCodigo()) != null) {
            return false;
        }
        return listaProductos.add(nuevoProducto);
    }

    public boolean registrarCliente(Cliente nuevoCliente) {
        if (nuevoCliente == null || buscarClientePorDocumento(nuevoCliente.getNumeroDocumento()) != null) {
            return false;
        }
        return listaClientes.add(nuevoCliente);
    }

    public Cliente buscarClientePorDocumento(String numeroDocumentoBuscado) {
        for (Cliente clienteActual : listaClientes) {
            if (clienteActual.getNumeroDocumento().equalsIgnoreCase(numeroDocumentoBuscado)) {
                return clienteActual;
            }
        }
        return null;
    }

    public Producto buscarProductoPorCodigo(String codigoProductoBuscado) {
        for (Producto productoActual : listaProductos) {
            if (productoActual.getCodigo().equalsIgnoreCase(codigoProductoBuscado)) {
                return productoActual;
            }
        }
        return null;
    }

    public EmpresaDomicilio gestionarServicioDomicilio(double distanciaKm) {
        for (EmpresaDomicilio empresaDomicilioActual : listaEmpresasDomicilio) {
            if (empresaDomicilioActual.aceptaServicio(distanciaKm)) {
                return empresaDomicilioActual;
            }
        }
        return null;
    }

    public boolean confirmarPedido(Pedido pedidoAConfirmar) {
        if (pedidoAConfirmar == null) {
            return false;
        }

        for (Pedido pedidoRegistrado : listaPedidos) {
            if (pedidoRegistrado.getCodigo().equalsIgnoreCase(pedidoAConfirmar.getCodigo())) {
                return false;
            }
        }

        if (buscarClientePorDocumento(pedidoAConfirmar.getCliente().getNumeroDocumento()) == null) {
            return false;
        }

        if (pedidoAConfirmar.getListaDetallePedido().isEmpty()) {
            return false;
        }

        for (DetallePedido detalleActual : pedidoAConfirmar.getListaDetallePedido()) {
            Producto productoEnMenu = buscarProductoPorCodigo(detalleActual.getProducto().getCodigo());
            if (productoEnMenu == null || detalleActual.getCantidad() <= 0 || !productoEnMenu.cumpleCondicionesCalidad()) {
                return false;
            }
        }

        if (pedidoAConfirmar.getModalidadEntrega() == ModalidadEntrega.DOMICILIO) {
            if (pedidoAConfirmar.getEmpresaDomicilio() == null ||
                    !pedidoAConfirmar.getEmpresaDomicilio().aceptaServicio(pedidoAConfirmar.getDistanciaKm())) {
                return false;
            }
        }

        pedidoAConfirmar.calcularValorTotal();
        pedidoAConfirmar.actualizarEstado(EstadoPedido.CONFIRMADO);
        listaPedidos.add(pedidoAConfirmar);
        pedidoAConfirmar.getCliente().agregarPedidoAlHistorial(pedidoAConfirmar);
        return true;
    }

    public List<Producto> consultarProductosDisponibles() {
        List<Producto> productosDisponibles = new ArrayList<>();
        for (Producto productoActual : listaProductos) {
            if (productoActual.cumpleCondicionesCalidad()) {
                productosDisponibles.add(productoActual);
            }
        }
        return productosDisponibles;
    }

    public List<Producto> consultarProductosRechazados() {
        List<Producto> productosRechazados = new ArrayList<>();
        for (Producto productoActual : listaProductos) {
            if (!productoActual.cumpleCondicionesCalidad()) {
                productosRechazados.add(productoActual);
            }
        }
        return productosRechazados;
    }

    public List<Pedido> consultarHistorialCliente(String numeroDocumentoCliente) {
        Cliente clienteEncontrado = buscarClientePorDocumento(numeroDocumentoCliente);
        return clienteEncontrado != null ? clienteEncontrado.getListaPedidos() : new ArrayList<>();
    }

    public double calcularIngresosPropiosRestaurante() {
        double ingresosPropios = 0.0;
        for (Pedido pedidoActual : listaPedidos) {
            if (pedidoActual.getEstado() == EstadoPedido.CONFIRMADO || pedidoActual.getEstado() == EstadoPedido.ENTREGADO) {
                for (DetallePedido detalleActual : pedidoActual.getListaDetallePedido()) {
                    ingresosPropios += detalleActual.calcularSubtotal();
                }
            }
        }
        return ingresosPropios;
    }

    public double calcularIngresosTotalesDomicilio() {
        double ingresosTotalesDomicilio = 0.0;
        for (Pedido pedidoActual : listaPedidos) {
            if ((pedidoActual.getEstado() == EstadoPedido.CONFIRMADO || pedidoActual.getEstado() == EstadoPedido.ENTREGADO)
                    && pedidoActual.getModalidadEntrega() == ModalidadEntrega.DOMICILIO) {
                ingresosTotalesDomicilio += pedidoActual.getCostoDomicilio();
            }
        }
        return ingresosTotalesDomicilio;
    }

    public Producto obtenerProductoMasVendido() {
        Map<Producto, Integer> conteoProductosVendidos = new HashMap<>();
        for (Pedido pedidoActual : listaPedidos) {
            if (pedidoActual.getEstado() == EstadoPedido.CONFIRMADO || pedidoActual.getEstado() == EstadoPedido.ENTREGADO) {
                for (DetallePedido detalleActual : pedidoActual.getListaDetallePedido()) {
                    conteoProductosVendidos.put(
                            detalleActual.getProducto(),
                            conteoProductosVendidos.getOrDefault(detalleActual.getProducto(), 0) + detalleActual.getCantidad()
                    );
                }
            }
        }

        Producto productoMasVendido = null;
        int cantidadMaximaVendida = 0;
        for (Map.Entry<Producto, Integer> entradaProducto : conteoProductosVendidos.entrySet()) {
            if (entradaProducto.getValue() > cantidadMaximaVendida) {
                cantidadMaximaVendida = entradaProducto.getValue();
                productoMasVendido = entradaProducto.getKey();
            }
        }
        return productoMasVendido;
    }

    public String obtenerCategoriaMayorIngreso() {
        double ingresosPlatosFuertes = 0.0;
        double ingresosBebidas = 0.0;
        double ingresosPostres = 0.0;

        for (Pedido pedidoActual : listaPedidos) {
            if (pedidoActual.getEstado() == EstadoPedido.CONFIRMADO || pedidoActual.getEstado() == EstadoPedido.ENTREGADO) {
                for (DetallePedido detalleActual : pedidoActual.getListaDetallePedido()) {
                    Producto productoActual = detalleActual.getProducto();
                    double subtotalDetalle = detalleActual.calcularSubtotal();
                    if (productoActual instanceof PlatoFuerte) {
                        ingresosPlatosFuertes += subtotalDetalle;
                    } else if (productoActual instanceof Bebida) {
                        ingresosBebidas += subtotalDetalle;
                    } else if (productoActual instanceof Postre) {
                        ingresosPostres += subtotalDetalle;
                    }
                }
            }
        }

        if (ingresosPlatosFuertes >= ingresosBebidas && ingresosPlatosFuertes >= ingresosPostres) {
            return "Platos Fuertes ($" + ingresosPlatosFuertes + ")";
        } else if (ingresosBebidas >= ingresosPlatosFuertes && ingresosBebidas >= ingresosPostres) {
            return "Bebidas ($" + ingresosBebidas + ")";
        } else {
            return "Postres ($" + ingresosPostres + ")";
        }
    }

    public String getNombreComercial() {
        return nombreComercial;
    }

    public void setNombreComercial(String nombreComercial) {
        this.nombreComercial = nombreComercial;
    }

    public String getNit() {
        return nit;
    }

    public void setNit(String nit) {
        this.nit = nit;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public List<Producto> getListaProductos() {
        return listaProductos;
    }

    public void setListaProductos(List<Producto> listaProductos) {
        this.listaProductos = listaProductos;
    }

    public List<Cliente> getListaClientes() {
        return listaClientes;
    }

    public void setListaClientes(List<Cliente> listaClientes) {
        this.listaClientes = listaClientes;
    }

    public List<Pedido> getListaPedidos() {
        return listaPedidos;
    }

    public void setListaPedidos(List<Pedido> listaPedidos) {
        this.listaPedidos = listaPedidos;
    }

    public List<EmpresaDomicilio> getListaEmpresasDomicilio() {
        return listaEmpresasDomicilio;
    }

    public void setListaEmpresasDomicilio(List<EmpresaDomicilio> listaEmpresasDomicilio) {
        this.listaEmpresasDomicilio = listaEmpresasDomicilio;
    }
}