if [ ! -f openapi-generator.jar ]; then
    curl https://repo1.maven.org/maven2/org/openapitools/openapi-generator-cli/3.3.4/openapi-generator-cli-3.3.4.jar --output openapi-generator.jar
fi

java -jar openapi-generator.jar generate -i auth-api.yml -l spring -o ../ -c generator.json
java -jar openapi-generator.jar generate -i app-api.yml -l spring -o ../ -c generator.json
java -jar openapi-generator.jar generate -i ansparen-api.yml -l spring -o ../ -c generator.json
java -jar openapi-generator.jar generate -i cert-api.yml -l spring -o ../ -c generator.json
