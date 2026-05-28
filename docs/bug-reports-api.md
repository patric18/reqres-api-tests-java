# API Bug Reports (Portfolio Examples)

## BUG-API-001 - Non-persistent data after create

- **Severity:** Medium
- **Priority:** Medium
- **Endpoint:** `POST /api/users` + follow-up `GET`
- **Expected:** Created resource should be retrievable
- **Actual:** Resource persistence is not guaranteed in ReqRes mock API
- **Note:** Documented as environment constraint, not framework defect.

## BUG-API-002 - Inconsistent response details on edge cases

- **Severity:** Minor
- **Priority:** Low
- **Endpoint:** User-related edge requests
- **Expected:** Stable response payload for equivalent invalid inputs
- **Actual:** Error detail/shape may vary between runs
- **Note:** Cases tagged where needed to avoid unstable CI signals.
