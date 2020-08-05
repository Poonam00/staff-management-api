FROM openjdk:8-jdk-alpine
VOLUME /tmp
ARG JAR_FILE=E:\myworkspace\staff_management\staff-management-api
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","/app.jar"]