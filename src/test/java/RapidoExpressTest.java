import static org.junit.jupiter.api.Assertions.*;

import model.domicilio.RapidoExpress;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class RapidoExpressTest {

    private RapidoExpress rapidoExpress;

    @BeforeEach
    void setUp() {
        rapidoExpress = new RapidoExpress();
    }

    @Test
    void calcularCostoEnvioCincoKm() {
        double resultado = rapidoExpress.calcularCostoEnvio(5.0);
        assertEquals(12500.0, resultado, 0.01);
    }

    @Test
    void aceptaServicioDistanciaValida() {
        boolean resultado = rapidoExpress.aceptaServicio(8.0);
        assertTrue(resultado);
    }

    @Test
    void aceptaServicioDistanciaLimiteDoceKm() {
        boolean resultado = rapidoExpress.aceptaServicio(12.0);
        assertTrue(resultado);
    }

    @Test
    void rechazaServicioDistanciaExcedida() {
        boolean resultado = rapidoExpress.aceptaServicio(15.0);
        assertFalse(resultado);
    }

    @Test
    void rechazaServicioDistanciaCero() {
        boolean resultado = rapidoExpress.aceptaServicio(0.0);
        assertFalse(resultado);
    }
}