package ec.edu.ug.gcs.grupod.depcheck;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.Validate;

/**
 * Aviso de seguridad (advisory) conocido para un artefacto Maven.
 * Modelo simplificado: la vulnerabilidad afecta a toda version anterior a {@code fixedVersion}.
 */
public final class Advisory {

    private final String cveId;
    private final String groupId;
    private final String artifactId;
    private final String fixedVersion;
    private final Severity severity;
    private final String description;

    public Advisory(String cveId, String groupId, String artifactId,
                    String fixedVersion, Severity severity, String description) {
        this.cveId = Validate.notBlank(cveId, "cveId requerido");
        this.groupId = Validate.notBlank(groupId, "groupId requerido");
        this.artifactId = Validate.notBlank(artifactId, "artifactId requerido");
        this.fixedVersion = Validate.notBlank(fixedVersion, "fixedVersion requerida");
        this.severity = Validate.notNull(severity, "severity requerida");
        this.description = StringUtils.trimToEmpty(description);
    }

    /**
     * Parsea una linea de la base local (delimitada por '|'):
     * cveId | groupId | artifactId | fixedVersion | severity | description
     */
    public static Advisory parseLine(String line) {
        String[] f = StringUtils.split(line, '|');
        Validate.isTrue(f != null && f.length >= 6,
                "linea de advisory invalida (se esperaban 6 campos): %s", line);
        return new Advisory(
                StringUtils.trim(f[0]),
                StringUtils.trim(f[1]),
                StringUtils.trim(f[2]),
                StringUtils.trim(f[3]),
                Severity.fromString(f[4]),
                StringUtils.trim(f[5]));
    }

    /** true si este advisory afecta a la dependencia dada (mismo GA y version &lt; fixedVersion). */
    public boolean affects(Dependency dep) {
        if (dep == null) {
            return false;
        }
        if (!StringUtils.equalsIgnoreCase(dep.getGroupId(), groupId)) {
            return false;
        }
        if (!StringUtils.equalsIgnoreCase(dep.getArtifactId(), artifactId)) {
            return false;
        }
        return VersionComparator.lessThan(dep.getVersion(), fixedVersion);
    }

    public String getCveId() {
        return cveId;
    }

    public String getGroupId() {
        return groupId;
    }

    public String getArtifactId() {
        return artifactId;
    }

    public String getFixedVersion() {
        return fixedVersion;
    }

    public Severity getSeverity() {
        return severity;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return cveId + " (" + severity + ") " + groupId + ":" + artifactId + " < " + fixedVersion;
    }
}
