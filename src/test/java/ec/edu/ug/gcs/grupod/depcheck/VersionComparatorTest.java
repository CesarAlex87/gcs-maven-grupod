package ec.edu.ug.gcs.grupod.depcheck;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

@DisplayName("VersionComparator - orden semantico de versiones")
class VersionComparatorTest {

    @Test
    @DisplayName("compara numericamente por segmento: 1.10.0 > 1.2.3")
    void comparesNumericallyNotLexically() {
        assertTrue(VersionComparator.INSTANCE.compare("1.10.0", "1.2.3") > 0);
        assertTrue(VersionComparator.INSTANCE.compare("1.2.3", "1.10.0") < 0);
    }

    @Test
    @DisplayName("versiones iguales devuelven 0")
    void equalVersions() {
        assertTrue(VersionComparator.INSTANCE.compare("2.5.1", "2.5.1") == 0);
    }

    @Test
    @DisplayName("longitudes distintas: 1.2 == 1.2.0 y 1.2 < 1.2.1")
    void differentLengths() {
        assertTrue(VersionComparator.INSTANCE.compare("1.2", "1.2.0") == 0);
        assertTrue(VersionComparator.lessThan("1.2", "1.2.1"));
    }

    @Test
    @DisplayName("ignora el sufijo cualificador tras el guion")
    void ignoresQualifier() {
        assertTrue(VersionComparator.INSTANCE.compare("1.0.0-SNAPSHOT", "1.0.0") == 0);
    }

    @ParameterizedTest(name = "{0} < {1}")
    @CsvSource({
            "2.14.1, 2.15.0",
            "1.9.0,  1.10.0",
            "3.2.1,  3.2.2",
            "1.30,   1.33"
    })
    @DisplayName("lessThan detecta versiones vulnerables frente a la corregida")
    void lessThanTrueCases(String vulnerable, String fixed) {
        assertTrue(VersionComparator.lessThan(vulnerable, fixed));
    }

    @Test
    @DisplayName("una version parcheada no es anterior a la corregida")
    void patchedIsNotLess() {
        assertFalse(VersionComparator.lessThan("2.23.1", "2.15.0"));
    }
}
