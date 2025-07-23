# Notification System - Coding Challenge

## Frontend Repo:
 - https://github.com/matheus-arruda/coding-ch-gilasw-fr

## Backend Technologies
- Java 17
- Spring Boot
- H2 Database
- JPA
- Lombok
- Swagger (OpenAPI)
- Spotless (formatter)
- JUnit + Mockito

## Design Decisions

- **Spring Boot + MVC:** Clean and modular structure.
- **Notification Strategy Pattern:** Makes it easy to extend channels (e.g., Email, SMS, Push).
- **DTOs:** Used to decouple domain entities from the external API layer.
- **Lombok:** Reduces boilerplate and improves readability.
- **H2 Database:** Lightweight and suitable for testing and prototyping.
- **OpenAPI (Swagger):** Improves developer experience and testing via Swagger UI.

### Prerequisites
- Java 17+
- Maven 3+

## Test Coverage

This project has **100% unit test coverage**, measured with **JaCoCo**.

> All core components (controllers, services, strategies) are covered with unit tests.
> 
> Note: Integration or E2E tests were not included due to scope constraints.
## How to Run

```bash
git clone https://github.com/matheus-arruda/coding-ch-gilasw.git
cd coding-ch-gilasw
./mvnw spring-boot:run
```

## Build and Run

```bash
./mvnw clean install
./mvnw spring-boot:run
```

## Access
- Swagger UI: http://localhost:8080/swagger-ui.html
- H2 Console: http://localhost:8080/h2-console
(JDBC URL: jdbc:h2:mem:testdb, user: sa, no password)

## API Endpoints
| Method | Endpoint         | Description                          |
| ------ | ---------------- | ------------------------------------ |
| POST   | `/notifications` | Sends a message to subscribed users  |
| GET    | `/logs`          | Retrieves all sent notification logs |

## Code Formatting
- Apply
```bash
./mvnw spotless:apply
```
- Check Violations
```bash
  ./mvnw spotless:check
```

## Tests
```bash
./mvnw test
```

## Architecture (Mermaid)

```mermaid
graph TD
  subgraph Controller
    A1[NotificationController]
    A2[LogController]
    A3[CategoryController]
  end

  subgraph Service
    B1[NotificationService]
    B2[LogService]
    B3[CategoryService]
  end

  subgraph Strategy
    C1[EmailNotification]
    C2[SmsNotification]
    C3[PushNotification]
    C4[NotificationStrategyFactory]
  end

  subgraph Repository
    D1[NotificationLogRepository]
    D2[CategoryRepository]
    D3[UserRepository]
  end

  A1 --> B1
  A2 --> B2
  A3 --> B3
  B1 --> C4
  C4 --> C1
  C4 --> C2
  C4 --> C3
  B1 --> D1
  B2 --> D1
  B3 --> D2
