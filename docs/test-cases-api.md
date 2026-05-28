# API Test Cases (Mapped 1:1 to code)

This document reflects current TestNG classes and methods in `src/test/java/tests/api`.

## Execution statistics

- Total test methods: **63**
- Group totals (method-based):
  - `smoke`: **15**
  - `regression`: **62**
  - `flaky`: **21**

## CI execution profile

- `testng.xml`: smoke + regression + flaky
- `testng-ci.xml`: includes `smoke` and `regression`, excludes `flaky`
- Effective CI method count (`testng-ci.xml`): **42**

## Auth - `LoginTest`

- `shouldLoginUserSuccessfully` (`smoke`, `regression`) - login success with token checks.
- `shouldFailLoginWithoutPassword` (`regression`) - 400 + "Missing password".
- `shouldFailLoginWithoutEmail` (`regression`) - 400 + "Missing email or username".
- `shouldFailLoginWithEmptyBody` (`regression`) - 400 with missing-field message.
- `shouldFailLoginWithInvalidEmail` (`regression`, `flaky`) - invalid email scenario.
- `shouldFailLoginWithWrongPassword` (`regression`, `flaky`) - ReqRes mock behavior documented.
- `shouldReturnValidTokenStructure` (`regression`) - token key/type validation.
- `shouldLoginFastEnough` (`smoke`, `regression`) - response time < 2000 ms.
- `shouldReturnSameTokenOnRepeatedLogin` (`flaky`) - token consistency check across repeated logins.

## Auth - `RegisterTest`

- `shouldRegisterUser` (`smoke`, `regression`) - register success with id/token checks.
- `shouldFailRegisterWithoutPassword` (`regression`) - 400 expected.
- `shouldFailRegisterWithoutEmail` (`regression`) - 400 expected.
- `shouldFailRegisterWithEmptyBody` (`regression`) - 400 expected.
- `shouldFailRegisterWithInvalidEmail` (`regression`, `flaky`) - defined-user limitation.
- `shouldHandleDifferentRegisterData` (`regression`, `flaky`) - mock dataset limitation behavior.

## Users - `CreateUserTest`

- `shouldCreateUser` (`smoke`, `regression`) - 201 and payload validation.
- `shouldHandleMissingJobField` (`regression`, `flaky`) - mock API accepts partial body.
- `shouldHandleMissingName` (`regression`, `flaky`) - mock API accepts partial body.
- `shouldHandleEmptyBody` (`regression`, `flaky`) - mock API behavior documented.
- `shouldHandleLongInputValues` (`regression`) - long value handling.
- `shouldReturnValidResponseStructure` (`regression`) - response contract keys check.

## Users - `GetUserTest`

- `shouldReturnSingleUser` (`smoke`, `regression`)
- `shouldValidateEmailFormatStrictly` (`regression`)
- `shouldReturn404ForNotExistingUser` (`smoke`, `regression`)
- `shouldReturnUserFastEnough` (`smoke`, `regression`)
- `shouldHaveCorrectResponseStructure` (`regression`)
- `shouldReturnConsistentUserData` (`regression`)
- `shouldNotHaveNullRequiredFields` (`regression`)
- `shouldMeetContractRequirements` (`smoke`, `regression`)

## Users - `GetUsersTest`

- `shouldReturnUsersPage2` (`smoke`, `regression`)
- `shouldReturnNonEmptyUserList` (`smoke`, `regression`)
- `shouldValidateUserFieldsExist` (`regression`)
- `shouldValidateEmailFormat` (`regression`)
- `shouldMatchPerPageWithDataSize` (`regression`)
- `shouldReturnDifferentDataForDifferentPages` (`regression`)
- `shouldReturnEmptyListForHighPageNumber` (`regression`)
- `shouldReturnUsersFastEnough` (`smoke`, `regression`)
- `shouldHaveCorrectResponseStructure` (`regression`)
- `shouldHaveValidPaginationConsistency` (`regression`)
- `shouldNotHaveNullUserObjects` (`regression`)
- `shouldRespectPerPageLimit` (`regression`)
- `shouldReturnConsistentUserIdsOrder` (`regression`)

## Users - `UpdateUserTest`

- `shouldUpdateUserWithPut` (`smoke`, `regression`)
- `shouldPartiallyUpdateUserWithPatch` (`regression`)
- `shouldHandleEmptyBodyOnUpdate` (`regression`, `flaky`)
- `shouldUpdateNonExistingUser` (`regression`, `flaky`)
- `shouldUpdateUserFastEnough` (`smoke`, `regression`)
- `shouldHaveValidResponseStructure` (`regression`)
- `patchShouldNotOverwriteOtherFields` (`regression`, `flaky`)
- `putShouldBeIdempotent` (`regression`, `flaky`)
- `patchAndPutShouldBothReturnUpdatedAt` (`regression`, `flaky`)
- `shouldHandleNullValues` (`regression`, `flaky`)
- `shouldReturnJsonContentType` (`regression`)

## Users - `DeleteUserTest`

- `shouldDeleteUser` (`smoke`, `regression`)
- `deleteShouldBeIdempotent` (`regression`, `flaky`)
- `shouldDeleteNonExistingUser` (`regression`, `flaky`)
- `shouldReturnEmptyBodyOnDelete` (`regression`)
- `shouldDeleteUsersWithDifferentIds` (`regression`, `flaky`)
- `shouldHandleZeroId` (`regression`, `flaky`)
- `shouldHandleNegativeId` (`regression`, `flaky`)
- `shouldHandleVeryLargeId` (`regression`, `flaky`)
- `shouldDeleteUserQuickly` (`smoke`, `regression`)
- `shouldCreateAndDeleteUser` (`regression`, `flaky`)
