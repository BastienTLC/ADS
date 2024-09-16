#!/bin/bash

JAR_FILE=$(find target -name "umu-ads-a1-1.0-SNAPSHOT.jar" | head -n 1)


if [ -z "$JAR_FILE" ]; then
    echo "error"
    exit 1
fi


ARGS="$@"

echo "Running with arguments: $ARGS"
java -jar "$JAR_FILE" $ARGS
