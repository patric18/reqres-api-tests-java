# ReqRes API Test Automation (QA Portfolio)

API automation framework for [ReqRes](https://reqres.in/) built with Java, RestAssured, Maven, and TestNG.

## What a recruiter can verify in 2 minutes

- API test design for auth and users endpoints.
- Maintainable structure: client layer, request factories, models, tests.
- Tagged execution strategy: `smoke`, `regression`, `flaky`.
- CI pipeline with report artifacts on every PR/push.
- QA documentation in `docs/` (API test plan, test cases, bug notes, traceability).

## Tech stack

- Java 17
- RestAssured
- TestNG
- Maven
- Hamcrest / AssertJ
- GitHub Actions

## Project structure

```text
reqres-api-tests-java/
├── .github/workflows/      # CI pipeline
├── src/main/java/api/
│   ├── client/             # API clients per endpoint domain
│   ├── config/             # Environment configuration
│   ├── data/               # Request data factories
│   ├── factory/            # Shared request specification factory
│   ├── model/request/      # Request payload models
│   └── utils/              # Helpers
├── src/test/java/tests/
│   ├── api/                # API suites by feature
│   ├── base/               # Base test setup
│   └── data/               # Test data
├── docs/                   # Manual QA artifacts
├── testng.xml
└── testng-ci.xml
```

## Run locally

```bash
git clone https://github.com/patric18/reqres-api-tests-java.git
cd reqres-api-tests-java
mvn clean test
```

Run CI-oriented suite:

```bash
mvn test -DsuiteXmlFile=testng-ci.xml
```

Run only smoke:

```bash
mvn test -Dgroups=smoke
```

## CI

- Triggered on `push` to `main` and on each pull request.
- Uses JDK 17 and Maven dependency cache.
- Uploads `target/surefire-reports` as build artifact for inspection.

## Notes on ReqRes behavior

ReqRes is a mock API and does not persist full state like production services.  
Some scenarios are intentionally marked with `flaky` group where API behavior is inconsistent or intentionally simplified.

## QA documentation

- `docs/test-plan-api.md`
- `docs/test-cases-api.md`
- `docs/bug-reports-api.md`
- `docs/traceability-matrix-api.md`
