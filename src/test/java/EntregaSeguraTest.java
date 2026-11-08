import static org.junit.jupiter.api.Assertions.*;

import model.domicilio.EntregaSegura;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class EntregaSeguraTest {

    private EntregaSegura entregaSegura;

    @BeforeEach
    void setUp() {
        entregaSegura = new EntregaSegura();
    }

    @Test
    void calcularCostoEnvioSinRecargo() {
        double resultado = entregaSegura.calcularCostoEnvio(4.0);
        assertEquals(11000.0, resultado, 0.01);
    }

    @Test
    void calcularCostoEnvioConRecargo() {
        double resultado = entregaSegura.calcularCostoEnvio(8.0);
        assertEquals(18000.0, resultado, 0.01);
    }

    @Test
    void aceptaServicioDistanciaValida() {
        boolean resultado = entregaSegura.aceptaServicio(15.0);
        assertTrue(resultado);
    }

    @Test
    void aceptaServicioDistanciaLimiteVeinteKm() {
        boolean resultado = entregaSegura.aceptaServicio(20.0);
        assertTrue(resultado);
    }

    @Test
    void rechazaServicioDistanciaExcedida() {
        boolean resultado = entregaSegura.aceptaServicio(25.0);
        assertFalse(resultado);
    }
}