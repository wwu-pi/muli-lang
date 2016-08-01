#!/bin/bash
docker run --rm -v "$PWD":/usr/src/myapp -w /usr/src/myapp java:openjdk-6-jdk java -jar muli-lang.jar testfiles/MuliTest.muli
