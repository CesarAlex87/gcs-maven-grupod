package ec.edu.ug.gcs.grupod.depcheck;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Resultado de un escaneo: dependencias analizadas y hallazgos encontrados.
 */
public final class ScanResult {

    private final List<Dependency> scanned;
    private final List<Finding> findings;

    public ScanResult(List<Dependency> scanned, List<Finding> findings) {
        this.scanned = Collections.unmodifiableList(new ArrayList<>(scanned));
        this.findings = Collections.unmodifiableList(new ArrayList<>(findings));
    }

    public List<Dependency> getScanned() {
        return scanned;
    }

    public List<Finding> getFindings() {
        return findings;
    }

    public boolean isVulnerable() {
        return !findings.isEmpty();
    }

    public int scannedCount() {
        return scanned.size();
    }

    public int findingCount() {
        return findings.size();
    }

    /** Cuenta hallazgos de una severidad dada. */
    public long countBySeverity(Severity severity) {
        return findings.stream()
                .filter(f -> f.getSeverity() == severity)
                .count();
    }

    /** Numero de dependencias sin ningun hallazgo. */
    public long safeCount() {
        long affected = findings.stream()
                .map(Finding::getDependency)
                .distinct()
                .count();
        return scanned.size() - affected;
    }

    /** Resumen de una linea para logs / CLI. */
    public String summary() {
        return String.format(
                "Escaneadas %d | hallazgos %d (criticas %d, altas %d, medias %d, bajas %d) | seguras %d",
                scannedCount(),
                findingCount(),
                countBySeverity(Severity.CRITICAL),
                countBySeverity(Severity.HIGH),
                countBySeverity(Severity.MEDIUM),
                countBySeverity(Severity.LOW),
                safeCount());
    }
}
