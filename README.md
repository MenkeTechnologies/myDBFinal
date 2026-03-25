# Personal Database-Driven Website for the Raspberry Pi3

A full-stack web application built with Kotlin, Spring Boot, JSPs, jQuery, Bootstrap, and MySQL.

![Welcome page screenshot](/welcome.png?raw=true)
![Tips and Tricks page](/tips.png?raw=true)

## Tech Stack

- **Language**: Kotlin (JVM target 23)
- **Framework**: Spring Boot 3.4
- **Views**: JSP, JSTL
- **Frontend**: jQuery, Bootstrap
- **Database**: MySQL (Connector/J 9.2)
- **Build**: Maven

## Prerequisites

- JDK 23+
- Maven
- MySQL

## Configuration

Edit `src/main/resources/application.properties` with your MySQL connection details:

```properties
db.host=localhost
db.password=yourpassword
db.port=3306
```

## Build & Run

```sh
mvn spring-boot:run
```

Or build and run the WAR directly:

```sh
mvn package
java -jar target/myDBFinal-1.0-SNAPSHOT.war
```

The app will be available at `http://localhost:8080/db/`.

## Features

- User authentication with SHA-256 hashed and salted passwords
- Electronics collection manager (CRUD with remote device control)
- Learning collection tracker (CRUD with categories)
- File upload to the Raspberry Pi
- Session-based login interceptor protecting `/app/*` routes
