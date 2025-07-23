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
| ------ |------------------|--------------------------------------|
| POST   | `/notifications` | Sends a message to subscribed users  |
| GET    | `/logs`          | Retrieves all sent notification logs |
| GET    | `/categories`    | Retrieves all categories             |

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
    NotificationController
  end

  subgraph Service
    NotificationService
    LogService
  end

  subgraph Strategy
    NotificationStrategyFactory
    EmailNotification
    SmsNotification
    PushNotification
  end

  subgraph DTO
    NotificationRequestDTO
  end

  subgraph Entity
    NotificationEntity
    UserEntity
  end

  subgraph Repository
    NotificationRepository
    UserRepository
  end

  subgraph Config
    WebConfig
  end

  subgraph Handler
    GlobalExceptionHandler
  end

  NotificationController -->|POST /notifications| NotificationService
  NotificationService --> NotificationStrategyFactory
  NotificationService --> NotificationRepository
  NotificationService --> LogService
  NotificationService --> UserRepository

  NotificationStrategyFactory --> EmailNotification
  NotificationStrategyFactory --> SmsNotification
  NotificationStrategyFactory --> PushNotification

  NotificationController --> NotificationRequestDTO
  NotificationRequestDTO -->|Validated| GlobalExceptionHandler

  NotificationService --> NotificationEntity
  NotificationEntity --> NotificationRepository

  NotificationService --> UserEntity
  UserEntity --> UserRepository

  WebConfig --> NotificationController
  GlobalExceptionHandler -->|Catches| NotificationController

