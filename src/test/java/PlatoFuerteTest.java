import static org.junit.jupiter.api.Assertions.*;

import model.enums.TipoPreparacion;
import model.producto.PlatoFuerte;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class PlatoFuerteTest {

    private PlatoFuerte bandeja;
    private PlatoFuerte corteCrudo;

    @BeforeEach
    void setUp() {
        bandeja = new PlatoFuerte("PF01", "Bandeja Paisa", "Plato tradicional", 25000.0, 30, 600.0, TipoPreparacion.A_LA_PLANCHA, true);
        corteCrudo = new PlatoFuerte("PF02", "Corte Crudo", "Sin terminar", 20000.0, 5, 150.0, TipoPreparacion.ASADO, false);
    }

    @Test
    void calcularPrecioVentaConIncremento() {
        double resultado = bandeja.calcularPrecioVenta();
        assertEquals(30000.0, resultado, 0.01);
    }

    @Test
    void platoTradicionalCumpleCalidad() {
        boolean resultado = bandeja.cumpleCondicionesCalidad();
        assertTrue(resultado);
    }

    @Test
    void platoRechazadoPorCoccionIncompleta() {
        boolean resultado = corteCrudo.cumpleCondicionesCalidad();
        assertFalse(resultado);
    }

    @Test
    void platoRechazadoPorPorcionMuyPequena() {
        boolean resultado = corteCrudo.cumpleCondicionesCalidad();
        assertFalse(resultado);
    }

    @Test
    void platoRechazadoPorTiempoExcesivo() {
        PlatoFuerte platoDemorado = new PlatoFuerte("PF03", "Lechona", "Larga cocción", 30000.0, 90, 500.0, TipoPreparacion.HORNEADO, true);
        boolean resultado = platoDemorado.cumpleCondicionesCalidad();
        assertFalse(resultado);
    }
}