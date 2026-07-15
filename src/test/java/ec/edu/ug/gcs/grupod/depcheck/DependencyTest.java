package ec.edu.ug.gcs.grupod.depcheck;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("Dependency - parseo y comparacion de coordenadas GAV")
class DependencyTest {

    @Test
    @DisplayName("parsea una coordenada GAV valida")
    void parseValidCoordinate() {
        Dependency d = Dependency.parse("org.apache.commons:commons-lang3:3.14.0");
        assertEquals("org.apache.commons", d.getGroupId());
        assertEquals("commons-lang3", d.getArtifactId());
        assertEquals("3.14.0", d.getVersion());
        assertEquals("org.apache.commons:commons-lang3", d.ga());
    }

    @Test
    @DisplayName("recorta espacios alrededor de la coordenada")
    void parseTrimsWhitespace() {
        Dependency d = Dependency.parse("  g:a:1.0  ");
        assertEquals("g:a:1.0", d.toString());
    }

    @Test
    @DisplayName("rechaza coordenadas con numero de segmentos incorrecto")
    void parseRejectsBadArity() {
        assertThrows(IllegalArgumentException.class, () -> Dependency.parse("g:a"));
        assertThrows(IllegalArgumentException.class, () -> Dependency.parse("g:a:1:extra"));
    }

    @Test
    @DisplayName("rechaza coordenadas vacias o nulas")
    void parseRejectsBlank() {
        assertThrows(IllegalArgumentException.class, () -> Dependency.parse("   "));
        assertThrows(NullPointerException.class, () -> Dependency.parse(null));
    }

    @Test
    @DisplayName("equals y hashCode se basan en los tres componentes")
    void equalsAndHashCode() {
        Dependency a = Dependency.parse("g:a:1.0");
        Dependency b = new Dependency("g", "a", "1.0");
        Dependency c = Dependency.parse("g:a:2.0");
        assertEquals(a, b);
        assertEquals(a.hashCode(), b.hashCode());
        assertNotEquals(a, c);
    }
}
