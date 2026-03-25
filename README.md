# Personal Database-Driven Website for the Raspberry Pi3

A full-stack web application built with Kotlin servlets, JSPs, jQuery, Bootstrap, and MySQL. Runs on Apache Tomcat 10+.

![Welcome page screenshot](/welcome.png?raw=true)
![Tips and Tricks page](/tips.png?raw=true)

## Tech Stack

- **Language**: Kotlin (JVM target 23, runs on JDK 25)
- **Web**: Jakarta Servlet 6.1, JSP
- **Frontend**: jQuery, Bootstrap
- **Database**: MySQL (Connector/J 9.2)
- **Build**: Maven
- **Server**: Apache Tomcat 10+ / 11 (Jakarta EE)

## Prerequisites

- JDK 25
- Maven
- Apache Tomcat 10.1+ or 11 (Jakarta EE compatible)
- MySQL

## Build

```sh
mvn package
```

The WAR file will be created at `target/myDBFinal-1.0-SNAPSHOT.war`.

## Deploy

Copy the WAR to your Tomcat `webapps` directory:

```sh
cp target/myDBFinal-1.0-SNAPSHOT.war $CATALINA_HOME/webapps/db.war
catalina start
```

The app will be available at `http://localhost:8080/db/`.

## Configuration

Create `web/WEB-INF/ip.txt` with your MySQL host and password:

```
<mysql-host>
<mysql-password>
```

## Features

- User authentication with SHA-256 hashed and salted passwords
- Electronics collection manager (CRUD with remote device control)
- Learning collection tracker (CRUD with categories)
- File upload to the Raspberry Pi
- Session-based login filter protecting `/app/*` routes
