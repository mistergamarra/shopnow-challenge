FROM openjdk:17-jdk-slim-buster
WORKDIR /app

COPY build/libs/gateway-0.0.1-SNAPSHOT.jar .

WORKDIR /app
ENTRYPOINT java -Dspring.profiles.active=prod  -jar gateway-0.0.1-SNAPSHOT.jar