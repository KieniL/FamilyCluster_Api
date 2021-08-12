FROM luke19/spring-base-image:1628744630

ENV AUTH_URL=tmp
ENV ANSPAR_URL=tmp
ENV CERT_URL=tmp
ENV API_LOG_LEVEL=DEBUG

COPY ./target/*.jar /app/app.jar


ENTRYPOINT ["java" ,"-jar", "app.jar"]

EXPOSE 8080
