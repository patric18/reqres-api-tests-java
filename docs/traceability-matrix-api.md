# Traceability Matrix - API (Current implementation)

| Requirement | Description | Automated test mapping |
|---|---|---|
| RQ-AUTH-LOGIN | Login endpoint positive/negative validation | `LoginTest` methods |
| RQ-AUTH-REGISTER | Register endpoint positive/negative validation | `RegisterTest` methods |
| RQ-USERS-CREATE | User creation and payload contract checks | `CreateUserTest` methods |
| RQ-USERS-GET-ONE | Single user retrieval and response contract | `GetUserTest` methods |
| RQ-USERS-GET-LIST | Paginated user list and consistency checks | `GetUsersTest` methods |
| RQ-USERS-UPDATE | PUT/PATCH update behavior and structure checks | `UpdateUserTest` methods |
| RQ-USERS-DELETE | Delete behavior, idempotency, edge IDs | `DeleteUserTest` methods |

## Group mapping from TestNG

- `smoke`: fast critical checks across auth/users
- `regression`: broad functional and contract coverage
- `flaky`: known ReqRes mock-behavior-dependent checks

## Endpoint coverage

| Endpoint | Methods covered | Test classes |
|---|---|---|
| `POST /api/login` | positive, missing fields, invalid credentials, response-time, token checks | `LoginTest` |
| `POST /api/register` | positive, missing fields, invalid/unsupported users | `RegisterTest` |
| `GET /api/users/{id}` | existing, non-existing, schema, response-time, consistency | `GetUserTest` |
| `GET /api/users?page={n}` | pagination, list integrity, schema, response-time | `GetUsersTest` |
| `POST /api/users` | create positive + partial/empty body behavior | `CreateUserTest` |
| `PUT /api/users/{id}` | full update, structure, idempotency, response-time | `UpdateUserTest` |
| `PATCH /api/users/{id}` | partial update, null handling, updatedAt checks | `UpdateUserTest` |
| `DELETE /api/users/{id}` | delete, idempotency, edge ID behavior, response-time | `DeleteUserTest` |
