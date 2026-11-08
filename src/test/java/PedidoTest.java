
    import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;

    import model.core.Cliente;
    import model.core.Pedido;
    import model.domicilio.RapidoExpress;
    import model.enums.EstadoPedido;
    import model.enums.ModalidadEntrega;
    import model.enums.TipoPreparacion;
    import model.producto.PlatoFuerte;

    import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

    class PedidoTest {

        private Cliente cliente;
        private PlatoFuerte bandeja;
        private Pedido pedidoRestaurante;
        private Pedido pedidoDomicilio;

        @BeforeEach
        void setUp() {
            cliente = new Cliente("1092460096", "Ana Pérez", "3001234567",
                    "ana@correo.com", "Calle 1 # 2-3");


            bandeja = new PlatoFuerte("P01", "Bandeja paisa", "Plato típico", 20000, 30,
                    500, TipoPreparacion.A_LA_PLANCHA, true);

            pedidoRestaurante = new Pedido("PED-001", LocalDateTime.now(), cliente,
                    ModalidadEntrega.RESTAURANTE, 0, null);

            pedidoDomicilio = new Pedido("PED-002", LocalDateTime.now(), cliente,
                    ModalidadEntrega.DOMICILIO, 5, new RapidoExpress());
        }

        @Test
        void agregarDetalleValido() {
            boolean resultado = pedidoRestaurante.agregarDetalle(bandeja, 2);

            assertTrue(resultado);
            assertEquals(1, pedidoRestaurante.getListaDetallePedido().size());
        }

        @Test
        void agregarDetalleConCantidadCero() {
            boolean resultado = pedidoRestaurante.agregarDetalle(bandeja, 0);

            assertFalse(resultado);
            assertTrue(pedidoRestaurante.getListaDetallePedido().isEmpty());
        }

        @Test
        void valorTotalEnRestauranteNoIncluyeDomicilio() {
            pedidoRestaurante.agregarDetalle(bandeja, 2);

            assertEquals(48000, pedidoRestaurante.calcularValorTotal(), 0.01);
            assertEquals(0, pedidoRestaurante.getCostoDomicilio(), 0.01);
        }

        @Test
        void valorTotalConDomicilio() {
            pedidoDomicilio.agregarDetalle(bandeja, 2); // 48.000 + (5.000 + 1.500 x 5)

            assertEquals(12500, pedidoDomicilio.calcularValorTotal() - pedidoDomicilio.calcularValorProductos(), 0.01);
            assertEquals(60500, pedidoDomicilio.getValorTotal(), 0.01);
        }

        @Test
        void pedidoCanceladoNoPuedePasarAEntregado() {
            pedidoRestaurante.actualizarEstado(EstadoPedido.CANCELADO);

            boolean resultado = pedidoRestaurante.actualizarEstado(EstadoPedido.ENTREGADO);

            assertFalse(resultado);
            assertEquals(EstadoPedido.CANCELADO, pedidoRestaurante.getEstado());
        }
    }

