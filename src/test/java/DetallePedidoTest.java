import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class DetallePedidoTest {

    private PlatoFuerte bandeja;

    @BeforeEach
    void setUp() {
        // precio base 20.000 -> precio de venta 24.000 (+20 %)
        bandeja = new PlatoFuerte("P01", "Bandeja paisa", "Plato típico", 20000, 30,
                500, TipoPreparacion.A_LA_PLANCHA, true);
    }

    @Test
    void constructorDebeGuardarProductoYCantidad() {
        DetallePedido detalle = new DetallePedido(bandeja, 2);

        assertSame(bandeja, detalle.getProducto());
        assertEquals(2, detalle.getCantidad());
    }

    @Test
    void precioAplicadoDebeSerElPrecioDeVentaDelProducto() {
        DetallePedido detalle = new DetallePedido(bandeja, 2);

        assertEquals(24000, detalle.getPrecioVentaAplicado(), 0.01);
    }

    @Test
    void subtotalConCantidadUnoEsElPrecioDeVenta() {
        DetallePedido detalle = new DetallePedido(bandeja, 1);

        assertEquals(24000, detalle.calcularSubtotal(), 0.01);
    }

    @Test
    void subtotalConCantidadTresMultiplicaElPrecio() {
        DetallePedido detalle = new DetallePedido(bandeja, 3);

        assertEquals(72000, detalle.calcularSubtotal(), 0.01);
    }

    @Test
    void precioAplicadoNoCambiaSiDespuesCambiaElPrecioBase() {
        DetallePedido detalle = new DetallePedido(bandeja, 2);

        bandeja.setPrecioBase(30000); // el producto sube de precio después del pedido

        assertEquals(24000, detalle.getPrecioVentaAplicado(), 0.01);
        assertEquals(48000, detalle.calcularSubtotal(), 0.01);
    }
}
