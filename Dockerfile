FROM adoptopenjdk/openjdk13:x86_64-alpine-jre13u-nightly

ENV TZ=Europe/Berlin
ENV AUTH_URL=tmp
ENV ANSPAR_URL=tmp
ENV CERT_URL=tmp
ENV API_LOG_LEVEL=DEBUG

WORKDIR /APP
COPY ./target/*.jar app.jar

# run container as non root
RUN apk update && apk upgrade -U -a && addgroup -S familygroup && adduser -S familyuser -G familygroup
USER familyuser

ENTRYPOINT java -jar app.jar

EXPOSE 8080
