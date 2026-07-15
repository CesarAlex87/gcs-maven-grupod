package ec.edu.ug.gcs.grupod;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Calculator class providing basic arithmetic operations.
 * Handles edge cases like division by zero.
 */
public class Calculator {
    private static final Logger logger = LogManager.getLogger(Calculator.class);

    /**
     * Adds two numbers.
     *
     * @param a first number
     * @param b second number
     * @return sum of a and b
     */
    public double add(double a, double b) {
        double result = a + b;
        logger.info("add({}) = {}", String.format("%.2f, %.2f", a, b), result);
        return result;
    }

    /**
     * Subtracts b from a.
     *
     * @param a minuend
     * @param b subtrahend
     * @return difference of a and b
     */
    public double subtract(double a, double b) {
        double result = a - b;
        logger.info("subtract({}) = {}", String.format("%.2f, %.2f", a, b), result);
        return result;
    }

    /**
     * Multiplies two numbers.
     *
     * @param a first number
     * @param b second number
     * @return product of a and b
     */
    public double multiply(double a, double b) {
        double result = a * b;
        logger.info("multiply({}) = {}", String.format("%.2f, %.2f", a, b), result);
        return result;
    }

    /**
     * Divides a by b.
     *
     * @param a dividend
     * @param b divisor
     * @return quotient of a divided by b
     * @throws IllegalArgumentException if divisor is zero
     */
    public double divide(double a, double b) {
        if (b == 0) {
            logger.error("Division by zero attempted: {} / {}", a, b);
            throw new IllegalArgumentException("Divisor cannot be zero");
        }
        double result = a / b;
        logger.info("divide({}) = {}", String.format("%.2f, %.2f", a, b), result);
        return result;
    }
}
