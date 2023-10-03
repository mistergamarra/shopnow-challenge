FROM openjdk:17-jdk-slim-buster
WORKDIR /app

COPY build/libs/warehouse-svc-0.0.1-SNAPSHOT.jar .

WORKDIR /app
ENTRYPOINT java -Dspring.profiles.active=prod -jar warehouse-svc-0.0.1-SNAPSHOT.jar