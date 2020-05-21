# staff-management-api
An opensource api which provides various options to manage office/household help/staff.

## Steps to setup project

pre-requisites

- Build Tool- Maven
- Git
- Jdk 8

1. First get the code from master branch to your local machine
   ```
   git clone https://github.com/PoonamSharma93/staff-management-api.git
   cd staff-management-api
   ```
2. Build the project using Maven build tool
   ```
   mvn clean install
   ```
3. Run the jar
   ```
   java -jar target/staff_management-x.x.x.jar --spring.profiles.active=prod
   ```
   
   ---
   
## Api
- /schedule</br>
The post api process the request asynchronously in another thread and initiates a scheduler to perform task for certain number of times.
