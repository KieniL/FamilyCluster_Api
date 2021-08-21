FROM luke19/spring-base-image:1629374721

LABEL maintainer="KieniL"
LABEL name="api"
LABEL version="1.0.0"
LABEL author="KieniL"
LABEL contact="https://github.com/KieniL/FamilyCluster_Api/issues"
LABEL documentation="https://github.com/KieniL/FamilyCluster_Api"

ENV AUTH_URL=tmp
ENV ANSPAR_URL=tmp
ENV CERT_URL=tmp
ENV API_LOG_LEVEL=DEBUG

COPY ./target/*.jar /app/app.jar


ENTRYPOINT ["java", "-Djava.io.tmpdir=/app/tmp" ,"-jar", "app.jar"]

EXPOSE 8080
