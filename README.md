# Business Domain: Independent Film Production System

The Independent Film Production System is a Spring Boot-based application designed to manage film productions, their properties, cast, crew, schedules and even scripts.

## Table of Contents

Tech Stack

Features

Project Structure

Installation

Running the Application

API Endpoints

Testing 

Error Handling

Contributing

## Tech Stack

Java 17

Spring Boot 3.x

Maven (for dependency management)

PostgreSQL (for production database)

H2 Database (for in-memory testing and development)

Spring Data JPA (for repository pattern and data access)

Hibernate (ORM)

MockMVC (for integration tests)

JUnit 5 (for unit tests)

Mockito (for mocking dependencies in tests)

Postman (for API testing)

## Features

**Film Production Management:** Create, Update, Search or Filter, and Delete Film Productions.

**Crew and Cast Members Management:** Create, Update, Search or Delete Crew Members.

**Schedule Management:** Create, Modify, Search or Delete Filming Schedules.

**Script Management:** Post, Search or Update a new Version of a Script, or Delete an old one.

**Custom Exceptions:** Handle exceptions in a clean manner, with an Custom Exception Handler for Productions, Members, Schedules and Scripts.

**Validation:** Creating and Updating requires a valid input from the user.

**Testing:** Comprehensive Intergration and Unit Testing for endpoints and services.

## Porject Structure
```
├───src
│   ├───main
│   │   ├───java
│   │   │   └───com
│   │   │       └───film_production
│   │   │           └───demo
│   │   │               ├───controllers
│   │   │               ├───exceptions
│   │   │               ├───models
│   │   │               │   ├───dtos
│   │   │               │   └───entities
│   │   │               ├───repositories
│   │   │               ├───services
│   │   │               └───specifications
│   │   └───resources
│   └───test
        ├───java
            └───com
                └───film_production
                    └───demo
                        ├───integration_tests
                        └───unit_tests
```        


## Running The Application

### Prerequisites

**Java 17**

**PostegreSQL**

**Maven**

### Steps

**1.Clone the repository**

```git clone https://github.com/CsiszerMihai/Independent-Film-Production.git```

**2.Configure PostgreSQL**

Set up a PostgreSQL database. Name should be set to independent_film_production.

Update the credentials in application.properties to match your PostgreSQL setup:

Replace user and password with your actual PostgreSQL username and password.

**3.Run the application using Maven:**

```mvn spring-boot:run```

### API Documentation

The app will be accessible at:

```http://localhost:8080```

## API Endpoints

The system offers the following functionalities

### Endpoint Formats

POST /api/film-productions - Creates a new Film Production

GET /api/film-productions  - Requests all Film Productions

GET /api/film-productions/{id} -Requests a film production by ID

PUT /api/film-productions/{id} - Updates a film production by ID

DELETE /api/film-production/{id} - Deletes a film produciton by ID

**Every Endpoint up until this point applies for Crew, Cast, Schedules, and Scripts**

**To use other entities enpoints, replace film-productions with: crew-members, cast-members, schedules, scripts. Otherwise the endpoints are mapped the same**

GET /api/film-productions/search -Requests a film production by multiple filters (ex: director, description, budget)

## Testing

Run unit and integration tests using the following command

```mvn test```

## Error Handling

All exceptions are handled by using GlobalExceptionHandler

400 Bad Request for invalid data input.

404 Not Found for missing resources.

## Contributing

Fork the repository and submit a pull request. For major changes, open an issue to discuss what you would like to change. Enjoy!
