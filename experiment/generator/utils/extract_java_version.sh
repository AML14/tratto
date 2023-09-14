#!/bin/bash

# Specify the path to the .pom file
POM_FILE="/tmp/csv_1_buggy/pom.xml"

# Search for the <maven.compiler.source> tag and extract its value
JAVA_VERSION=$(grep -o '<maven.compile.source>[^<]*</maven.compile.source>' "$POM_FILE" | sed -e 's/<maven.compile.source>//;s/<\/maven.compile.source>//')

if [ -n "$JAVA_VERSION" ]; then
  echo "Java version: $JAVA_VERSION"
else
  echo "Java version not found in the .pom file."
fi