FROM adoptopenjdk/openjdk13:x86_64-alpine-jre13u-nightly
WORKDIR /APP
COPY ./target/*.jar app.jar

# run container as non root
RUN apk update && apk upgrade -U -a && addgroup -S familygroup && adduser -S familyuser -G familygroup
USER familyuser

ENTRYPOINT java -jar app.jar

EXPOSE 8080
