#!/usr/bin/env bash

SCRIPT_DIR="$(cd "$(dirname "${BASH_SOURCE[0]}")" >/dev/null 2>&1 && pwd)"

mvn install:install-file \
    -Dfile=$SCRIPT_DIR/../libs/tratto-oracle-grammar-0.0.1-SNAPSHOT.jar \
    -DgroupId=star.tratto \
    -DartifactId=tratto-oracle-grammar \
    -Dversion=0.0.1-SNAPSHOT \
    -Dpackaging=jar \
    -DgeneratePom=true