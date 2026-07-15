package ec.edu.ug.gcs.grupod.depcheck;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.Validate;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

/**
 * Coordenada Maven (GAV): groupId:artifactId:version.
 * Value object inmutable. El parseo y las precondiciones se apoyan en Commons-Lang3.
 */
public final class Dependency {

    private final String groupId;
    private final String artifactId;
    private final String version;

    public Dependency(String groupId, String artifactId, String version) {
        this.groupId = Validate.notBlank(StringUtils.trim(groupId), "groupId no puede estar vacio");
        this.artifactId = Validate.notBlank(StringUtils.trim(artifactId), "artifactId no puede estar vacio");
        this.version = Validate.notBlank(StringUtils.trim(version), "version no puede estar vacia");
    }

    /**
     * Parsea una coordenada "groupId:artifactId:version".
     *
     * @throws NullPointerException     si es null
     * @throws IllegalArgumentException si esta vacia o no tiene exactamente 3 segmentos
     */
    public static Dependency parse(String coordinate) {
        Validate.notBlank(coordinate, "la coordenada no puede estar vacia");
        String[] parts = StringUtils.split(coordinate.trim(), ':');
        Validate.isTrue(parts != null && parts.length == 3,
                "coordenada GAV invalida (se esperaba groupId:artifactId:version): %s", coordinate);
        return new Dependency(parts[0], parts[1], parts[2]);
    }

    public String getGroupId() {
        return groupId;
    }

    public String getArtifactId() {
        return artifactId;
    }

    public String getVersion() {
        return version;
    }

    /** Clave groupId:artifactId (sin version), util para indexar advisories. */
    public String ga() {
        return groupId + ":" + artifactId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Dependency)) {
            return false;
        }
        Dependency other = (Dependency) o;
        return new EqualsBuilder()
                .append(groupId, other.groupId)
                .append(artifactId, other.artifactId)
                .append(version, other.version)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(groupId)
                .append(artifactId)
                .append(version)
                .toHashCode();
    }

    @Override
    public String toString() {
        return groupId + ":" + artifactId + ":" + version;
    }
}
