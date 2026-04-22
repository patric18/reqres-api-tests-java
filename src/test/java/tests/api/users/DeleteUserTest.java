package tests.api.users;

import api.client.UserClient;
import org.testng.annotations.Test;

import static org.hamcrest.Matchers.*;

public class DeleteUserTest {

    UserClient userClient = new UserClient();

    // NOTE: ReqRes returns 204 even for non-existing users.
// In real API this should return 404.

    @Test
    void shouldDeleteUser(){
        userClient.deleteUser(2)
                .then()
                .statusCode(204);
    }

    @Test
    void deleteShouldBeIdempotent(){
        userClient.deleteUser(2)
                .then()
                .statusCode(204);

        userClient.deleteUser(2)
                .then()
                .statusCode(204); // should be 404, but ReqRes 204

    }

    @Test
    void shouldDeleteNonExistingUser() {
        userClient.deleteUser(1245)
                .then()
                .statusCode(204); // should be 404, but ReqRes 204
    }

    @Test
    void shouldReturnEmptyBodyOnDelete(){
        userClient.deleteUser(2)
                .then()
                .statusCode(204)
                .body(equalTo(""));
    }

    @Test
    void shouldDeleteUsersWithDifferentIds(){
        int[] ids = {1, 2, 3, 999};

        for (int id : ids) {
            userClient.deleteUser(id)
                    .then()
                    .statusCode(204);
        }
    }

    @Test
    void shouldHandleZeroId(){
        userClient.deleteUser(0)
                .then()
                .statusCode(204);
    }

    @Test
    void shouldHandleNegativeId(){
        userClient.deleteUser(-1)
                .then()
                .statusCode(204);
    }

    @Test
    void shouldHandleVeryLargeId(){
        userClient.deleteUser(Integer.MAX_VALUE)
                .then()
                .statusCode(204);
    }

    @Test
    void shouldDeleteUserQuickly(){
        userClient.deleteUser(2)
                .then()
                .statusCode(204)
                .time(lessThan(2000L));
    }

    @Test
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
