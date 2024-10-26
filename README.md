# library-demo
This project presents a RESTful service using Spring Boot 3.  
This is a Library Simulator that allows CRUD operations on books. Each book contains a title, year, 
number of pages and ISBN code, as well as link to authors and publisher.The API allows you to create, read, update and 
delete books, authors and publishers, as well get all books by author or publisher.  
Application includes the following features:

## Features

* Filtering and pagination of books by author or publisher
* Incoming data validation
* Data caching
* API documentation in Swagger UI
* Migrating data on first load with Liquibase
* Unit tests
* Deploying service based on a Docker image

## Technologies

* Java 17
* Spring Boot 3
* Spring JPA
* Maven
* PostgreSQL
* Docker

## Getting Started

To get started with this project, you will need to have the following installed on your local machine:

* JDK 17+
* Maven 3+
* Docker

To build and run the project, follow these steps:

* Clone the repository:

```sh
git clone https://github.com/MikeReliable/library-demo.git
 ```

* Navigate to the project directory: cd librarydemo
* Build the project:

```sh
mvn clean install
 ```

* Start the docker engine and build image:

```sh
docker build -t libraryDemo .
 ```

* Download PosrgreSQL docker image:

```sh
docker pull postgres:16
 ```

* Run the docker-compose file:

```sh
docker-compose up
 ```

-> The application will be available at http://localhost:8080  
-> Swagger UI specification will be available at http://localhost:8080/swagger-ui/index.html