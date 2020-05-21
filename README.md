# Short Message App
This is test task to create application to send short messages.
The requirements were to make it quite simple, so there is no DTO objects, strict validations, any containers or migration scripts

## Stack
* Java 8 and Lombok
* Spring Boot, Spring Data and Hibernate
* Spring Security and JWT auth
* Angular 4 and TypeScript
## Requirements

Maven >= 3.3.1

Need to run specific goals by id from command line. Ex. mvn frontend:yarn@goal_id


## Installing

 1. mvn install

## Run for front end development

 1. mvn -f ../../pom.xml frontend:yarn@args -Dyarn.args="start"

## Run

 1. mvn spring-boot:run

