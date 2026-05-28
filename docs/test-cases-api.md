# API Test Cases (Sample Set)

## TC-AUTH-001 - Login with valid credentials

- **Priority:** High
- **Request:** `POST /api/login`
- **Input:** valid email + password
- **Expected:** HTTP 200 with non-empty token

## TC-AUTH-002 - Login with missing password

- **Priority:** High
- **Request:** `POST /api/login`
- **Input:** valid email, missing password
- **Expected:** HTTP 400 with error message

## TC-USR-001 - Get existing user

- **Priority:** High
- **Request:** `GET /api/users/{id}`
- **Input:** known existing user id
- **Expected:** HTTP 200 and user payload with expected fields

## TC-USR-002 - Create user

- **Priority:** Medium
- **Request:** `POST /api/users`
- **Input:** valid name and job
- **Expected:** HTTP 201 and response contains `id` and `createdAt`

## TC-USR-003 - Delete user

- **Priority:** Medium
- **Request:** `DELETE /api/users/{id}`
- **Input:** user id
- **Expected:** HTTP 204
