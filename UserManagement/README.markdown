# User Management System

A Spring Boot application implementing a User Management & Role Assignment System following Clean Architecture principles, using an H2 in-memory database.

## Prerequisites

- Java 17 or higher
- Maven

## Setup

1. **Clone the repository**:
   ```bash
   git clone <repository-url>
   cd usermanagement
   ```

2. **Build the project**:
   ```bash
   mvn clean install
   ```

3. **Run the application**:
   ```bash
   mvn spring-boot:run
   ```

4. **Access the H2 Console**:
   - Open a browser and navigate to `http://localhost:9090/h2-console`.
   - Use the following settings:
     - JDBC URL: `jdbc:h2:mem:usermanagement`
     - Username: `sa`
     - Password: (leave blank)
   - Click "Connect" to view the database.

## API Endpoints

- **Create User**: `POST http://localhost:9090/users`
  - Body: `{ "name": "John Doe", "email": "john@example.com" }`
  - Response: User ID (UUID)

- **Create Role**: `POST http://localhost:9090/roles`
  - Body: `{ "roleName": "ADMIN" }`
  - Response: Role ID (UUID)

- **Assign Role to User**: `POST http://localhost:9090/users/{userId}/assign-role/{roleId}`
  - Response: Success message

- **Get User Details**: `GET http://localhost:9090/users/{id}`
  - Response: User details with assigned roles

## Testing

- **Run unit tests**:
  ```bash
  mvn test
  ```

- Tests are located in `src/test/java/com/example/usermanagement/application`.

## Notes

- The application follows Clean Architecture with separated layers (Domain, Application, Infrastructure, Configuration).
- Validation ensures non-blank names/role names and valid email formats.
- Error handling returns appropriate HTTP status codes (e.g., 404 for not found, 400 for invalid input).
- DTOs are used to avoid exposing domain entities in the API.
- The H2 database is in-memory and resets on application restart.