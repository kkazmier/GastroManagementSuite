# GastroManagementSuite

**GastroManagementSuite** is a web-based restaurant management application. It allows managing employees, orders, menu items, and deliveries. Built with Spring Boot, it uses an in-memory H2 database and integrates with external APIs such as LocationIQ for address geolocation and the National Bank of Poland (NBP) API for currency exchange rates.

## 📌 Table of Contents

- [Technologies](#technologies)
- [Features](#features)
- [Requirements](#requirements)
- [Running the Application](#running-the-application)
- [Project Structure](#project-structure)
- [External APIs](#external-apis)
- [Testing](#testing)
- [Author](#author)

## 🛠 Technologies

- Java 17
- Spring Boot
- Spring Data JPA
- Spring Security
- H2 Database
- RESTful API
- Static HTML/CSS/JS views
- LocationIQ API
- NBP Web API

## ✅ Features

- Employee management (CRUD)
- Menu item and order management
- Delivery tracking with geolocation (map + coordinates)
- Currency conversion using NBP exchange rates
- Token-based authentication (JWT)
- Custom HTTP error handling (400, 401, 403, 404)

## 🧰 Requirements

- Java 17 or higher
- Maven
- Internet connection (for external APIs)

## ▶️ Running the Application

1. Clone the repository:

   ```bash
   git clone https://github.com/kkazmier/GastroManagementSuite.git
   ```

2. Navigate to the project directory:

   ```bash
   cd GastroManagementSuite
   ```

3. Run the application with Maven:

   ```bash
   mvn spring-boot:run
   ```

4. Open your browser at: [http://localhost:8082](http://localhost:8082)

## 🗂 Project Structure

```
GastroManagementSuite/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── pl/gastro/gastro_management_suite/
│   │   │       ├── controller/       # REST controllers
│   │   │       ├── model/            # Entities
│   │   │       ├── repository/       # JPA repositories
│   │   │       ├── service/          # Business logic
│   │   │       ├── security/         # JWT + auth config
│   │   │       └── exception/        # Global error handling
│   │   └── resources/
│   │       ├── static/               # Static views (HTML/JS/CSS)
│   │       └── application.properties
├── pom.xml
└── README.md
```

## 🌍 External APIs

### 🌐 LocationIQ API

Used to convert delivery addresses into geographic coordinates (latitude, longitude).

To enable it, set your API key in `application.properties`:

```properties
locationiq.api.key=YOUR_API_KEY
```

More info: [https://locationiq.com](https://locationiq.com)

### 🇵🇱 NBP Web API

Used to fetch up-to-date currency exchange rates (e.g. for converting order totals to EUR/USD).

Sample request:

```
GET https://api.nbp.pl/api/exchangerates/rates/A/{currency}/?format=json
```

Documentation: [NBP Web API](https://api.nbp.pl/)

## 🧪 Testing

The project uses:

- **JUnit 5**
- **Mockito**
- **Spring Boot Test**

To run tests:

```bash
mvn test
```

Unit and integration tests cover services and controllers to ensure correctness and reliability.

## 👤 Author

- GitHub: [@kkazmier](https://github.com/kkazmier)

---

Feel free to fork the project or open issues/PRs. Contributions are welcome!