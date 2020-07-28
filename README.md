# staff-management-api
An opensource api which provides various options to manage office/household help/staff.

## Steps to setup project
### pre-requisites
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
 
      We create a User named dao (data access object) bean and UserDTO named its dto (data transfer object). DTO/VO is the one which transfer the data between the processes or the classes. DAO/Entity is the object with interacts with the db to enable crud operaion.
     
     To perform CRUD operation we are going to make use of Http Methods:
        
    - POST : Create
    - GET : Read
    - PUT : Update
    - DELETE : Delete
 
 ---
 ## Hibernate Association Mapping
 Always use Set to denote a collection instead of list. Hibernate removes all the associative entity and reinsert the remaining ones in case of list.
- @OneToMany(Society to Customer) : The best way to use OneToMany mapping is to rely on ManytoOne to make it Bidirectional
- @ManytoObe(Customer to Society) : default initialized by EAGER initialization. To improve performance we are using (fetch = FetchType.LAZY)
- @ManytoMany(User to Customer) : We should use @JoinTable to prevent creation of separate table for maintaining the foreign keys between the entites
- @OneToOne
The @OneToMany and @ManyToMany associations are defaulted to LAZY loading; and @OneToOne and @ManyToOne are defaulted to EAGER loading.
We can change this as @OneToMany(  fetch = FetchType.LAZY )

## Component Mapping
@Emdedded and @Embeddable(User to Addesss) : the address fields are shown in the User table only. Separate table is not created for Address.

## CascadeType
Cascading : When we perform some action on the target entity, the same action will be applied to the associated entity.
For LAZY initialization mapping user CascadeType.All so that the child object is inserted on insertion of parent object.

orphanRemoval=true: delete orphan(no longer connected to parent entity) child entity

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
 2.  Add parameters in application.properties. Default path is db/changelog/db.changelogmaster.yml
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
   
   Whenever you try to push your code to that branch a maven action will be performed. If the file syntax or anything goes wrong, the workflow will fail
   
---

### Jackson Json View
## Description
- @JsonView:  To limit or control fields display for different views of a model object
- @JsonInclude: To ignore the null fields, put @JsonInclude(JsonInclude.Include.NON_NULL) on class level or field level.
- @JsonIgnore: To ignore few fields for all the Views.
- @JsonPropertyOrder: To specify the order of fields of the model. We can also specify the alphabetic order of a collection type field by @JsonPropertyOrder(alphabetic = true)

---









