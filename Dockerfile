FROM maven:3.6.1-jdk-8-alpine AS MAVEN_BUILD
 
# copy the pom and src code to the container
COPY ./ ./
 
# package our application code
RUN mvn clean package
 
# the second stage of our build will use open jdk 8 on alpine 3.9
FROM openjdk:8-jre-alpine3.9
 
# copy only the artifacts we need from the first stage and discard the rest
COPY --from=MAVEN_BUILD /staff-management-api/target/staff_management-0.2.0.jar /app.jar
 
# set the startup command to execute the jar
CMD ["java", "-jar","-Dspring.profiles.active=docker", "/app.jar"]