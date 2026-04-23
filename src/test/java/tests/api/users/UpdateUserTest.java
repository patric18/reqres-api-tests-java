package tests.api.users;

import api.client.UserClient;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.Matchers.*;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;
import static org.testng.Assert.*;

public class UpdateUserTest {

    // ReqRes is a mock API and does not persist data.
// Some tests are marked as 'flaky' because behavior differs from real APIs.

    UserClient userClient = new UserClient();

    @Test(groups = {"smoke", "regression"})
    void shouldUpdateUserWithPut(){


        userClient.updateUser(2,"Marek","cleaning")
                .then()
                .statusCode(200)
                .body("name", equalTo("Marek"))
                .body("job", equalTo("cleaning"))
                .body("updatedAt", notNullValue());
    }


    @Test(groups = {"regression"})
    void shouldPartiallyUpdateUserWithPatch(){

        Map<String,Object> body = new HashMap<>();
        body.put("job","astronaut");

        userClient.patchUser(2,body)
                .then()
                .statusCode(200)
                .body("job", equalTo("astronaut"))
                .body("updatedAt", notNullValue());
    }

    //not validated body
    @Test(groups = {"regression", "flaky"})
    void shouldHandleEmptyBodyOnUpdate(){

        userClient.updateUser(2,Map.of())
                .then()
                .statusCode(200)
                .contentType(containsString("application/json"))
                .body("updatedAt", notNullValue());


    }

    //it should be 404 in Real API
    @Test(groups = {"regression", "flaky"})
    void shouldUpdateNonExistingUser(){

        userClient.updateUser(999,"some","plumber")
                .then()
                .statusCode(200)
                .body("name", equalTo("some"))
                .body("job", equalTo("plumber"))
                .body("updatedAt", notNullValue());
    }

    @Test(groups = {"smoke", "regression"})
    void shouldUpdateUserFastEnough(){
        userClient.updateUser(2,"Marek","cleaning")
                .then()
                .statusCode(200)
                .time(lessThan(2000L));
    }

    @Test(groups = {"regression"})
    void shouldHaveValidResponseStructure(){
        userClient.updateUser(2,"Marek","cleaning")
                .then()
                .statusCode(200)
                .body("$", hasKey("name"))
                .body("$", hasKey("job"))
                .body("$", hasKey("updatedAt"));
    }

    // patch does not return full object in reqres
    @Test(groups = {"regression", "flaky"})
    void patchShouldNotOverwriteOtherFields(){

        userClient.patchUser(2, Map.of("job","astronaut"))
                .then()
                .statusCode(200)
                .body("job", equalTo("astronaut"))
                .body("updatedAt", notNullValue());
        // ReqRes does not return full updated user object after PATCH
    }

    @Test(groups = {"regression", "flaky"})
    void putShouldBeIdempotent(){
        userClient.updateUser(2,"Marek","cleaning")
                .then()
                .statusCode(200);

        userClient.updateUser(2,"Marek","cleaning")
                .then()
                .statusCode(200);
    }

    @Test(groups = {"regression", "flaky"})
    void patchAndPutShouldBothReturnUpdatedAt(){
        var putResponse = userClient.updateUser(2,"Marek","cleaning")
                .then()
                .extract()
                .path("updatedAt");


        var patchResponse = userClient.patchUser(2, Map.of("job","astronaut"))
                .then()
                .extract()
                .path("updatedAt");

        assertNotEquals(putResponse, patchResponse);

    }

    //reqres accepts null without validation
    @Test(groups = {"regression", "flaky"})
    void shouldHandleNullValues(){

        Map<String, Object> body = new HashMap<>();
        body.put("job", null);
        userClient.patchUser(2, body)
                .then()
                .statusCode(200);
    }

    @Test(groups = {"regression"})
    void shouldReturnJsonContentType(){
        userClient.updateUser(2,"Marek","cleaning")
                .then()
                .statusCode(200)
                .contentType(containsString("application/json"));
    }

}
