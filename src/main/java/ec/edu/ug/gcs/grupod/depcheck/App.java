package ec.edu.ug.gcs.grupod.depcheck;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Punto de entrada CLI de depcheck.
 *
 * <p>Uso:
 * <pre>
 *   java -jar depcheck-1.0.0.jar [ruta/deps.txt]
 * </pre>
 * Si no se pasa archivo, se usa la lista de ejemplo embebida {@code /sample-deps.txt}.
 * Cada linea del archivo es una coordenada GAV {@code groupId:artifactId:version};
 * las lineas vacias y las que empiezan con '#' se ignoran.
 */
public final class App {

    private static final Logger LOG = LogManager.getLogger(App.class);
    private static final String SAMPLE_RESOURCE = "/sample-deps.txt";

    private App() {
    }

    public static void main(String[] args) {
        List<Dependency> deps;
        try {
            deps = (args.length > 0) ? readFromFile(Path.of(args[0])) : readFromSample();
        } catch (IOException e) {
            LOG.error("No se pudo leer la lista de dependencias: {}", e.getMessage());
            System.exit(2);
            return;
        }

        AdvisoryDatabase db = AdvisoryDatabase.loadDefault();
        VulnerabilityScanner scanner = new VulnerabilityScanner(db);
        ScanResult result = scanner.scan(deps);

        printReport(result);

        // Codigo de salida: 1 si hay al menos un hallazgo (util para pipelines CI/CD).
        System.exit(result.isVulnerable() ? 1 : 0);
    }

    private static void printReport(ScanResult result) {
        System.out.println();
        System.out.println("==================== REPORTE depcheck ====================");
        if (!result.isVulnerable()) {
            System.out.println("Sin vulnerabilidades conocidas en las dependencias analizadas.");
        } else {
            for (Finding finding : result.getFindings()) {
                System.out.println(finding.describe());
                System.out.println("        " + finding.getAdvisory().getDescription());
            }
        }
        System.out.println("----------------------------------------------------------");
        System.out.println(result.summary());
        System.out.println("==========================================================");
    }

    private static List<Dependency> readFromFile(Path path) throws IOException {
        try (BufferedReader br = Files.newBufferedReader(path, StandardCharsets.UTF_8)) {
            return parseCoordinates(br);
        }
    }

    private static List<Dependency> readFromSample() throws IOException {
        InputStream in = App.class.getResourceAsStream(SAMPLE_RESOURCE);
        if (in == null) {
            throw new IOException("no se encontro el recurso de ejemplo " + SAMPLE_RESOURCE);
        }
        try (BufferedReader br = new BufferedReader(new InputStreamReader(in, StandardCharsets.UTF_8))) {
            LOG.info("Sin archivo de entrada: usando lista de ejemplo embebida {}", SAMPLE_RESOURCE);
            return parseCoordinates(br);
        }
    }

    /** Lee coordenadas GAV, una por linea, ignorando comentarios ('#') y lineas vacias. */
    static List<Dependency> parseCoordinates(BufferedReader br) throws IOException {
        List<Dependency> deps = new ArrayList<>();
        String line;
        while ((line = br.readLine()) != null) {
            String trimmed = StringUtils.trim(line);
            if (StringUtils.isEmpty(trimmed) || StringUtils.startsWith(trimmed, "#")) {
                continue;
            }
            deps.add(Dependency.parse(trimmed));
        }
        return deps;
    }
}
