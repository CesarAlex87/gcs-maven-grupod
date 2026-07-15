---
title: "GCS Maven Project - Grupo D"
created: "2026-06-20"
status: "draft"
context: "university"
tags: ["coursework", "education", "university"]
---

# GCS Maven Project - Grupo D

## Project Overview
- **University:** Universidad de Guayaquil
- **Subject:** Gestión de la Configuración del Software
- **Professor:** Ph.D. Franklin Parrales Bravo
- **Group:** Grupo D

### Group Members
1. Tipán Antón César Alexander
2. Camillie Ayovi
3. Erick Córdova
4. Mateo Aguilar

## Project Structure

### Maven Configuration
- **Java Version:** 21
- **Maven Version:** 3.9.6
- **Group ID:** ec.edu.ug.gcs.grupod
- **Artifact ID:** maven-project
- **Version:** 1.0-SNAPSHOT

### Dependencies
- **JUnit 5 (Jupiter 5.11.0)** - Unit testing framework
- **Log4j2 (2.23.1)** - Logging framework
- **Apache Commons Lang3 (3.14.0)** - Utility functions
- **Maven Surefire Plugin (3.3.0)** - Test execution

### Source Code

#### Main Classes (`src/main/java/ec/edu/ug/gcs/grupod/`)
1. **App.java** - Main application demonstrating logging and utilities
2. **Calculator.java** - Calculator with operations: add, subtract, multiply, divide
3. **StringProcessor.java** - String manipulation using Commons Lang3

#### Test Classes (`src/test/java/ec/edu/ug/gcs/grupod/`)
1. **AppTest.java** - Application execution test
2. **CalculatorTest.java** - 15 test cases for calculator operations
3. **StringProcessorTest.java** - 24 test cases for string operations

### Configuration
- **Log4j2 Configuration:** `src/main/resources/log4j2.xml`
  - Console appender for console output
  - File appender for logging to `logs/gcs-maven-project.log`
  - Debug level for application packages

## Build Results

### Maven Lifecycle Execution

#### 1. Validate Phase
```
BUILD SUCCESS
Total time: 2.460 s
```

#### 2. Test Phase
```
Tests run: 41, Failures: 0, Errors: 0, Skipped: 0
Total time: 26.312 s
```

Tests breakdown:
- AppTest: 2 tests ✓
- CalculatorTest: 15 tests ✓
- StringProcessorTest: 24 tests ✓

#### 3. Package Phase
```
BUILD SUCCESS
JAR created: target/maven-project-1.0-SNAPSHOT.jar (7.7 KB)
Total time: Completed
```

#### 4. Install Phase
```
BUILD SUCCESS
Total time: 14.100 s
Installed to: ~/.m2/repository/ec/edu/ug/gcs/grupod/maven-project/1.0-SNAPSHOT/
```

## Artifact
- **JAR File:** `target/maven-project-1.0-SNAPSHOT.jar` (7.7 KB)
- **Location:** `/mnt/d/Ghosty/kb/university/2026-S1/GCS/maven-project/`

## All Lifecycle Outputs
- `output-validate.txt` - Validate phase output
- `output-test.txt` - Test phase output with full test logs
- `output-package.txt` - Package phase output
- `output-install.txt` - Install phase output

## Features

### Calculator
- ✓ Addition with positive, negative, and mixed numbers
- ✓ Subtraction
- ✓ Multiplication
- ✓ Division with error handling for division by zero
- ✓ Logging of all operations

### StringProcessor
- ✓ String capitalization (using Commons Lang3)
- ✓ String reversal
- ✓ Palindrome detection (case-insensitive, space-insensitive)
- ✓ Vowel counting
- ✓ Uppercase conversion
- ✓ Blank string detection
- ✓ Logging of all operations

## Verification Checklist
- ✓ Java 21 compiler configuration
- ✓ All dependencies properly declared
- ✓ Maven Surefire plugin configured
- ✓ 41 unit tests passing
- ✓ JAR file generated successfully
- ✓ Artifact installed to local repository
- ✓ Log4j2 configuration file created
- ✓ No compilation or runtime errors
- ✓ All Maven lifecycle phases successful
