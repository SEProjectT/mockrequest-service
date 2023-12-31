FROM openjdk:17-jdk-slim-buster
WORKDIR /app

COPY build/libs/application.jar build/

WORKDIR /app/build
EXPOSE 8081
CMD ["java", "-jar", "/app/build/application.jar"]