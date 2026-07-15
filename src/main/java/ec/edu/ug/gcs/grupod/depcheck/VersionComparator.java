package ec.edu.ug.gcs.grupod.depcheck;

import java.util.Comparator;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;

/**
 * Compara versiones al estilo semantico (numerico por segmentos: 1.10.0 &gt; 1.2.3).
 * Ignora el sufijo cualificador tras un guion (p. ej. "-SNAPSHOT", "-RC1") para el orden.
 */
public final class VersionComparator implements Comparator<String> {

    public static final VersionComparator INSTANCE = new VersionComparator();

    @Override
    public int compare(String a, String b) {
        String[] pa = splitVersion(a);
        String[] pb = splitVersion(b);
        int len = Math.max(pa.length, pb.length);
        for (int i = 0; i < len; i++) {
            int na = i < pa.length ? NumberUtils.toInt(pa[i], 0) : 0;
            int nb = i < pb.length ? NumberUtils.toInt(pb[i], 0) : 0;
            if (na != nb) {
                return Integer.compare(na, nb);
            }
        }
        return 0;
    }

    private static String[] splitVersion(String version) {
        String core = StringUtils.substringBefore(version, "-");
        core = StringUtils.defaultIfBlank(core, version);
        String[] parts = StringUtils.split(core, '.');
        return parts != null ? parts : new String[0];
    }

    /** true si {@code a} es estrictamente anterior a {@code b}. */
    public static boolean lessThan(String a, String b) {
        return INSTANCE.compare(a, b) < 0;
    }
}
