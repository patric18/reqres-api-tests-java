package tests.api.auth;

import api.client.UserClient;
import org.testng.annotations.Test;

import java.util.Map;

import static org.hamcrest.Matchers.*;
import static org.testng.Assert.*;

public class LoginTest {

    // ReqRes is a mock API and does not persist data.
// Some tests are marked as 'flaky' because behavior differs from real APIs.

    UserClient userClient = new UserClient();

    @Test(groups = {"smoke", "regression"})
    void shouldLoginUserSuccessfully(){

        userClient.loginUser("eve.holt@reqres.in", "admin123")
                .then()
                .statusCode(200)
                .body("token", notNullValue())
                .body("token.length()", greaterThan(5));
    }

    @Test(groups = {"regression"})
    void shouldFailLoginWithoutPassword(){

        userClient.loginUser(Map.of("email","eve.holt@reqres.in"))
                .then()
                .statusCode(400)
                .body("error", equalTo("Missing password"));
    }

    @Test(groups = {"regression"})
    void shouldFailLoginWithoutEmail(){

        userClient.loginUser(Map.of("password","admin123"))
                .then()
                .statusCode(400)
                .body("error", equalTo("Missing email or username"));
    }

    @Test(groups = {"regression"})
    void shouldFailLoginWithEmptyBody(){

        userClient.loginUser(Map.of())
                .then()
                .statusCode(400)
                .body("error", containsString("Missing"));
    }

    @Test(groups = {"regression", "flaky"})
    void shouldFailLoginWithInvalidEmail(){

        userClient.loginUser("invalid.email", "admin123")
                .then()
                .statusCode(400)
                .body("error", equalTo("user not found"));
    }

    @Test(groups = {"regression", "flaky"})
    void shouldFailLoginWithWrongPassword(){

        userClient.loginUser("eve.holt@reqres.in", "wrongpass")
                .then()
                .statusCode(200) // ReqRes behavior
                .body("token", notNullValue());

        // NOTE: ReqRes does not validate credentials properly.
        // In real API this should return 401/400.
    }

    @Test(groups = {"regression"})
    void shouldReturnValidTokenStructure(){

        userClient.loginUser("eve.holt@reqres.in", "admin123")
                .then()
                .statusCode(200)
                .body("$", hasKey("token"))
                .body("token", instanceOf(String.class));
    }

    @Test(groups = {"smoke","regression"})
    void shouldLoginFastEnough(){

        userClient.loginUser("eve.holt@reqres.in", "admin123")
                .then()
                .statusCode(200)
                .time(lessThan(2000L));
    }

    //token consistency in Mock API
    @Test(groups = {"flaky"})
    void shouldReturnSameTokenOnRepeatedLogin(){

        var first = userClient.loginUser("eve.holt@reqres.in", "admin123")
                .then()
                .extract()
                .path("token");

        var second = userClient.loginUser("eve.holt@reqres.in", "admin123")
                .then()
                .extract()
                .path("token");

        assertEquals(first, second);
    }


}
