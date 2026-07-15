package ec.edu.ug.gcs.grupod;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Unit tests for StringProcessor class.
 */
public class StringProcessorTest {

    private StringProcessor processor;

    @BeforeEach
    public void setUp() {
        processor = new StringProcessor();
    }

    @Test
    public void testCapitalizeSimpleString() {
        String result = processor.capitalize("hello");
        assertEquals("Hello", result);
    }

    @Test
    public void testCapitalizeMultipleWords() {
        String result = processor.capitalize("hello world");
        assertEquals("Hello world", result);
    }

    @Test
    public void testCapitalizeEmptyString() {
        String result = processor.capitalize("");
        assertEquals("", result);
    }

    @Test
    public void testCapitalizeNull() {
        String result = processor.capitalize(null);
        assertNull(result);
    }

    @Test
    public void testReverseSimpleString() {
        String result = processor.reverse("hello");
        assertEquals("olleh", result);
    }

    @Test
    public void testReverseMultipleWords() {
        String result = processor.reverse("hello world");
        assertEquals("dlrow olleh", result);
    }

    @Test
    public void testReverseNull() {
        String result = processor.reverse(null);
        assertNull(result);
    }

    @Test
    public void testIsPalindromeTrue() {
        boolean result = processor.isPalindrome("racecar");
        assertTrue(result);
    }

    @Test
    public void testIsPalindromeWithSpaces() {
        boolean result = processor.isPalindrome("a man a plan a canal panama");
        assertTrue(result);
    }

    @Test
    public void testIsPalindromeFalse() {
        boolean result = processor.isPalindrome("hello");
        assertFalse(result);
    }

    @Test
    public void testIsPalindromeEmptyString() {
        boolean result = processor.isPalindrome("");
        assertFalse(result);
    }

    @Test
    public void testIsPalindromeNull() {
        boolean result = processor.isPalindrome(null);
        assertFalse(result);
    }

    @Test
    public void testCountVowelsSimpleString() {
        int result = processor.countVowels("hello");
        assertEquals(2, result);
    }

    @Test
    public void testCountVowelsMultipleWords() {
        int result = processor.countVowels("hello world");
        assertEquals(3, result);
    }

    @Test
    public void testCountVowelsNoVowels() {
        int result = processor.countVowels("xyz");
        assertEquals(0, result);
    }

    @Test
    public void testCountVowelsNull() {
        int result = processor.countVowels(null);
        assertEquals(0, result);
    }

    @Test
    public void testToUpperCaseSimple() {
        String result = processor.toUpperCase("hello");
        assertEquals("HELLO", result);
    }

    @Test
    public void testToUpperCaseWithNumbers() {
        String result = processor.toUpperCase("hello123");
        assertEquals("HELLO123", result);
    }

    @Test
    public void testToUpperCaseNull() {
        String result = processor.toUpperCase(null);
        assertNull(result);
    }

    @Test
    public void testIsBlankWithSpaces() {
        boolean result = processor.isBlank("   ");
        assertTrue(result);
    }

    @Test
    public void testIsBlankEmptyString() {
        boolean result = processor.isBlank("");
        assertTrue(result);
    }

    @Test
    public void testIsBlankNull() {
        boolean result = processor.isBlank(null);
        assertTrue(result);
    }

    @Test
    public void testIsBlankWithText() {
        boolean result = processor.isBlank("hello");
        assertFalse(result);
    }

    @Test
    public void testIsBlankWithTextAndSpaces() {
        boolean result = processor.isBlank(" hello ");
        assertFalse(result);
    }
}
