# demo — DDD + Spring WebFlux Example

This project demonstrates **Domain-Driven Design (DDD)** and **Hexagonal Architecture** using **Spring WebFlux**, **R2DBC Postgres**, **value objects** and **Domain Events**.

---

## 🧩 Overview

A fully reactive flow following DDD principles with domain events:
**HTTP API → Application Use Case → Persistence Adapter (R2DBC) → Domain Event → Notification Listener**

**Flow:** create → save in database → emit domain event → handle notification.

---

## 🚀 Features

- Reactive stack using **Spring WebFlux** and **R2DBC**
- Domain-driven layering with clear boundaries:
  - **Domain**: core business model & logic
  - **Application**: use cases and event handling
  - **Infrastructure**: adapters (persistence, REST API)
- Domain events handled via `ApplicationEventPublisher`
- Example of modular, testable architecture

---

## 🧱 Architecture (Hexagonal / Ports & Adapters)
[![](https://mermaid.ink/img/pako:eNp1lMtu00AUhl_laLoBKWkdJ74KFbVxek0vagoLki7G9jixmHjC2C4taTYINlChclmwoKqEkGCDeAM2vEmfoI_A2B5XycKWNbLn_-b8Z36NZoo85hNko4Cyl94I8wSOnUEE4lnr391cXsFRp3cMbRYlnFFK-COXr6weHoi5FTwJV9KY8PgE6vVVWBf85_fwFNPQxwmBI_IiJXFyUlRbz5m2YD78gTYnGfFELIYe4aehRyTWzjFnKkr9hM4YhxQ6Z2GcxI9nBVCMToZd3H57C71_vy-g07-9voSWYmWNBjT0SlfJCXGfXcCGcP_0F3r4tPA-mS-5kTtv9h_c3Vx9BQcn2MUxeSiZzVzd6meWedvFFnwpb-Xytqj_5Q3s4QkkDBwm2o8ksJ0DO1lEP8rtd05JVDa6k-u7mf4dDlOXhvFoASjG3RzrCuzj60KGrkiHRPeb6ebEXlbol8g28osUF9T9TL2GLhsuWBTiQSa-g7UI0_Mk9OIF_zg5pwTWIAgptZcapKXgoOYxyri9FATBPLQuoaZrqoFeAbUlpBCsEasCciQUaBZR3BJSFGUe6kjI91RdrbLbKO00S9er7DYlZHhNTPwKaKuMQHEts1EBbZd2ptVw1QpoR0Kmq3mVOe1KSMRkKl4F1C0jcFXDMCqgPQnpGm4qVbvbLytZhqFU9XRQ5kRU1SNzEKqhIQ99ZCc8JTU0JlycQPGLptnyAUpGZEwGyBafPubPB2gQzcSaCY6eMTYul3GWDkfIDjCNxV86ye4UJ8RDjsf3s1ycb8LbLI0SZOuNVl4E2VN0hmxrudVoiaNgNBuauBlUUjdq6BzZdWVZyR5T08QpEK_aNHWzqc9q6FVuH6WUzv4DYFSh5w?type=png)](https://mermaid.live/edit#pako:eNp1lMtu00AUhl_laLoBKWkdJ74KFbVxek0vagoLki7G9jixmHjC2C4taTYINlChclmwoKqEkGCDeAM2vEmfoI_A2B5XycKWNbLn_-b8Z36NZoo85hNko4Cyl94I8wSOnUEE4lnr391cXsFRp3cMbRYlnFFK-COXr6weHoi5FTwJV9KY8PgE6vVVWBf85_fwFNPQxwmBI_IiJXFyUlRbz5m2YD78gTYnGfFELIYe4aehRyTWzjFnKkr9hM4YhxQ6Z2GcxI9nBVCMToZd3H57C71_vy-g07-9voSWYmWNBjT0SlfJCXGfXcCGcP_0F3r4tPA-mS-5kTtv9h_c3Vx9BQcn2MUxeSiZzVzd6meWedvFFnwpb-Xytqj_5Q3s4QkkDBwm2o8ksJ0DO1lEP8rtd05JVDa6k-u7mf4dDlOXhvFoASjG3RzrCuzj60KGrkiHRPeb6ebEXlbol8g28osUF9T9TL2GLhsuWBTiQSa-g7UI0_Mk9OIF_zg5pwTWIAgptZcapKXgoOYxyri9FATBPLQuoaZrqoFeAbUlpBCsEasCciQUaBZR3BJSFGUe6kjI91RdrbLbKO00S9er7DYlZHhNTPwKaKuMQHEts1EBbZd2ptVw1QpoR0Kmq3mVOe1KSMRkKl4F1C0jcFXDMCqgPQnpGm4qVbvbLytZhqFU9XRQ5kRU1SNzEKqhIQ99ZCc8JTU0JlycQPGLptnyAUpGZEwGyBafPubPB2gQzcSaCY6eMTYul3GWDkfIDjCNxV86ye4UJ8RDjsf3s1ycb8LbLI0SZOuNVl4E2VN0hmxrudVoiaNgNBuauBlUUjdq6BzZdWVZyR5T08QpEK_aNHWzqc9q6FVuH6WUzv4DYFSh5w)
## 🧰 Prerequisites

- **Java 17+**
- **Maven**
- **Docker** (for PostgreSQL)

---

## 🏗️ Run (Postgres + App)

1. Start Postgres with Docker:
   ```bash
   docker compose up -d
   ```

2. Build & run the Spring Boot app:
   ```bash
   mvn spring-boot:run
   ```

---

## 🌐 API Example

Create a new user:

```bash
curl -i -X POST http://localhost:8080/api/users   -H "Content-Type: application/json"   -d '{
    "name": "edgard espinoza",
    "email": "edgard.espinoza@gmail.com"
  }'
```

---

## 🔄 Reactive Flow

1. `ControllerUser` receives the HTTP POST request.  
2. `CreateUserService` checks for duplicate emails.  
3. If valid, `PostgresUserAdapter` saves the entity using R2DBC.  
4. The domain entity is mapped to a `UserEvent`.  
5. The event is published via `ApplicationEventPublisher`.  
6. `DomainEventListener` receives the event and logs/sends notification.

---

## 📁 Project Structure

```
.
├── docker-compose.yaml
├── pom.xml
├── README.md
└── src/
    ├── main/java/com/example/demo/
    │   ├── DemoApplication.java
    │   ├── application/
    │   │   ├── service/
    │   │   │   ├── CreateUserService.java
    │   │   │   └── FindUserService.java
    │   │   └── events/
    │   │       └── DomainEventListener.java
    │   ├── domain/
    │   │   ├── model/
    │   │   │   └── UserDomain.java
    │   │   └── event/
    │   │       └── UserEvent.java
    │   └── infrastructure/
    │       ├── adapter/input/rest/ControllerUser.java
    │       └── adapter/output/persistence/PostgresUserAdapter.java
    └── resources/
        └── application.properties
```

---

## 🧠 Notes

- The domain event listener currently logs events — replace with actual notification integration (e.g., email, queue).  
- Uses **R2DBC** for fully non-blocking persistence.  
- Follows **DDD + Hexagonal Architecture + Reactive programming** principles.

---

Made with ❤️ using Spring WebFlux + DDD.
