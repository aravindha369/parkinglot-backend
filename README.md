# 🚗 Parking Lot Management System – Backend

This is the Spring Boot backend service for the Parking Lot Management System.

It handles authentication, role-based access, and REST APIs for vehicle entry/exit tracking.

## 🔧 Built With

- Java 17  
- Spring Boot  
- Spring Security (JWT)  
- Spring Data JPA  
- H2 Database (in-memory)  
- Maven  

## 🛡️ Features

- JWT-based user authentication  
- Role-based access (`ADMIN`, `USER`)  
- RESTful APIs for:  
  - Vehicle entry and exit  
  - Viewing parked vehicles  
  - Search by vehicle number or driver name  
- Admin-only endpoints  
- CORS support for Angular frontend  

## ⚙️ Running the App

### 1. Database

This project uses **H2 in-memory database**, which requires no external setup.

Database settings are configured in `application.properties`:

```properties
spring.datasource.url=jdbc:h2:mem:testdb
spring.datasource.driverClassName=org.h2.Driver
spring.datasource.username=sa
spring.datasource.password=
spring.h2.console.enabled=true