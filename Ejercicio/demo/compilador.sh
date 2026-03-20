#!/bin/bash

echo "Analizador Léxico con ANTLR"

echo "Limpiando proyecto con Maven..."
mvn clean

echo "Compilando proyecto con Maven..."
mvn package

echo "Compilación completada. Ejecutando análisis léxico..."
java -jar target/demo-1.0-jar-with-dependencies.jar ejemplo.txt
