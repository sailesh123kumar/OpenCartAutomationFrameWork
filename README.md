# OpenCart Automation Framework

This project is a Java-based automation framework designed to test the functionalities of the [OpenCart](https://www.opencart.com/) e-commerce platform.

---

## Features

- **User Registration**: Automates the process of registering a new user on the OpenCart platform.
- **User Login**: Tests the login functionality for existing users.
- **Product Browsing**: Navigates through various product categories and pages.
- **Product Search**: Searches for products using the search bar.
- **Shopping Cart**: Adds products to the cart and validates cart functionality.
- **Checkout Process**: Automates the checkout process, including entering address details and payment.
- **CI/CD Ready**: Includes support for Docker and Jenkins for continuous integration and deployment.

---

## Technology Stack

- **Programming Language**: Java
- **Testing Framework**: TestNG
- **Build Tool**: Maven
- **Automation Tool**: Selenium WebDriver
- **Design Pattern**: Page Object Model (POM)
- **CI/CD Tools**: Docker, Jenkins

---

## Project Structure

### 1. **Utility Package**
   - Contains helper classes and methods to support automation testing.
   - Examples include `ConfigReader` for reading configuration files, `ExcelUtil` for handling data-driven testing, and `DriverManager` for browser setup.

### 2. **BaseTest**
   - Provides the foundation for all test cases.
   - Handles WebDriver initialization and teardown.
   - Reads configurations and applies them globally, including browser setup, implicit waits, and test-specific parameters.

### 3. **Test Package**
   - Contains modular TestNG test classes for different functionalities of the OpenCart platform.
   - Groups related test cases for better organization and maintainability.

### 4. **Listeners**
   - Implements TestNG listeners to enhance the test execution process:
     - **`ITestListener`**: Tracks and reports test status.
     - **Retry Logic**: Retries flaky tests using `RetryAnalyzer`.
     - **Enhanced Reporting**: Captures logs and screenshots for failed test cases.

### 5. **Test Runner**
   - Centralized `testng.xml` file:
     - Specifies the suite configuration.
     - Defines test groups, parallel execution, and test methods.
   - Supports parameterized test execution.

---

## Integration with Docker

### Why Docker?
- Standardized environment for test execution.
- Easy integration into CI/CD pipelines.
- Portable and lightweight solution for running tests on any system.

### Docker Setup
1. **Create a `Dockerfile`**:
   ```dockerfile
   FROM openjdk:8-jdk-alpine
   WORKDIR /app
   COPY . /app
   RUN ./mvnw clean install
   CMD ["mvn", "test"]
   
