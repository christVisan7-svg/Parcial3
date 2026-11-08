import static org.junit.jupiter.api.Assertions.*;

import model.producto.Postre;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class PostreTest {

    private Postre tresLeches;
    private Postre brownie;

    @BeforeEach
    void setUp() {
        tresLeches = new Postre("PO01", "Tres Leches", "Helado", 8000.0, 15, 200.0, 4.0, true);
        brownie = new Postre("PO02", "Brownie", "Tradicional", 5000.0, 10, 150.0, 22.0, false);
    }

    @Test
    void calcularPrecioPostreRefrigerado() {
        double resultado = tresLeches.calcularPrecioVenta();
        assertEquals(9600.0, resultado, 0.01);
    }

    @Test
    void calcularPrecioPostreTemperaturaAmbiente() {
        double resultado = brownie.calcularPrecioVenta();
        assertEquals(5750.0, resultado, 0.01);
    }

    @Test
    void postreRefrigeradoCumpleCalidad() {
        boolean resultado = tresLeches.cumpleCondicionesCalidad();
        assertTrue(resultado);
    }

    @Test
    void postreDerretidoPorMalaTemperaturaSeRechaza() {
        Postre tresLechesDerretido = new Postre("PO03", "Tres Leches", "Derretido", 8000.0, 15, 200.0, 25.0, true);
        boolean resultado = tresLechesDerretido.cumpleCondicionesCalidad();
        assertFalse(resultado);
    }

    @Test
    void postreRechazadoPorPorcionMuyPequena() {
        Postre muestraBocado = new Postre("PO04", "Bocado", "Muestra", 2000.0, 5, 30.0, 20.0, false);
        boolean resultado = muestraBocado.cumpleCondicionesCalidad();
        assertFalse(resultado);
    }
}