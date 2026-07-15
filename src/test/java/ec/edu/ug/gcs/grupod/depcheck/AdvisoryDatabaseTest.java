package ec.edu.ug.gcs.grupod.depcheck;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;
import java.io.StringReader;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("AdvisoryDatabase / Advisory - carga y coincidencia")
class AdvisoryDatabaseTest {

    private static final String SAMPLE =
            "# comentario que debe ignorarse\n"
            + "\n"
            + "CVE-2021-44228 | org.apache.logging.log4j | log4j-core | 2.15.0 | CRITICA | Log4Shell RCE.\n"
            + "CVE-2015-6420  | commons-collections | commons-collections | 3.2.2 | ALTA | Deserializacion insegura.\n";

    @Test
    @DisplayName("carga los advisories ignorando comentarios y lineas vacias")
    void loadsSkippingCommentsAndBlanks() throws IOException {
        AdvisoryDatabase db = AdvisoryDatabase.load(new StringReader(SAMPLE));
        assertEquals(2, db.size());
    }

    @Test
    @DisplayName("el recurso por defecto embebido carga correctamente")
    void loadsDefaultResource() {
        AdvisoryDatabase db = AdvisoryDatabase.loadDefault();
        assertTrue(db.size() >= 5, "se esperaban varios advisories en la base por defecto");
    }

    @Test
    @DisplayName("advisory afecta una version anterior a la corregida (mismo GA)")
    void advisoryAffectsOlderVersion() {
        Advisory a = Advisory.parseLine(
                "CVE-2021-44228 | org.apache.logging.log4j | log4j-core | 2.15.0 | CRITICA | Log4Shell.");
        assertTrue(a.affects(Dependency.parse("org.apache.logging.log4j:log4j-core:2.14.1")));
        assertEquals(Severity.CRITICAL, a.getSeverity());
    }

    @Test
    @DisplayName("advisory NO afecta una version parcheada ni otro artefacto")
    void advisoryDoesNotAffectPatchedOrOther() {
        Advisory a = Advisory.parseLine(
                "CVE-2021-44228 | org.apache.logging.log4j | log4j-core | 2.15.0 | CRITICA | Log4Shell.");
        assertFalse(a.affects(Dependency.parse("org.apache.logging.log4j:log4j-core:2.23.1")));
        assertFalse(a.affects(Dependency.parse("org.apache.commons:commons-lang3:3.14.0")));
    }

    @Test
    @DisplayName("findFor devuelve los advisories coincidentes de la base")
    void findForReturnsMatches() throws IOException {
        AdvisoryDatabase db = AdvisoryDatabase.load(new StringReader(SAMPLE));
        assertEquals(1, db.findFor(
                Dependency.parse("commons-collections:commons-collections:3.2.1")).size());
        assertTrue(db.findFor(
                Dependency.parse("commons-collections:commons-collections:3.2.2")).isEmpty());
    }
}
