#!/bin/bash

set -e

VERSION=0.1.0-SNAPSHOT
GETENTROPY_VERSION=0.1.0-SNAPSHOT
GETRANDOM_VERSION=0.1.1
RDRAND_VERSION=0.1.0

export VERSION

extract_so() {
  if [ ! -f libgetentropy-provider-${GETENTROPY_VERSION}.jnilib ]; then
    unzip -p target/random-provider-benchmarks-${VERSION}.jar libgetentropy-provider-${GETENTROPY_VERSION}.jnilib > target/libgetentropy-provider-${GETENTROPY_VERSION}.jnilib
  fi
}

reaname_output() {
  mv threads-1.txt threads-1-$1.txt
  mv threads-4.txt threads-4-$1.txt
  mv threads-8.txt threads-8-$1.txt
}

main() {
  extract_so

#  export JAVA_HOME=${HOME}/bin/java/jdk-8
#  ./src/main/bash/run-benchmarks-8.sh
#  reaname_output java-8
#
#  export JAVA_HOME=${HOME}/bin/java/graalvm-8
#  ./src/main/bash/run-benchmarks-8.sh
#  reaname_output graal-8

export JAVA_HOME=/Library/Java/JavaVirtualMachines/zulu-17.jdk/Contents/Home
./src/main/bash/run-benchmarks-9.sh
reaname_output java-17

#  export JAVA_HOME=${HOME}l/bin/java/jdk-9
#  ./src/main/bash/run-benchmarks-graal9.sh
#  reaname_output graal-9
#
#  export JAVA_HOME=${HOME}/bin/java/openj9-9
#  ./src/main/bash/run-benchmarks-j9.sh
#  reaname_output ibm-9
}

main
