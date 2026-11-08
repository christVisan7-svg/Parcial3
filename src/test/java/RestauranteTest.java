import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import model.core.Cliente;
import model.core.Pedido;
import model.domicilio.RapidoExpress;
import model.enums.EstadoPedido;
import model.enums.ModalidadEntrega;
import model.enums.TipoPreparacion;
import model.enums.TipoTemperatura;
import model.producto.Bebida;
import model.core.Restaurante;
import model.producto.PlatoFuerte;


import java.time.LocalDateTime;


public class RestauranteTest {

    private Restaurante restaurante;
    private Cliente cliente;
    private PlatoFuerte bandeja;
    private Bebida limonada;
    private PlatoFuerte platoMalo;

    @BeforeEach
    void setUp() {
        restaurante = new Restaurante("Sabor & Tradición", "900123456-1", "Calle 10 # 5-20");

        cliente = new Cliente("1092460096", "Cristo Escobar", "3001234567",
                "Cristo@correo.com", "Calle 1 # 2-3");

        bandeja = new PlatoFuerte("P01", "Bandeja paisa", "Plato típico", 20000, 30,
                500, TipoPreparacion.A_LA_PLANCHA, true);

        limonada = new Bebida("B01", "Limonada", "Limonada natural", 5000, 5,
                300, 5, TipoTemperatura.FRIA, false);

        platoMalo = new PlatoFuerte("P99", "Plato pequeño", "No cumple peso", 10000, 20,
                100, TipoPreparacion.FRITO, true);

        restaurante.registrarCliente(cliente);
        restaurante.registrarProducto(bandeja);
        restaurante.registrarProducto(limonada);
    }

    @Test
    void registrarProductoConCodigoDuplicadoDebeRechazarse() {
        boolean resultado = restaurante.registrarProducto(bandeja);

        assertFalse(resultado);
        assertEquals(2, restaurante.getListaProductos().size());
    }

    @Test
    void registrarClienteConDocumentoRepetidoDebeRechazarse() {
        boolean resultado = restaurante.registrarCliente(cliente);

        assertFalse(resultado);
        assertEquals(1, restaurante.getListaClientes().size());
    }

    @Test
    void productosRechazadosDebeIncluirSoloLosQueNoCumplenCalidad() {
        restaurante.registrarProducto(platoMalo);

        var rechazados = restaurante.consultarProductosRechazados();

        assertEquals(1, rechazados.size());
        assertTrue(rechazados.contains(platoMalo));
    }

    @Test
    void confirmarPedidoValidoDebeCambiarEstadoYGuardarloEnLosHistoriales() {
        Pedido pedido = new Pedido("PED-001", LocalDateTime.now(), cliente,
                ModalidadEntrega.RESTAURANTE, 0, null);
        pedido.agregarDetalle(bandeja, 2);
        restaurante.registrarPedido(pedido);

        boolean resultado = restaurante.confirmarPedido(pedido);

        assertTrue(resultado);
        assertEquals(EstadoPedido.CONFIRMADO, pedido.getEstado());
        assertEquals(1, cliente.getListaPedidos().size());
    }

    @Test
    void ingresosPropiosNoDebenIncluirElValorDelDomicilio() {
        Pedido enRestaurante = new Pedido("PED-001", LocalDateTime.now(), cliente,
                ModalidadEntrega.RESTAURANTE, 0, null);
        enRestaurante.agregarDetalle(bandeja, 2);
        restaurante.registrarPedido(enRestaurante);
        restaurante.confirmarPedido(enRestaurante);

        Pedido aDomicilio = new Pedido("PED-002", LocalDateTime.now(), cliente,
                ModalidadEntrega.DOMICILIO, 5, new RapidoExpress());
        aDomicilio.agregarDetalle(limonada, 1);
        restaurante.registrarPedido(aDomicilio);
        restaurante.confirmarPedido(aDomicilio);

        assertEquals(53500, restaurante.calcularIngresosPropiosRestaurante(), 0.01);
        assertEquals(12500, restaurante.calcularIngresosTotalesDomicilio(), 0.01);
    }
}