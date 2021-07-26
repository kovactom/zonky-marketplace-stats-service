# Zonky marketplace service
Simple web service for providing statistics of interest rate for loans.

## Requirements
* Java JDK 11

## How to build
Build can be done by using maven wrapper which is part of this project:

```./mvnw clean install```

## How to run
Application can be started by using Spring Boot Maven plugin:

`./mvnw spring-boot:run`

Or by `java -jar` command:

`java -jar ./target/zonky-marketplace-service-0.0.1-SNAPSHOT.jar`