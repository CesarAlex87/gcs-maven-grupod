package ec.edu.ug.gcs.grupod.depcheck;

import org.apache.commons.lang3.Validate;

/**
 * Hallazgo: una dependencia escaneada que resulto afectada por un advisory concreto.
 */
public final class Finding {

    private final Dependency dependency;
    private final Advisory advisory;

    public Finding(Dependency dependency, Advisory advisory) {
        this.dependency = Validate.notNull(dependency, "dependency requerida");
        this.advisory = Validate.notNull(advisory, "advisory requerido");
    }

    public Dependency getDependency() {
        return dependency;
    }

    public Advisory getAdvisory() {
        return advisory;
    }

    public Severity getSeverity() {
        return advisory.getSeverity();
    }

    /** Linea legible para el reporte. */
    public String describe() {
        return String.format("[%s] %s -> %s : actualizar a >= %s",
                advisory.getSeverity(),
                dependency,
                advisory.getCveId(),
                advisory.getFixedVersion());
    }

    @Override
    public String toString() {
        return describe();
    }
}
