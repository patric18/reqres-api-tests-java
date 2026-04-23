package tests.api.users;

import api.client.UserClient;
import org.testng.annotations.Test;

import static org.hamcrest.Matchers.*;

public class DeleteUserTest {

    UserClient userClient = new UserClient();

    // ReqRes is a mock API and does not persist data.
// Some tests are marked as 'flaky' because behavior differs from real APIs.

    @Test(groups = {"smoke", "regression"})
    void shouldDeleteUser(){
        userClient.deleteUser(2)
                .then()
                .statusCode(204);
    }

    @Test(groups = {"regression", "flaky"})
    void deleteShouldBeIdempotent(){
        userClient.deleteUser(2)
                .then()
                .statusCode(204);

        userClient.deleteUser(2)
                .then()
                .statusCode(204); // should be 404, but ReqRes 204

    }

    @Test(groups = {"regression", "flaky"})
    void shouldDeleteNonExistingUser() {
        userClient.deleteUser(1245)
                .then()
                .statusCode(204); // should be 404, but ReqRes 204
    }

    @Test(groups = {"regression"})
    void shouldReturnEmptyBodyOnDelete(){
        userClient.deleteUser(2)
                .then()
                .statusCode(204)
                .body(equalTo(""));
    }

    @Test(groups = {"regression", "flaky"})
    void shouldDeleteUsersWithDifferentIds(){
        int[] ids = {1, 2, 3, 999};

        for (int id : ids) {
            userClient.deleteUser(id)
                    .then()
                    .statusCode(204);
        }
    }

    @Test(groups = {"regression", "flaky"})
    void shouldHandleZeroId(){
        userClient.deleteUser(0)
                .then()
                .statusCode(204);
    }

    @Test(groups = {"regression", "flaky"})
    void shouldHandleNegativeId(){
        userClient.deleteUser(-1)
                .then()
                .statusCode(204);
    }

    @Test(groups = {"regression", "flaky"})
    void shouldHandleVeryLargeId(){
        userClient.deleteUser(Integer.MAX_VALUE)
                .then()
                .statusCode(204);
    }

    @Test(groups = {"smoke", "regression"})
    void shouldDeleteUserQuickly(){
        userClient.deleteUser(2)
                .then()
                .statusCode(204)
                .time(lessThan(2000L));
    }

    @Test(groups = {"regression", "flaky"})
    void shouldCreateAndDeleteUser(){
        int userId = Integer.parseInt(userClient.createUser("Jan","tester")
                .then()
                .extract()
                .path("id")
        );

        userClient.deleteUser(userId)
                .then()
                .statusCode(204);

    }

}
