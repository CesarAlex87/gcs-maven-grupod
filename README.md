# Proyecto Final Maven — Grupo D

**Materia:** Gestión de la Configuración del Software (GCSW)
**Docente:** Ph.D. Franklin Parrales Bravo
**Universidad de Guayaquil — Ingeniería en Software — 2026 Ciclo I**

## Integrantes (Grupo D)

- Tipán Antón César Alexander
- Camillie Ayovi
- Erick Córdova
- Mateo Aguilar

## Descripción

Proyecto Java creado con el arquetipo `maven-archetype-quickstart`, que demuestra la gestión completa del ciclo de vida de construcción con Apache Maven: configuración de coordenadas GAV, gestión de dependencias externas, pruebas unitarias con JUnit 5 y empaquetado/instalación del artefacto.

## Coordenadas del proyecto (GAV)

| Campo | Valor |
|-------|-------|
| `groupId` | `ec.edu.ug.gcs.grupod` |
| `artifactId` | `maven-project` |
| `version` | `1.0-SNAPSHOT` |
| Java | 21 |

## Dependencias (declaradas en `pom.xml`)

| Dependencia | Versión | Propósito | Scope |
|-------------|---------|-----------|-------|
| JUnit 5 (Jupiter: api, params, engine — vía `junit-bom`) | 5.11.0 | Framework de pruebas unitarias | `test` |
| Apache Log4j2 (`log4j-api`, `log4j-core`) | 2.23.1 | Logging estructurado (consola + archivo, config en `src/main/resources/log4j2.xml`) | compile |
| Apache Commons Lang3 | 3.14.0 | Utilidades de manipulación de cadenas (`StringUtils`) | compile |

Plugin de ejecución de pruebas: **Maven Surefire 3.3.0**.

## Estructura

```
├── pom.xml
├── src/
│   ├── main/
│   │   ├── java/ec/edu/ug/gcs/grupod/
│   │   │   ├── App.java              # Punto de entrada, integra las 3 dependencias
│   │   │   ├── Calculator.java       # Operaciones aritméticas con validación
│   │   │   └── StringProcessor.java  # Procesamiento de texto con Commons Lang3
│   │   └── resources/log4j2.xml
│   └── test/java/ec/edu/ug/gcs/grupod/
│       ├── AppTest.java              # 2 pruebas
│       ├── CalculatorTest.java       # 15 pruebas
│       └── StringProcessorTest.java  # 24 pruebas
├── docs/
│   ├── Informe_Maven_GrupoD.pdf      # Informe técnico del proyecto
│   └── Manual_Operaciones_GrupoD.pdf # Manual de operaciones
└── output-*.txt                      # Evidencia de cada fase del ciclo de vida
```

## Ciclo de vida ejecutado

```bash
mvn validate   # Verifica la configuración del proyecto      → BUILD SUCCESS
mvn test       # Compila y ejecuta las 41 pruebas unitarias   → Tests run: 41, Failures: 0, Errors: 0
mvn package    # Genera target/maven-project-1.0-SNAPSHOT.jar → BUILD SUCCESS
mvn install    # Instala el artefacto en ~/.m2/repository     → BUILD SUCCESS
```

Las salidas completas de cada fase están capturadas en `output-validate.txt`, `output-test.txt`, `output-package.txt` y `output-install.txt`.

## Cómo reproducir

Requisitos: JDK 21+ y Maven 3.9+.

```bash
git clone <este-repositorio>
cd gcs-maven-grupod
mvn install
java -cp target/classes:$(mvn -q dependency:build-classpath -Dmdep.outputFile=/dev/stdout) ec.edu.ug.gcs.grupod.App
```

## Documentación

- [Informe técnico (PDF)](docs/Informe_Maven_GrupoD.pdf) — configuración, dependencias documentadas, capturas de las 4 fases y análisis de vulnerabilidades de dependencias.
- [Manual de operaciones (PDF)](docs/Manual_Operaciones_GrupoD.pdf) — guía paso a paso de instalación, construcción y ejecución.
