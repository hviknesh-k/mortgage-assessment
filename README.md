# Spring Boot Assessment Project

## About
Assessment project to build endpoints in spring boot and perform testing.

## Requirement Assumptions

* Interest rate and mortgage calculation are treated as two different components
* Mortgage rate calculation is done based on standard formula available online (https://www.jessym.com/articles/deriving-the-mortgage-payment-formula)
* loanValue is considered for the mortgage calculation and not home value
* Since not very sure of the condition for eligibility, assumption was done to compare X percentage of monthly income to monthly repayment costs.
* maturity period is assumed to be in years and user will input one of the values from GET encpoint
* maturity period is assumed to be unique
* No security framework is considered for this assessment

## What's inside

Project contains:
* application files
* common plugins and libraries
* In memory database and liquibase to setup data on application start up 
* docker file
* automatically publishes API documentation to (http://localhost:8080/swagger-ui/#/)
* integration / unit test files
* jacoco report will be generated under target/site/

The application exposes only two default actuator endpoints (http://localhost:8080/actuator/health) and metrics endpoint
(http://localhost:8080/actuator/info).

## Setup

To run application in local run below command from project repo
```bash
  ./mvnw spring-boot:run
```
## Building and deploying the application

### Building the application

The project uses maven as a build tool. It already contains wrapper script, so there's no need to install maven.

To build the project execute the following command:

```bash
  ./mvnw clean install
```

### Running on Docker

Build the docker file on project repo

```bash
  docker build -t spring-boot-assessment .
```

Verify the list of images generated

```bash
  docker image ls
```
Launch docker container using 

```bash
  docker run -p 8080:8080 spring-boot-assessment
```

## Scope for Improvements

* Having a distributed caching like redis to serve GET endpoint response
* Sort out jacoco file exclusions and improve code coverage
* 

## Testing Application

Postman collection is added to the repo