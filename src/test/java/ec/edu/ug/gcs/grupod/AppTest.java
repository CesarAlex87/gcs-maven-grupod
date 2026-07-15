package ec.edu.ug.gcs.grupod;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

/**
 * Unit test for App class.
 */
public class AppTest {

    /**
     * Test that app main runs without throwing exceptions.
     */
    @Test
    public void testAppMainExecutes() {
        assertDoesNotThrow(() -> App.main(new String[]{}));
    }

    /**
     * Basic sanity check.
     */
    @Test
    public void shouldAnswerWithTrue() {
        assertTrue(true);
    }
}
