

JAR_FILE=$(find target -name "gRpcService-1.0-SNAPSHOT.jar" | head -n 1)

if [ -z "$JAR_FILE" ]; then
    echo "No JAR found"
    exit 1
fi

echo "Running application"
java -jar "$JAR_FILE"
