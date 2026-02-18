# Developer Guide: Spring Boot 4 Security 7 JWT

This guide will help other developers understand, configure, and extend the security setup in this project.

## Overview
This project uses Spring Boot 4, Spring Security 7, and JWT for stateless authentication. It includes:
- JWT-based authentication and authorization
- Custom exception handling for unauthorized and access denied responses
- CORS configuration for frontend integration
- Role-based access control

## Key Components
- **SecurityConfiguration.java**: Main security setup (filter chain, CORS, exception handling)
- **JwtAuthenticationFilter**: Validates JWT tokens on incoming requests
- **AuthenticationProvider**: Custom authentication logic
- **Http401UnauthorizedEntryPoint**: Handles unauthorized access
- **CustomAccessDeniedHandler**: Handles forbidden access

## Configuration
### Properties
- `application.properties` or `application.yml` contains DB, JWT, and server settings.
- Set the `POSTGRES_PASSWORD` environment variable before running the app.

### CORS
- Only allows requests from `http://localhost:4200` (Angular default). Update in `SecurityConfiguration` if needed.

### JWT
- Secret key, expiration, and cookie names are set in properties.
- Tokens are validated by `JwtAuthenticationFilter`.

## Running the Application
1. Set the environment variable for the database password:
   - **PowerShell**: `$env:POSTGRES_PASSWORD="your_password"`
   - **CMD**: `set POSTGRES_PASSWORD=your_password`
2. Start your PostgreSQL database (default: localhost:5432, db: db_security, user: postgres).
3. Run the application:
   - `./mvnw spring-boot:run` (Linux/macOS)
   - `mvnw.cmd spring-boot:run` (Windows)

## Extending Security
- To add new public endpoints, update the `.requestMatchers(...).permitAll()` section in `SecurityConfiguration`.
- To restrict endpoints by role, use `.requestMatchers(HttpMethod.POST, "/api/v1/resource").hasRole("ADMIN")` or method-level annotations.
- For new roles/privileges, update the `Role` and `Privilege` enums.

## Error Handling
- Unauthorized (401) and forbidden (403) responses are handled by custom classes for better API feedback.

## Testing
- Use the included test class `SecurityApplicationTests.java` for integration tests.
- Ensure your frontend sends JWT in the Authorization header or as a cookie, as configured.

## Troubleshooting
- If you get DB connection errors, check your environment variable and DB status.
- For CORS issues, update allowed origins in `SecurityConfiguration`.

## Contribution
- Follow standard Java and Spring Boot best practices.
- Write tests for new features.
- Document any new configuration or endpoints.

---
For more details, see the code comments and configuration files.

