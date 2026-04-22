package tests.api.auth;

import api.client.UserClient;
import org.testng.annotations.Test;

import java.util.Map;

import static org.hamcrest.Matchers.*;
import static org.testng.Assert.*;

public class LoginTest {

    UserClient userClient = new UserClient();

    @Test
    void shouldLoginUserSuccessfully(){

        userClient.loginUser("eve.holt@reqres.in", "admin123")
                .then()
                .statusCode(200)
                .body("token", notNullValue())
                .body("token.length()", greaterThan(5));
    }

    @Test
    void shouldFailLoginWithoutPassword(){

        userClient.loginUser(Map.of("email","eve.holt@reqres.in"))
                .then()
                .statusCode(400)
                .body("error", equalTo("Missing password"));
    }

    @Test
    void shouldFailLoginWithoutEmail(){

        userClient.loginUser(Map.of("password","admin123"))
                .then()
                .statusCode(400)
                .body("error", equalTo("Missing email or username"));
    }

    @Test
    void shouldFailLoginWithEmptyBody(){

        userClient.loginUser(Map.of())
                .then()
                .statusCode(400)
                .body("error", containsString("Missing"));
    }

    @Test
    void shouldFailLoginWithInvalidEmail(){

        userClient.loginUser("invalid.email", "admin123")
                .then()
                .statusCode(400)
                .body("error", equalTo("user not found"));
    }

    @Test
    void shouldFailLoginWithWrongPassword(){

        userClient.loginUser("eve.holt@reqres.in", "wrongpass")
                .then()
                .statusCode(200) // ReqRes behavior
                .body("token", notNullValue());

        // NOTE: ReqRes does not validate credentials properly.
        // In real API this should return 401/400.
    }

    @Test
    void shouldReturnValidTokenStructure(){

        userClient.loginUser("eve.holt@reqres.in", "admin123")
                .then()
                .statusCode(200)
                .body("$", hasKey("token"))
                .body("token", instanceOf(String.class));
    }

    @Test
    void shouldLoginFastEnough(){

        userClient.loginUser("eve.holt@reqres.in", "admin123")
                .then()
                .statusCode(200)
                .time(lessThan(2000L));
    }

    @Test
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
