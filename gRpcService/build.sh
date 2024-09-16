echo "compilation project"
mvn clean package

if [ $? -ne 0 ]; then
    echo "Build fail"
    exit 1
fi
