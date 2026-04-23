package tests.api.users;

import api.client.UserClient;
import org.testng.annotations.Test;

import java.util.Map;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.Matchers.*;

public class CreateUserTest {

    // ReqRes is a mock API and does not persist data.
// Some tests are marked as 'flaky' because behavior differs from real APIs.

    UserClient userClient = new UserClient();

    @Test(groups = {"smoke", "regression"})
    void shouldCreateUser(){

        userClient.createUser("Jan", "cooker")
                .then()
                .statusCode(201)
                .body("name", equalTo("Jan"))
                .body("job", equalTo("cooker"))
                .body("id", notNullValue())
                .body("createdAt", notNullValue());
    }

    // it should be 400 not 201
    @Test(groups = {"regression", "flaky"})
    void shouldHandleMissingJobField(){


        userClient.createUser(Map.of("name","Jan"))
                .then()
                .statusCode(201)
                .body("name", equalTo("Jan"))
                .body("id", notNullValue());

    }

    // it should be 400 not 201
    @Test(groups = {"regression", "flaky"})
    void shouldHandleMissingName(){

        userClient.createUser(Map.of("job", "cooker"))
                .then()
                .statusCode(201)
                .body("job", equalTo("cooker"))
                .body("id", notNullValue());
    }

    // it should be 400 not 201
    @Test(groups = {"regression", "flaky"})
    void shouldHandleEmptyBody(){
        userClient.createUser(Map.of())
                .then()
                .statusCode(201); //reqres 400
    }


    @Test(groups = {"regression"})
    void shouldHandleLongInputValues(){
        String longName = "a".repeat(100);

        userClient.createUser(longName,"tester")
                .then()
                .statusCode(201)
                .body("name", equalTo(longName));
    }

    @Test(groups = {"regression"})
    void shouldReturnValidResponseStructure(){
        userClient.createUser("Jan","tester")
                .then()
                .statusCode(201)
                .body("id", notNullValue())
                .body("createdAt", notNullValue())
                .body("$", hasKey("name"))
                .body("$", hasKey("job"));
    }


}
