# staff-management-api
An opensource api which provides various options to manage office/household help/staff.

## Pre-requisites
- Build Tool- Maven
- Git
- Jdk 8

## Steps to setup project
1. Clone the git project
   ```
   git clone https://github.com/PoonamSharma93/staff-management-api.git
   cd staff-management-api
   ```
2. Build the project using maven build tool
   ```
   mvn clean install
   ```
3. Run the jar
   ```
   java -jar target/staff_management-x.x.x.jar --spring.profiles.active=prod
   ```
---
## Api
- `/`</br>
The get api shows the active profile
- `/schedule`</br>
The post api process the request asynchronously in another thread and initiates a scheduler to perform task for certain number of times.
- `/headers`</br>
The post api return the request of header back in response
---
## CRUD Operation with Hibernate
### Steps:
1. Add Data-Jpa dependency to enable hibernate in spring boot project. ModelMapper is used to map VO/DTO to DAO object.
   ```
   <dependency>
      <groupId>org.springframework.boot</groupId>
      <artifactId>spring-boot-starter-data-jpa</artifactId>
   </dependency>
   <dependency>
      <groupId>org.modelmapper</groupId>
      <artifactId>modelmapper</artifactId>
      <version>2.3.5</version>
   </dependency>
   ```
2. Use h2 Database into the project
   ```
   <dependency>
      <groupId>com.h2database</groupId>
      <artifactId>h2</artifactId>
      <scope>runtime</scope>
   </dependency>
   ```   
   add parameters in application.properties
   ```    
   spring.h2.console.enabled=true
   spring.h2.console.path=/console
   ```
3. User Repository : 
 
      We create a User named dao (data access object) bean and UserDTO named its dto (data transfer object). DTO/VO is the one which transfer the data between the processes or the classes. DAO/Entity is the object with interacts with the db to enable CRUD operaion.
     
     To perform CRUD operation we are going to make use of Http Methods:
        
    - POST : Create
    - GET : Read
    - PUT : Update
    - DELETE : Delete
 
---
## Jackson Json View
- `@JsonView`:  To limit or control fields display for different views of a model object
- `@JsonInclude`: To ignore the null fields, put `@JsonInclude(JsonInclude.Include.NON_NULL)` on class level or field level.
- `@JsonIgnore`: To ignore few fields for all the Views.
- `@JsonPropertyOrder`: To specify the order of fields of the model. We can also specify the alphabetic order of a collection type field by `@JsonPropertyOrder(alphabetic = true)`
---
## Hibernate Association Mapping
Always use a Set to denote a collection instead of a List. Hibernate removes all the associative entity and reinsert the remaining ones in case of List.
- `@OneToMany` (Society to Customer) : The best way to use OneToMany mapping is to rely on ManyToOne to make it Bidirectional
- `@ManyToOne` (Customer to Society) : It is default initialized by EAGER type. To improve performance we are using `(fetch = FetchType.LAZY)`
- `@ManyToMany` (User to Customer) : We should use `@JoinTable` to prevent creation of separate table for maintaining the foreign keys between the entites
- `@OneToOne`

The `@OneToMany` and `@ManyToMany` associations are defaulted to **LAZY** loading and `@OneToOne` and `@ManyToOne` are defaulted to **EAGER** loading.
We can change this by `@OneToMany(fetch = FetchType.LAZY )`

## Component Mapping
`@Emdedded` and `@Embeddable` (User to Addesss) : The address fields are shown in the User table only. Separate table is not created for Address.

## CascadeType
**Cascading** : When we perform some action on the target entity, the same action will be applied to the associated entity.
For LAZY initialization mapping use `CascadeType.All` so that the child object is inserted on insertion of parent object.

`orphanRemoval=true`: Delete orphan(no longer connected to parent entity) child entity.

