JAR_FILE=$(find target -name "*.jar" | head -n 1)

if [ -z "$JAR_FILE" ]; then
    echo "error no jar"
    exit 1
fi

echo "Running application"
java -jar "$JAR_FILE"
