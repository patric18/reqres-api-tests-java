package tests.api.auth;

import api.client.UserClient;
import org.testng.annotations.Test;

import java.util.Map;

import static org.hamcrest.Matchers.*;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalTo;

public class RegisterTest {

    UserClient userClient = new UserClient();

    @Test
    void shouldRegisterUser(){

        userClient.registerUser("eve.holt@reqres.in", "admin123")
                .then()
                .statusCode(200) // should be 201 in real API
                .body("id", notNullValue())
                .body("id", instanceOf(Number.class))
                .body("token", notNullValue())
                .body("token.length()", greaterThan(5));
    }

    @Test
    void shouldFailRegisterWithoutPassword(){

        userClient.registerUser(Map.of("email","eve.holt@reqres.in"))
                .then()
                .statusCode(400)
                .body("error", equalTo("Missing password"));
    }

    @Test
    void shouldFailRegisterWithoutEmail(){



        userClient.registerUser(Map.of("password","admin123"))
                .then()
                .statusCode(400)
                .body("error", equalTo("Missing email or username"));
    }

    @Test
    void shouldFailRegisterWithEmptyBody(){

        userClient.registerUser(Map.of())
                .then()
                .statusCode(400)
                .body("error", containsString("Missing"));
    }

    @Test
    void shouldFailRegisterWithInvalidEmail() {


        userClient.registerUser("some.some", "admin123")
                .then()
                .statusCode(400)
                .body("error", equalTo("Note: Only defined users succeed registration"));
    }

    @Test
    void shouldHandleDifferentRegisterData(){


        userClient.registerUser("test@test.com", "admin123")
                .then()
                .statusCode(400)
                .body("error", equalTo("Note: Only defined users succeed registration"));

    }


}
