# GastroManagementSuite

**GastroManagementSuite** is a comprehensive restaurant management application following a client-server architecture. The backend is built with **Spring Boot** (JPA, REST API), while the desktop client is implemented using **JavaFX**.

## 📂 Project Structure

```
GastroManagementSuite/
├── spring-backend/         # Spring Boot project (REST API, JPA)
│   ├── src/main/java/...   # Java source files
│   └── pom.xml             # Maven build file
├── javafx-client/          # JavaFX project (desktop client)
│   ├── src/main/java/...   # Java source files
│   └── build.gradle        # Gradle build file
└── README.md               # This file
```

## 🚀 Getting Started

### 1. Backend (Spring Boot)

1. Navigate to the `spring-backend` directory:

   ```bash
   cd spring-backend
   ```
2. Build and run the application with Maven:

   ```bash
   mvn clean package
   mvn spring-boot:run
   ```
3. The REST API will start on port `8082`. Example endpoint:

   ```
   GET http://localhost:8082/
   ```

### 2. Frontend (JavaFX)

#### Option A: Gradle wrapper

1. Navigate to the `javafx-client` directory:

   ```bash
   cd javafx-client
   ```
2. Run the client:

   ```bash
   ./gradlew run
   ```

#### Option B: IntelliJ IDEA

1. Open the `javafx-client` project in IntelliJ.
2. Add the JavaFX SDK as a library (File → Project Structure → Libraries).
3. Configure a Run configuration:

   * Main class: `Main`
   * VM options:

     ```text
     --module-path /path/to/javafx-sdk/lib --add-modules javafx.controls
     ```
4. Run the newly created configuration.

## ⚙️ Configuration

By default, the JavaFX client expects the backend at:

```
http://localhost:8080/api/hello
```

## 🧩 Technologies

**Backend**

* Java 17+
* Spring Boot
* Spring Data JPA / Hibernate
* Maven
* LocationIQ REST API

**Frontend**

* Java 17+
* JavaFX 21
* Gradle

## 📄 License

This project is licensed under the MIT License. See the [LICENSE](LICENSE) file for details.
