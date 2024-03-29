#!/bin/bash

set -e

run_benchark() {
   $JAVA_HOME/bin/java \
     -XX:+UseParallelGC \
     -Xmx32g -Xms32g \
     -Djava.security.properties=src/main/resources/jvm.java9.security \
     -Djava.library.path=/home/marschall/git/random-provider-benchmarks/target \
     -jar target/random-provider-benchmarks-${VERSION}.jar \
       $1 threads-$1.txt
}

main() {
  run_benchark 1
  run_benchark 4
  run_benchark 16
}

main

