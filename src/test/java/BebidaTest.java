import static org.junit.jupiter.api.Assertions.*;

import model.enums.TipoTemperatura;
import model.producto.Bebida;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class BebidaTest {

    private Bebida jugoLulo;
    private Bebida coctelCasa;

    @BeforeEach
    void setUp() {
        jugoLulo = new Bebida("BE01", "Jugo de Lulo", "Natural", 6000.0, 5, 400.0, 4.0, TipoTemperatura.FRIA, false);
        coctelCasa = new Bebida("BE02", "Coctel Casa", "Especial", 10000.0, 10, 300.0, 5.0, TipoTemperatura.FRIA, true);
    }

    @Test
    void calcularPrecioBebidaEstandar() {
        double resultado = jugoLulo.calcularPrecioVenta();
        assertEquals(6600.0, resultado, 0.01);
    }

    @Test
    void calcularPrecioBebidaConPreparacionEspecial() {
        double resultado = coctelCasa.calcularPrecioVenta();
        assertEquals(14000.0, resultado, 0.01);
    }

    @Test
    void bebidaFriaCumpleCalidad() {
        boolean resultado = jugoLulo.cumpleCondicionesCalidad();
        assertTrue(resultado);
    }

    @Test
    void bebidaCalienteCumpleCalidad() {
        Bebida tinto = new Bebida("BE03", "Tinto", "Caliente", 3000.0, 3, 200.0, 65.0, TipoTemperatura.CALIENTE, false);
        boolean resultado = tinto.cumpleCondicionesCalidad();
        assertTrue(resultado);
    }

    @Test
    void bebidaRechazadaPorVolumenInvalido() {
        Bebida shotPequeno = new Bebida("BE04", "Shot", "Muestra", 2000.0, 2, 50.0, 5.0, TipoTemperatura.FRIA, false);
        boolean resultado = shotPequeno.cumpleCondicionesCalidad();
        assertFalse(resultado);
    }
}