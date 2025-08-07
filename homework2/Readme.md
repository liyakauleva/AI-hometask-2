# Homework2 Spring Boot Web Project

## Overview
This project is a modern Java Spring Boot web application with JWT-based authentication, PostgreSQL database, Dockerized deployment, and a layered architecture. It demonstrates best practices for secure user management, RESTful API design, and integration testing.

---

## Business Logic
- **User Management:**
  - CRUD operations for users, following the JSONPlaceholder `/users` schema (name, username, email, address, company, etc.).
  - Input validation for all user fields.
- **Authentication:**
  - Users register with name, email, and password (bcrypt-hashed).
  - JWT tokens are issued on login and required for all protected endpoints.
  - Only `/auth/register` and `/auth/login` are public; all other endpoints require a valid JWT.
- **Database:**
  - PostgreSQL is used for persistent storage.
  - Flyway is used for database migrations and seeding test data.

---

## Technical Details
- **Frameworks & Libraries:**
  - Spring Boot 3 (Web, Data JPA, Security, Validation)
  - PostgreSQL
  - Flyway (migrations)
  - Lombok (boilerplate reduction)
  - JWT (io.jsonwebtoken)
  - Docker & Docker Compose
  - JUnit, Mockito, Testcontainers (testing)
- **Architecture:**
  - Layered: Controller → Service → Repository → Database
  - DTOs for all API input/output (no entity exposure)
  - Mapper class for entity/DTO conversion
  - Global exception handling
- **Security:**
  - JWT authentication for all protected endpoints
  - Passwords stored as bcrypt hashes
  - CSRF disabled for stateless API

---

## Setup & Running

### Prerequisites
- Java 17+
- Maven 3.8+
- Docker & Docker Compose

### Local Development
1. **Clone the repository:**
   ```sh
   git clone <repo-url>
   cd homework2
   ```
2. **Start PostgreSQL with Docker Compose:**
   ```sh
   docker-compose up -d db
   ```
3. **Build and run the app:**
   ```sh
   mvn clean spring-boot:run -f homework2/pom.xml
   ```
   Or run both app and db with:
   ```sh
   docker-compose up --build
   ```

### Running Tests
```sh
mvn test -f homework2/pom.xml
```
- Unit tests use JUnit and Mockito.
- Integration tests use Testcontainers for real PostgreSQL.

---

## API Endpoints

### Authentication
- `POST /auth/register` — Register a new user (name, email, password)
- `POST /auth/login` — Login and receive JWT token

### Users (JWT required)
- `GET /users` — List all users
- `GET /users/{id}` — Get user by ID
- `POST /users` — Create user
- `PUT /users/{id}` — Update user (all fields)
- `PATCH /users/{id}` — Partial update
- `DELETE /users/{id}` — Delete user

### Example JWT Usage
Include the token in the `Authorization` header:
```
Authorization: Bearer <token>
```

---

## Database Schema
- **users**: Matches JSONPlaceholder `/users` (id, name, username, email, address, company, ...)
- **authentication**: Stores registered users (id, name, email, password_hash)
- Flyway migrations auto-create and seed tables on startup.

---

## Development Notes
- All DTOs and entities use Lombok for getters/setters.
- All business logic is in the service layer; controllers are thin.
- Exception handling returns proper HTTP status codes and messages.
- The project is ready for extension (roles, more endpoints, etc.).
