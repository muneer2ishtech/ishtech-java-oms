FROM openjdk:21-jdk-slim

ARG APP_VERSION=0.1.0-SNAPSHOT
ARG SERVER_PORT=8080

ENV TZ=Europe/Helsinki

COPY target/ishtech-java-oms-${APP_VERSION}.jar ishtech-java-oms.jar

EXPOSE ${SERVER_PORT}

ENTRYPOINT ["java", "-jar", "/ishtech-java-oms.jar"]
