package aed;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class CoberturaTests {
    Cobertura cobertura = new Cobertura();

    @Test
    void testFizzBuzz() {
        // mult 3, mult 5
        assertEquals("FizzBuzz", cobertura.fizzBuzz(15));
        // mult 3, no mult 5
        assertEquals("Fizz", cobertura.fizzBuzz(3));
        // no mult 3, mult 5
        assertEquals("Buzz", cobertura.fizzBuzz(5));
        // no mult 3, no mult 5
        assertEquals("17", cobertura.fizzBuzz(17));
    }

    @Test
    void testNumeroCombinatorio() { 
        // k=n
        assertEquals(1, cobertura.numeroCombinatorio(0, 0));
        // n=0, k!=0
        assertEquals(0, cobertura.numeroCombinatorio(0, 3));
        // k=0, n!=0
        assertEquals(1, cobertura.numeroCombinatorio(4, 0));
        // n > k
        assertEquals(35, cobertura.numeroCombinatorio(7, 3));
        // n < k
        assertEquals(0, cobertura.numeroCombinatorio(3, 4));
    }

    @Test
    void testRepeticionesConsecutivas() {
        // assertTrue(false);
    }
}
