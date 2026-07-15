package ec.edu.ug.gcs.grupod;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Main application class demonstrating logging and utility usage.
 *
 * University: Universidad de Guayaquil
 * Subject: Gestión de la Configuración del Software
 * Professor: Ph.D. Franklin Parrales Bravo
 * Group: Grupo D
 *
 * Members:
 * 1. Tipán Antón César Alexander
 * 2. Camillie Ayovi
 * 3. Erick Córdova
 * 4. Mateo Aguilar
 */
public class App {
    private static final Logger logger = LogManager.getLogger(App.class);

    public static void main(String[] args) {
        logger.info("========================================");
        logger.info("GCS Maven Project - Grupo D");
        logger.info("========================================");

        logger.info("Starting application...");

        demonstrateCalculator();
        demonstrateStringProcessor();

        logger.info("Application completed successfully");
        logger.info("========================================");
    }

    private static void demonstrateCalculator() {
        logger.info("\n--- Calculator Demonstration ---");
        Calculator calc = new Calculator();

        logger.info("Testing basic operations:");
        double add = calc.add(10, 5);
        logger.info("10 + 5 = {}", add);

        double subtract = calc.subtract(10, 5);
        logger.info("10 - 5 = {}", subtract);

        double multiply = calc.multiply(10, 5);
        logger.info("10 * 5 = {}", multiply);

        double divide = calc.divide(10, 5);
        logger.info("10 / 5 = {}", divide);

        logger.info("Testing edge case (division by zero):");
        try {
            calc.divide(10, 0);
        } catch (IllegalArgumentException e) {
            logger.error("Caught expected exception: {}", e.getMessage());
        }
    }

    private static void demonstrateStringProcessor() {
        logger.info("\n--- StringProcessor Demonstration ---");
        StringProcessor processor = new StringProcessor();

        String testString = "hello world";
        logger.info("Testing with string: '{}'", testString);

        String capitalized = processor.capitalize(testString);
        logger.info("Capitalized: '{}'", capitalized);

        String reversed = processor.reverse(testString);
        logger.info("Reversed: '{}'", reversed);

        String upperCase = processor.toUpperCase(testString);
        logger.info("Uppercase: '{}'", upperCase);

        int vowelCount = processor.countVowels(testString);
        logger.info("Vowel count: {}", vowelCount);

        String palindromeTest = "racecar";
        boolean isPalin = processor.isPalindrome(palindromeTest);
        logger.info("Is '{}' a palindrome? {}", palindromeTest, isPalin);

        boolean isBlank = processor.isBlank("   ");
        logger.info("Is '   ' blank? {}", isBlank);
    }
}
