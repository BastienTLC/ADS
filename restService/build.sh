echo "compiling project..."
mvn clean package

if [ $? -ne 0 ]; then
    echo "Build failed"
    exit 1
fi