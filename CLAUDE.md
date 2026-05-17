# CLAUDE.md

This file provides guidance to Claude Code (claude.ai/code) when working with code in this repository.

## Build & Run Commands

```bash
# Run the application
./gradlew bootRun

# Build (includes PiTest mutation tests)
./gradlew build

# Build without tests
./gradlew clean build -x test

# Run unit tests
./gradlew test

# Run mutation tests (PiTest) — generates HTML report in build/reports/pitest/
./gradlew pitest
```

**Docker:**
```bash
docker build -t spring-with-react .
docker run -p 8080:8080 spring-with-react
```

## Architecture Overview

This is a **Spring Boot 3.4.4 REST API** (Java 17, Gradle) with no frontend — it's backend-only, CORS-enabled for external UIs.

### Layer Structure

```
Controllers → Services → Repositories → JPA Entities → PostgreSQL
```

Three vertical slices, each with its own controller/service/repository:
- **User** (`/users`) — CRUD + authentication
- **UserDocument** (`/userdocument`) — file upload/retrieval
- **UserGoals** (`/user/goals`) — user goal management

### Cross-Cutting Components

- **`CorrelationIdFilter`** — Servlet filter that generates or extracts a `correlationId` from request headers and puts it in MDC for all log lines.
- **`LoggingAspect`** — AOP aspect (`@Before`) that logs method entry for all classes in `com.example.spring_with_react`.
- **`OrderedMDCConverter`** — Custom Logback converter that formats MDC fields into structured log output.

### Entity Relationships

```
UserEntity (1) ──── (*) UserDocUploadEntity
UserEntity (1) ──── (*) UserGoalsEntity (*) ──── (1) GoalEntity
```

- `UserEntity` stores passwords encrypted via PostgreSQL `pgp_sym_encrypt/decrypt` (key: `'sprint_with_react_123'`).
- UUIDs are used for users and goals; auto-increment integers for document IDs.

## Database

PostgreSQL required locally at `localhost:5432/postgres`.
- Username: `postgres`, Password: `qa`
- Config: `src/main/resources/application.properties`

The password encryption/decryption happens at the SQL level using pgcrypto functions, not in Java code — queries in the repository layer use native SQL with `pgp_sym_encrypt`/`pgp_sym_decrypt`.

## Testing

Tests live in `src/test/java/com/example/spring_with_react/controller/`. The existing `UserControllerTest` uses `MockMvc` + Mockito and verifies MDC/correlation ID behavior alongside endpoint logic.

PiTest mutation coverage threshold is currently set to 0% — the Todo.md notes this should be raised to 95%.

## Pending Work (from Todo.md)

- Unit and integration tests (WireMock for integration)
- Kafka implementation
- Swagger/OpenAPI documentation
- JaCoCo coverage reports
- Increase PiTest mutation coverage threshold to 95%
