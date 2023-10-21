# Roach Demo Data :: JPA/Hibernate

A CockroachDB Spring Boot Demo using [Spring Data JPA](https://spring.io/projects/spring-data-jpa)
with Hibernate for data access.

Spring Data JPA provides a powerful model for using ORM frameworks such as Hibernate. It's considered a good
fit for data access logic that involves large, complex domain model mapping. Spring Data JPA essentially
removes the need for application code to bind directly to Hibernate APIs. It also unlocks many advanced
features of JPA.

## Dependencies Installation

Before you begin, make sure you have the following dependencies installed:

#### 1. Install JDK

Download and install a Java Development Kit. Spring Boot is compatible with Java versions 8, 11, and 14. In this tutorial, we use JDK 17 from OpenJDK.

#### 2. Install Maven

This sample application uses Maven to manage all application dependencies. Spring supports Maven versions 3.2 onwards.

To install Maven on macOS, run the following command:

```bash
brew install maven
```

This ensures that Maven is installed and ready to manage your application dependencies.

## Ponti Reparte Project CONFIGURATION

Welcome to the Ponti Reparte project! Here are the necessary steps to get started with the backend server.

### Step 1: Access the Project

Navigate to the Ponti Reparte project directory. If you are using the command line, you can do this with the following command:

```bash
cd ../Ponti-reparte/project
```

### Step 2: Clean and Install Executable

Once you are inside the project directory, clean and install the executable using the following command:

```bash
mvn clean install
```

### Step 3: Run the Backend Server

Once you are inside the project directory, run the backend server using the following command:

```bash
java -jar .target/ponti-reparte-2.7.1.jar
```

And that's it! You should now have everything you need to run and develop on the Ponti Reparte project. If you have any questions or issues, feel free to reach out to us. Happy coding!
