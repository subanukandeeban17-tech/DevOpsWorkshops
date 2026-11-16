# DevOps Gear Store (E-Commerce Web Application)

A full-stack Java Spring Boot e-commerce web application built for DevOps CI/CD, Docker, and Kubernetes practice.

## Project Structure
- `pom.xml` - Maven configuration & Spring Boot dependencies
- `src/main/java/com/example/ecommerce/EcommerceApplication.java` - Main Spring Boot entry point
- `src/main/java/com/example/ecommerce/model/Product.java` - Product data model
- `src/main/java/com/example/ecommerce/controller/StoreController.java` - REST APIs for catalog, cart, checkout, and health check
- `src/main/resources/static/index.html` - Responsive frontend store UI
- `src/test/java/com/example/ecommerce/EcommerceApplicationTests.java` - JUnit integration tests

## Build & Run
1. Build JAR & run tests: `mvn clean package`
2. Start server: `java -jar target/devops-ecommerce-1.0.0.jar`
3. Open browser: `http://localhost:8080`
4. Health check endpoint: `http://localhost:8080/api/health`
