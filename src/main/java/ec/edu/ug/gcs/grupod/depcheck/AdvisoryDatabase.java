package ec.edu.ug.gcs.grupod.depcheck;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UncheckedIOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.Validate;

/**
 * Base local de advisories. Se carga desde el recurso empaquetado
 * {@code /advisories.txt} (delimitado por '|', con lineas de comentario que inician en '#').
 */
public final class AdvisoryDatabase {

    /** Recurso por defecto embebido en el jar. */
    public static final String DEFAULT_RESOURCE = "/advisories.txt";

    private final List<Advisory> advisories;

    public AdvisoryDatabase(List<Advisory> advisories) {
        Validate.notNull(advisories, "la lista de advisories no puede ser null");
        this.advisories = Collections.unmodifiableList(new ArrayList<>(advisories));
    }

    /** Carga la base por defecto embebida en el classpath. */
    public static AdvisoryDatabase loadDefault() {
        InputStream in = AdvisoryDatabase.class.getResourceAsStream(DEFAULT_RESOURCE);
        Validate.notNull(in, "no se encontro el recurso %s en el classpath", DEFAULT_RESOURCE);
        try (Reader reader = new InputStreamReader(in, StandardCharsets.UTF_8)) {
            return load(reader);
        } catch (IOException e) {
            throw new UncheckedIOException("error leyendo la base de advisories", e);
        }
    }

    /** Carga advisories desde un Reader (una linea por advisory). */
    public static AdvisoryDatabase load(Reader reader) throws IOException {
        List<Advisory> parsed = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(reader)) {
            String line;
            while ((line = br.readLine()) != null) {
                String trimmed = StringUtils.trim(line);
                if (StringUtils.isEmpty(trimmed) || StringUtils.startsWith(trimmed, "#")) {
                    continue;
                }
                parsed.add(Advisory.parseLine(trimmed));
            }
        }
        return new AdvisoryDatabase(parsed);
    }

    /** Devuelve todos los advisories que afectan a la dependencia dada. */
    public List<Advisory> findFor(Dependency dep) {
        List<Advisory> matches = new ArrayList<>();
        for (Advisory advisory : advisories) {
            if (advisory.affects(dep)) {
                matches.add(advisory);
            }
        }
        return matches;
    }

    public int size() {
        return advisories.size();
    }

    public List<Advisory> all() {
        return advisories;
    }
}
