package tests.api.users;

import api.client.UserClient;
import org.testng.annotations.Test;

import static org.hamcrest.Matchers.*;
import static org.testng.Assert.*;

public class GetUserTest {

    UserClient userClient = new UserClient();

    @Test
    void shouldReturnSingleUser(){
        userClient.getUser(2)
                .then()
                .statusCode(200)
                .body("data.id", equalTo(2))
                .body("data.email", containsString("@"));
    }

    @Test
    void shouldValidateEmailFormatStrictly(){
        userClient.getUser(2)
                .then()
                .statusCode(200)
                .body("data.email", matchesRegex("^[A-Za-z0-9+_.-]+@(.+)$"));
    }

    @Test
    void shouldReturn404ForNotExistingUser(){
        userClient.getUser(2312)
                .then()
                .statusCode(404);
    }

    @Test
    void shouldReturnUserFastEnough(){
        userClient.getUser(2)
                .then()
                .statusCode(200)
                .time(lessThan(2000L));
    }

    @Test
    void shouldHaveCorrectResponseStructure(){
        userClient.getUser(2)
                .then()
                .statusCode(200)
                .body("$", hasKey("data"))
                .body("data", hasKey("id"))
                .body("data", hasKey("email"))
                .body("data", hasKey("first_name"))
                .body("data", hasKey("last_name"));
    }

    @Test
    void shouldReturnConsistentUserData(){
        var firstCall = userClient.getUser(2)
                .then()
                .extract()
                .path("data.email");

        var secondCall = userClient.getUser(2)
                .then()
                .extract()
                .path("data.email");

        assertEquals(firstCall, secondCall);
    }

    @Test
    void shouldNotHaveNullRequiredFields(){
        userClient.getUser(2)
                .then()
                .statusCode(200)
                .body("data.first_name", notNullValue())
                .body("data.last_name", notNullValue());
    }

    @Test
    void shouldMeetContractRequirements(){
        userClient.getUser(2)
                .then()
                .statusCode(200)
                .time(lessThan(2000L))
                .body("data.id", equalTo(2))
                .body("data.email", containsString("@"))
                .body("data.first_name", notNullValue());
    }


}
