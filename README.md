# OIBSIP_JavaDevelopment_Task1

# Train Reservation System

## Oasis Infobyte Internship - Java Development

### Task 1: Train Reservation System

## Objective

The objective of this project is to develop a web-based Train Reservation System that allows users to search trains, reserve tickets, manage bookings, and cancel reservations while providing administrators with tools to manage trains, routes, users, and reservations.

The project demonstrates the implementation of Java Full Stack Development concepts using Spring Boot, Thymeleaf, MySQL, and Spring Security.

---

## Features

### User Module

* User Registration and Login
* Secure Authentication using Spring Security
* Search Trains between Stations
* View Available Trains
* Book Train Tickets
* Enter Multiple Passenger Details
* Automatic PNR Generation
* View My Reservations
* Cancel Reservations using PNR
* Update Profile Information
* Change Password

### Admin Module

* Admin Login
* Dashboard with Statistics
* Add New Trains
* Edit Existing Trains
* Delete Trains
* Manage Train Routes
* Add Route Stations
* View Registered Users
* View All Reservations

---

## Technologies Used

* Java 21
* Spring Boot
* Spring MVC
* Spring Data JPA
* Spring Security
* Thymeleaf
* MySQL
* Bootstrap 5
* HTML5
* CSS3
* Maven
* Git & GitHub

---

## Project Structure

```
src
 ├── controller
 ├── entity
 ├── repository
 ├── service
 ├── config
 ├── templates
 └── static
```

---

## Steps Performed

1. Designed the database entities for Users, Trains, Reservations, Routes, and Passengers.
2. Implemented authentication and authorization using Spring Security.
3. Developed user registration and login functionality.
4. Built train search with dynamic fare calculation.
5. Implemented ticket reservation with passenger information collection.
6. Generated unique PNR numbers for bookings.
7. Developed reservation viewing and cancellation features.
8. Created a complete admin panel for managing trains, routes, users, and reservations.
9. Improved the user interface with responsive Bootstrap-based professional layouts.
10. Tested all major workflows and integrated MySQL persistence.

---

## How to Run the Project

### 1. Clone the repository

```bash
git clone https://github.com/Rishav-Kundu/OIBSIP_JavaDevelopment_Task1.git
```

### 2. Open the project in your preferred IDE

Examples:

* IntelliJ IDEA
* Spring Tool Suite (STS)
* VS Code

### 3. Configure MySQL

Create a database named:

```
train_reservation
```

Update `application.properties` with your own MySQL credentials:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/train_reservation
spring.datasource.username=YOUR_USERNAME
spring.datasource.password=YOUR_PASSWORD
```

### 4. Run the application

Run:

```
TrainreservationApplication.java
```

or

```bash
mvn spring-boot:run
```

### 5. Open in browser

```
http://localhost:8080
```

---

## Tools Used

* Spring Boot
* Maven
* MySQL Workbench
* IntelliJ IDEA
* Bootstrap 5
* Git
* GitHub

---

## Outcome

Successfully developed a responsive and secure Train Reservation System with separate User and Admin modules.

The application supports:

* Secure authentication
* Train search
* Ticket booking
* Passenger management
* Reservation management
* PNR-based cancellation
* Admin train and route management
* Modern responsive user interface

The project demonstrates practical implementation of Java Full Stack Development concepts using Spring Boot and serves as a real-world reservation management application.

---

## GitHub Repository

https://github.com/Rishav-Kundu/OIBSIP_JavaDevelopment_Task1

---

## Demo Video

[Add your YouTube or LinkedIn demo video link here.


## LinkedIn Post

Add your LinkedIn project post link here.

Example:

```
https://www.linkedin.com/posts/your-post-link
```

---

## Author

**Rishav Kundu**

Oasis Infobyte Java Development Internship
