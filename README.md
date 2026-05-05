# ReqRes API Automation Framework

A professional, scalable API test automation framework built with **Java 17**, **RestAssured**, and **TestNG**. This project demonstrates industry-standard practices in automated testing of RESTful services.

## 🚀 Key Features

*   **Modular Architecture**: Clear separation between API clients, data factories, and test logic.
*   **Fluent API Design**: `UserClient` methods designed for readability and ease of use.
*   **Advanced Test Grouping**: Efficient execution using `smoke`, `regression`, and `flaky` tags.
*   **Robust CI/CD**: Fully automated pipeline with **GitHub Actions** including test reporting.
*   **Model-Based Testing**: Utilizes **Lombok** POJOs for request/response mapping and serialization.
*   **Environment Flexibility**: System-property-driven configuration (BaseURL, API Keys).

## 🛠 Tech Stack

*   **Language**: Java 17
*   **API Client**: [RestAssured](https://rest-assured.io/)
*   **Testing Framework**: [TestNG](https://testng.org/)
*   **Data Handling**: Lombok, Jackson Databind, Org.JSON
*   **Assertions**: Hamcrest & AssertJ
*   **CI/CD**: GitHub Actions
*   **Build Tool**: Maven

## 📂 Project Structure

```text
├── .github/workflows       # CI/CD pipeline definitions
├── src/main/java/api
│   ├── client              # Logic for calling specific endpoints
│   ├── config              # Environment and global configuration
│   ├── data                # Factories for creating request objects
│   ├── factory             # RequestSpecification factory (BaseRequest)
│   ├── model/request       # POJOs for API requests (Lombok)
│   └── utils               # JSON readers and helper classes
├── src/test/java/tests
│   ├── api                 # Categorized API tests (Auth, Users)
│   └── base                # BaseTest class for setup/teardown
├── testng.xml              # Main suite definition
└── testng-ci.xml           # Optimized suite for CI pipeline

Getting Started
Prerequisites
Java 17 installed

Maven 3.8+ installed

Installation
1. Clone the repository:
git clone [https://github.com/patric18/reqres-api-tests-java.git](https://github.com/patric18/reqres-api-tests-java.git)

2. Install dependencies:
mvn clean install -DskipTests

### Execution
*   **Run all tests**:
    ```bash
    mvn test

Run CI-optimized suite (Smoke + Regression, No Flaky):

Bash
mvn test -DsuiteXmlFile=testng-ci.xml

*   **Run only Smoke tests**:
    ```bash
    mvn test -Dgroups=smoke

📊 CI/CD Integration
This project uses GitHub Actions to automate the testing lifecycle.

Workflow: Triggered on every push to main and all Pull Requests.

Environment: Ubuntu-latest, Temurin JDK 17.

Artifacts: Detailed Surefire reports are uploaded after every run for debugging.

📝 Design Decisions & Notes
Handling Mock API Limitations
ReqRes is a mock API and does not persist state. To maintain a professional standard:

Tests that fail due to API inconsistency (e.g., ID persistence) are marked with groups = {"flaky"}.

The code includes comments explaining the difference between the mock behavior and a production-grade API (e.g., 204 vs 404 status codes).

Scalability
The use of RequestFactory ensures that any global change (like adding a new Authorization header) is done in one place, affecting all clients automatically.