---
## Liquibase
### Description
Liquibase is an open source version control tool for database schema migration. Using Change Log file, liquibase will 
use it as configiration guidelines to make changes to the underlying database schema. The changesets files can be in various formats    including XML, JSON, YAML, and SQL. It creates its own 2 table for maintaining version and lock i.e DATABASECHANGELOG and DATABASECHANGELOGLOCK.
### Steps:
1. Add liquibase dependency 
   ```
   <dependency>
      <groupId>org.liquibase</groupId>
      <artifactId>liquibase-core</artifactId>
   </dependency>
   ``` 
2. Add parameters in application.properties. Default path is db/changelog/db.changelogmaster.yml
   ```    
   spring.liquibase.change-log=classpath:/db/changelog/changelog-master.xml
   ```
3. Create master databasechangelog xml file and other specific changeset file. Include these changeset files in the master file
---
## Github Action
### Description
This feature of Github can be used for CI/CD integration while commiting or pushing our code. 

Github Action to build jar of code code while performing push operation
### Steps:
1. Create a WorkFlow File of yml type
.github/workflows/continuous-integration-workflow.yml
2. Following is the syntax of maven build workflow
   ```    
   name: Java CI
   
   on: [push]

   jobs:
      build:
         runs-on: ubuntu-latest
         steps:
            - uses: actions/checkout@v2
            - name: Set up JDK 1.8
              uses: actions/setup-java@v1
              with:
                java-version: 1.8
            - name: Build with Maven
              run: mvn -B package --file pom.xml
   ```
3. Commit this file to the branch where you want your workflow to run.
   
Whenever you try to push your code to that branch a maven action will be performed. If the file syntax or anything goes wrong, the workflow will fail.

---
## Run docker image in google cloud instance
Created an compute engine instance in google cloud with linux operating system and build docker image over it.
Used Commands:
```
cd staff-management-api
docker build -t app:1.0 .
docker run -itd -p 3333:9090 -v /home/poonamsnu/springboot_docker/:/app --name springboot_docker app:1.0
docker exec springboot_docker /bin/sh
```
Used mount point for docker container to save logs on the hostmachine usine `VOLUME` command in dockerfile

Command to pull docker image to local repository
```
docker pull poonam00/springboot_docker
```
---
## Swagger
### Description
We are using OpenApi Specification 3.0(latest swagger) in this project for documentation purpose.
### Steps
1. add dependency
```
<dependency>
    <groupId>org.springdoc</groupId>
    <artifactId>springdoc-openapi-ui</artifactId>
    <version>1.2.32</version>
</dependency>
```
2. add `@ResponseStatus` on Exception handler class to generate documentation for response code 
3. add `@Operation` to give description to the api and `@ApiResponses` to generate documentation for possible response code of the api
4. add `@OpenAPIDefinition` to define title, version and description of the project

The documentation is available at endpoint */swagger-ui.html*

---
## Spring Cloud Config
### Description
Spring Cloud Config is Spring's client/server approach for storing and serving configuration files across multiple applications and environments.
It generally have 3 modules:
1. Config Server : It is a spring boot application which fetch config client's configurations from git repo
2. Config Client : It loads its application.properties files from git repo through cinfig server
3. Git Config Repo : Here we store all the application.properties for different environments.

Config server is availabe at repo https://github.com/Poonam00/springcloudconfigserver

Git config repo is available at https://github.com/Poonam00/config-repo

For the config client to access the configurations from git repo following step are required to be taken:
1. Add dependency in pom.xml
```
<dependency>
   <groupId>org.springframework.cloud</groupId>
	<artifactId>spring-cloud-starter</artifactId>
	<version>2.2.2.RELEASE</version>
</dependency>
<dependency>
	<groupId>org.springframework.cloud</groupId>
	<artifactId>spring-cloud-starter-config</artifactId>
	<version>2.2.2.RELEASE</version>
</dependency>
```

2. Give config server url in bootstrap.properties
3. Use `@RefreshScope` in the class where configuration properties are used
4. Enable actuator's `/refresh` end point to refresh the bean of configuration when some congifiguration changes are needed to be reflected
---



