
    import static org.junit.jupiter.api.Assertions.*;

    import org.junit.jupiter.api.BeforeEach;
    import org.junit.jupiter.api.Test;

    import java.time.LocalDateTime;
    import java.util.List;

    import model.core.Cliente;
    import model.core.Pedido;
    import model.enums.ModalidadEntrega;

    public class ClienteTest {

        private Cliente cliente;
        private Pedido pedido1;
        private Pedido pedido2;

        @BeforeEach
        void setUp() {
            cliente = new Cliente("1092460096", "Juanpa", "3001234567",
                    "Juanpalo@gmail.com", "Calle 1 # 2-3");

            pedido1 = new Pedido("PED-001", LocalDateTime.now(), cliente,
                    ModalidadEntrega.RESTAURANTE, 0, null);
            pedido2 = new Pedido("PED-002", LocalDateTime.now(), cliente,
                    ModalidadEntrega.RESTAURANTE, 0, null);
        }

        @Test
        void clienteNuevoHistorialVacio() {
            assertTrue(cliente.getListaPedidos().isEmpty());
        }

        @Test
        void agregarPedidoValidoEnHistorial() {
            boolean resultado = cliente.agregarPedidoAlHistorial(pedido1);

            assertTrue(resultado);
            assertEquals(1, cliente.getListaPedidos().size());
        }

        @Test
        void pedidoConCodigoDuplicadoDebeRechazarse() {
            cliente.agregarPedidoAlHistorial(pedido1);

            boolean resultado = cliente.agregarPedidoAlHistorial(pedido1);

            assertFalse(resultado);
            assertEquals(1, cliente.getListaPedidos().size());
        }

        @Test
        void pedidoNuloDebeRechazarse() {
            boolean resultado = cliente.agregarPedidoAlHistorial(null);

            assertFalse(resultado);
            assertTrue(cliente.getListaPedidos().isEmpty());
        }

        @Test
        void historialDebeDevolverLosPedidosAgregados() {
            cliente.agregarPedidoAlHistorial(pedido1);
            cliente.agregarPedidoAlHistorial(pedido2);

            List<Pedido> historial = cliente.consultarHistorialCompras();

            assertEquals(2, historial.size());
            assertTrue(historial.contains(pedido1));
            assertTrue(historial.contains(pedido2));
        }
    }

