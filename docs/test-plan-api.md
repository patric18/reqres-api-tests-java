# Test Plan - ReqRes API

## Objective

Validate critical API behaviors for authentication and user resources with repeatable automated checks.

## In scope

- Auth endpoints (`login`, `register`)
- User endpoints (`GET`, `POST`, `PUT/PATCH`, `DELETE`)
- Response status and schema-level validations
- Selected negative paths for invalid/missing data

## Out of scope

- Performance and load testing
- Security and penetration testing
- Contract testing against independent producer/consumer contracts

## Test approach

- API-level automation via RestAssured
- Grouping strategy:
  - `smoke` for fast confidence checks
  - `regression` for broader functional verification
  - `flaky` for known mock-environment instability

## Entry criteria

- ReqRes service reachable
- Build dependencies available
- CI agent with Java 17

## Exit criteria

- Smoke suite passes on PR
- Regression suite passes on `main`
- No open high-severity issues in covered scope

## Risks and mitigations

- **Risk:** Mock API non-persistent behavior  
  **Mitigation:** Explicitly document expected mock limitations and isolate flaky checks.

- **Risk:** External API downtime  
  **Mitigation:** CI retry policy and clear error reporting from surefire artifacts.
