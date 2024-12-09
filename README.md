# Web Service

**Home Page:** [http://localhost:8082/books](http://localhost:8082/books)

## Features
- View books with pagination.
- Add, edit, and delete books.
- Validates data with error messages on the form.

## Setup
1. Clone the repository.
2. Navigate to the web service folder.
3. Run `mvn spring-boot:run`.
4. Open the browser and go to `http://localhost:8082/books`.

## Dependencies
- Thymeleaf
- Spring Boot Web
- H2 Database
- Swagger

## How to Build and Run

### 1. Clone the Repository
- git clone https://github.com/Adewale103/web-service.git
- cd web-service

### 2.  Build the JAR
mvn clean package

### 3.  Run the Service
java -jar target/web-service-0.0.1-SNAPSHOT.jar

### 4.  Access the Application
Go to:
http://localhost:8082/books

### 5.  Integration with Management Service

Default Management Service URL is http://localhost:8080/api/v1/book. 
- Update application.properties if needed:
management.service.url = http://<management_service_url>
