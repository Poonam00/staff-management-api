FROM openjdk:8-jdk-alpine
VOLUME /app
COPY ./target/*.jar app.jar
ENTRYPOINT ["java","-jar","-Dspring.profiles.active=docker","/app.jar"]
