FROM maven:3.6.3-jdk-8
VOLUME /app
COPY ./ ./
RUN mvn clean package
ENTRYPOINT ["java", "-jar","-Dspring.profiles.active=docker", "target/staff_management-0.2.0.jar"]