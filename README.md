# depcheck — Proyecto Final Maven (Grupo D)

**Materia:** Gestión de la Configuración del Software (GCSW)
**Docente:** Ph.D. Franklin Parrales Bravo
**Universidad de Guayaquil — Ingeniería en Software — 2026 Ciclo I**

## Integrantes (Grupo D)

- Tipán Antón César Alexander
- Camillie Ayovi
- Erick Córdova
- Mateo Aguilar

## Descripción

Escáner de vulnerabilidades en dependencias Maven. Lee una lista de coordenadas
Maven (`groupId:artifactId:version`), las cruza contra una base local de *advisories*
(CVE reales) y reporta las dependencias vulnerables con su severidad y la versión
en la que se corrige.

El proyecto demuestra la gestión completa del ciclo de vida de construcción con
Apache Maven: coordenadas GAV, gestión de dependencias externas, pruebas unitarias
con JUnit 5 y empaquetado/instalación del artefacto.

## Coordenadas del proyecto (GAV)

| Campo | Valor |
|-------|-------|
| `groupId` | `ec.edu.ug.gcs.grupod` |
| `artifactId` | `depcheck` |
| `version` | `1.0.0` |
| Java | 21 |

## Dependencias (declaradas en `pom.xml`)

| Dependencia | Versión | Scope | Propósito |
|-------------|---------|-------|-----------|
| `org.junit.jupiter:junit-jupiter` | 5.11.3 | `test` | Framework de pruebas unitarias (22 tests). No viaja en el artefacto final. |
| `org.apache.logging.log4j:log4j-core` (+ `log4j-api`) | 2.23.1 | compile | Logging estructurado: registra cada dependencia escaneada y emite ERROR/WARN por hallazgo según severidad (config en `src/main/resources/log4j2.xml`). |
| `org.apache.commons:commons-lang3` | 3.14.0 | compile | `Validate` (precondiciones al parsear GAV), `StringUtils.split` (parseo de coordenadas y del CSV de advisories), builders de equals/hashCode/toString. |

## Ciclo de vida Maven (comandos ejecutados)

```bash
mvn validate      # valida el POM y la estructura
mvn test          # compila y ejecuta las 22 pruebas unitarias
mvn package       # genera el uber-jar ejecutable target/depcheck-1.0.0.jar
mvn install       # publica el artefacto en el repositorio local ~/.m2
```

Las salidas reales de cada fase están capturadas como evidencia en
`output-validate.txt`, `output-test.txt`, `output-package.txt`,
`output-install.txt` y `output-run.txt`.

## Ejecución

```bash
# Con la lista de ejemplo embebida:
java -jar target/depcheck-1.0.0.jar

# Con tu propia lista de dependencias (una coordenada GAV por línea):
java -jar target/depcheck-1.0.0.jar mis-deps.txt
```

Código de salida: `0` sin hallazgos, `1` si hay al menos una dependencia vulnerable
(pensado para integrarse en un pipeline CI/CD).

## Reproducción

Requisitos: JDK 21 y Maven 3.9+.

```bash
git clone https://github.com/CesarAlex87/gcs-maven-grupod.git
cd gcs-maven-grupod
mvn install        # corre validate → test → package → install
java -jar target/depcheck-1.0.0.jar
```

## Estructura

```
depcheck/
├── pom.xml
├── src/main/java/ec/edu/ug/gcs/grupod/depcheck/
│   ├── App.java                  # CLI / punto de entrada
│   ├── Dependency.java           # value object GAV
│   ├── VersionComparator.java    # orden semántico de versiones
│   ├── Advisory.java             # aviso CVE
│   ├── AdvisoryDatabase.java     # carga la base local
│   ├── VulnerabilityScanner.java # motor de escaneo
│   ├── Finding.java / ScanResult.java / Severity.java
├── src/main/resources/
│   ├── advisories.txt            # base local de CVE (delimitada por '|')
│   ├── sample-deps.txt           # lista de ejemplo
│   └── log4j2.xml                # configuración de logging
├── src/test/java/.../depcheck/   # 4 clases de prueba, 22 tests
├── output-*.txt                  # evidencia del ciclo de vida Maven
└── docs/                         # Informe (PDF) y Manual de Operaciones (PDF)
```

## Documentación

- [`docs/Informe_Maven_depcheck_GrupoD.pdf`](docs/Informe_Maven_depcheck_GrupoD.pdf) — informe del proyecto (configuración, dependencias, comandos, pruebas).
- [`docs/Manual_Operaciones_depcheck_GrupoD.pdf`](docs/Manual_Operaciones_depcheck_GrupoD.pdf) — manual de operaciones.
