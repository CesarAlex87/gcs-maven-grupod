package ec.edu.ug.gcs.grupod.depcheck;

import org.apache.commons.lang3.StringUtils;

/**
 * Severidad de una vulnerabilidad, alineada con las bandas cualitativas de CVSS v3.1.
 * El {@code baseScore} es el umbral inferior de cada banda (referencia didactica).
 */
public enum Severity {
    CRITICAL(9.0),
    HIGH(7.0),
    MEDIUM(4.0),
    LOW(0.1),
    NONE(0.0);

    private final double baseScore;

    Severity(double baseScore) {
        this.baseScore = baseScore;
    }

    public double baseScore() {
        return baseScore;
    }

    /**
     * Convierte un texto (p. ej. "critica", "HIGH", "media") en su Severity.
     * Tolera espacios, mayusculas y las etiquetas en espanol usadas en la base local.
     */
    public static Severity fromString(String raw) {
        String value = StringUtils.upperCase(StringUtils.trimToEmpty(raw));
        switch (value) {
            case "CRITICAL":
            case "CRITICA":
                return CRITICAL;
            case "HIGH":
            case "ALTA":
                return HIGH;
            case "MEDIUM":
            case "MEDIA":
                return MEDIUM;
            case "LOW":
            case "BAJA":
                return LOW;
            default:
                return NONE;
        }
    }
}
