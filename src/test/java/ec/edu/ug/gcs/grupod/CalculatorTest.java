package ec.edu.ug.gcs.grupod;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Unit tests for Calculator class.
 */
public class CalculatorTest {

    private Calculator calculator;

    @BeforeEach
    public void setUp() {
        calculator = new Calculator();
    }

    @Test
    public void testAddPositiveNumbers() {
        double result = calculator.add(10, 5);
        assertEquals(15, result, 0.001);
    }

    @Test
    public void testAddNegativeNumbers() {
        double result = calculator.add(-10, -5);
        assertEquals(-15, result, 0.001);
    }

    @Test
    public void testAddMixedNumbers() {
        double result = calculator.add(10, -5);
        assertEquals(5, result, 0.001);
    }

    @Test
    public void testAddZero() {
        double result = calculator.add(10, 0);
        assertEquals(10, result, 0.001);
    }

    @Test
    public void testSubtractPositiveNumbers() {
        double result = calculator.subtract(10, 5);
        assertEquals(5, result, 0.001);
    }

    @Test
    public void testSubtractNegativeResult() {
        double result = calculator.subtract(5, 10);
        assertEquals(-5, result, 0.001);
    }

    @Test
    public void testMultiplyPositiveNumbers() {
        double result = calculator.multiply(10, 5);
        assertEquals(50, result, 0.001);
    }

    @Test
    public void testMultiplyByZero() {
        double result = calculator.multiply(10, 0);
        assertEquals(0, result, 0.001);
    }

    @Test
    public void testMultiplyNegativeNumbers() {
        double result = calculator.multiply(-10, -5);
        assertEquals(50, result, 0.001);
    }

    @Test
    public void testDividePositiveNumbers() {
        double result = calculator.divide(10, 5);
        assertEquals(2, result, 0.001);
    }

    @Test
    public void testDivideByOne() {
        double result = calculator.divide(10, 1);
        assertEquals(10, result, 0.001);
    }

    @Test
    public void testDivideNegativeNumbers() {
        double result = calculator.divide(-10, -5);
        assertEquals(2, result, 0.001);
    }

    @Test
    public void testDivideByZeroThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> calculator.divide(10, 0));
    }

    @Test
    public void testDivideZeroByNumber() {
        double result = calculator.divide(0, 5);
        assertEquals(0, result, 0.001);
    }

    @Test
    public void testDivideDecimalNumbers() {
        double result = calculator.divide(7.5, 2.5);
        assertEquals(3, result, 0.001);
    }
}
