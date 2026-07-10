# Student Management REST API

A well-structured RESTful API built with Java and Spring Boot, demonstrating solid backend architecture patterns (DTO pattern, layered architecture, validation, and centralized exception handling) with a real PostgreSQL database.

## 🚀 Key Features

- **Strict Data Validation** — Prevents empty or invalid inputs at creation time. Enforces constraints like GPA limits (0.0–4.0) and validates Egyptian phone number format using regex.
- **Unique Business Identity** — Treats the student's phone number as a unique business identifier, preventing duplicate entries at both the Service layer and the Database level.
- **DTO Pattern (Java Records)** — Complete separation between database entities and API contracts. The internal `Student` entity is never exposed directly; immutable Java Records are used for clean, secure data transfer.
- **Advanced Querying & Pagination** — Built-in pagination and sorting, plus custom search endpoints for exact grade match, GPA range, partial name match, and phone number lookup.
- **Smart Partial Updates** — Proper `PATCH` implementation that updates only the fields provided in the request, without requiring the full object or triggering validation errors on unchanged fields.
- **Global Exception Handling** — Centralized `@RestControllerAdvice` catches validation errors and custom exceptions (like `DuplicateResourceException`), returning clean, standardized JSON error responses.

## 💡 Key Concepts Practiced

- DTO pattern with Java Records for immutable data transfer
- Constructor-based dependency injection
- Custom validation with Bean Validation (`@NotNull`, `@DecimalMin`, `@Pattern`...)
- Partial updates without violating immutability
- Global exception handling with `@RestControllerAdvice`
- Layered architecture (Controller → Service → Repository)

## 🛠️ Tech Stack

| Layer | Technology                              |
|---|-----------------------------------------|
| Language | Java 25                                 |
| Framework | Spring Boot (Web, Data JPA, Validation) |
| Database | PostgreSQL                              |
| Build Tool | Maven                                   |
| Architecture | Controller → Service → Repository       |

## 📋 Prerequisites

- Java 17 or higher
- Maven 3.8+
- PostgreSQL 14+ (installed and running locally)

## ⚙️ Setup and Running

1. Clone the repository:
   ```bash
   git clone <your-repo-url>
   cd students
   ```

2. Create a PostgreSQL database:
   ```sql
   CREATE DATABASE students;
   ```

3. Update `src/main/resources/application.properties` with your database credentials:
   ```properties
   spring.datasource.url=jdbc:postgresql://localhost:5432/students
   spring.datasource.username=your_username
   spring.datasource.password=your_password
   ```

4. Run the application:
   ```bash
   mvn spring-boot:run
   ```
   Or run `StudentsApplication.java` directly from your IDE.

   Hibernate will automatically create the required tables on startup.

5. The API will be available at `http://localhost:8080`.

## 📁 Project Structure

```
src/main/java/com/yusuf/students/
├── controllers/          # REST endpoints
├── services/             # Business logic
├── repositories/         # Data access (Spring Data JPA)
├── entities/             # Database entities
├── DTOs/                 # Request/response records & mappers
├── exceptions/           # Custom exceptions
└── exceptions_handling/  # Global exception handler (@RestControllerAdvice)
```

## 📡 API Endpoints

### Student Registration & Modification

| Method | Endpoint | Description |
|---|---|---|
| `POST` | `/api/v1/student` | Register a new student (full validation) |
| `PATCH` | `/api/v1/student/{id}` | Partially update an existing student |
| `DELETE` | `/api/v1/student/{id}` | Delete a student record |

### Data Retrieval

| Method | Endpoint | Description |
|---|---|---|
| `GET` | `/api/v1/student?page=0&size=5` | Get all students (paginated, sortable) |
| `GET` | `/api/v1/student/id/{id}` | Get a student by database ID |
| `GET` | `/api/v1/student/number/{phoneNumber}` | Get a student by phone number |
| `GET` | `/api/v1/student/gpa-range?minGpa=2.5&maxGpa=3.5` | Filter students by GPA range |
| `GET` | `/api/v1/student/search?name={name}&phoneNumber={phone}` | Search by name and/or phone number |

## 🔍 Example Usage

**Register a student**

`POST /api/v1/student`
```json
{
  "name": "Ahmed Ali",
  "phoneNumber": "+201012345678",
  "grade": 3,
  "gpa": 3.75
}
```

**Response — `201 Created`**
```json
{
  "Message": "Student added successfully!"
}
```

**Partial update**

`PATCH /api/v1/student/1`
```json
{
  "gpa": 3.9
}
```
Only `gpa` is updated — all other fields remain unchanged, and no validation errors are thrown for the omitted fields.

## ⚠️ Error Responses

| Status Code | Scenario |
|---|---|
| `400 Bad Request` | Validation failed (e.g. invalid GPA, malformed phone number) |
| `404 Not Found` | Student not found |
| `409 Conflict` | Duplicate phone number |

Example error response (validation):
```json
{
  "gpa": "GPA cannot exceed 4.0",
  "phoneNumber": "Phone number must be a valid Egyptian number"
}
```

Example error response (duplicate):
```json
{
  "error": "Student already exists"
}
```

## 🗺️ Roadmap

- [ ] Add authentication/authorization (JWT)
- [ ] Add unit and integration tests
- [ ] Dockerize the application
- [ ] Add CI/CD pipeline

## 📄 License

This project is for educational/portfolio purposes.
