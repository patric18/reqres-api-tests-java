package tests.api.users;

import api.client.UserClient;
import org.testng.annotations.Test;

import static org.hamcrest.Matchers.*;
import static org.testng.Assert.assertNotEquals;
import static org.testng.Assert.*;

public class GetUsersTest {

    private final UserClient userClient = new UserClient();


    @Test(groups = {"smoke", "regression"})
    void shouldReturnUsersPage2(){

        userClient.getUsers(2)
                .then()
                .statusCode(200)
                .body("page", equalTo(2));


    }

    @Test(groups = {"smoke", "regression"})
    void shouldReturnNonEmptyUserList(){

        userClient.getUsers(2)
                .then()
                .statusCode(200)
                .body("data.size()", greaterThan(0));

    }

    @Test(groups = {"regression"})
    void shouldValidateUserFieldsExist(){
        userClient.getUsers(2)
                .then()
                .statusCode(200)
                .body("data.id", everyItem(notNullValue()))
                .body("data.email", everyItem(notNullValue()))
                .body("data.first_name", everyItem(notNullValue()))
                .body("data.last_name", everyItem(notNullValue()));
    }

    @Test(groups = {"regression"})
    void shouldValidateEmailFormat(){
        userClient.getUsers(2)
                .then()
                .statusCode(200)
                .body("data.email", everyItem(containsString("@")));
    }

    @Test(groups = {"regression"})
    void shouldMatchPerPageWithDataSize(){
        var response = userClient.getUsers(2);
        int perPage = response.then().extract().path("per_page");

        response.then()
                .statusCode(200)
                .body("data.size()", equalTo(perPage));
    }

    @Test(groups = {"regression"})
    void shouldReturnDifferentDataForDifferentPages(){
        var responsePage1 = userClient.getUsers(1)
                .then()
                .extract()
                .path("data[0].id");

        var responsePage2 = userClient.getUsers(2)
                .then()
                .extract()
                .path("data[0].id");

        assertNotEquals(responsePage1,responsePage2);
    }

    @Test(groups = {"regression"})
    void shouldReturnEmptyListForHighPageNumber(){
        userClient.getUsers(999999)
                .then()
                .statusCode(200)
                .body("data", empty());
    }

    @Test(groups = {"smoke", "regression"})
    void shouldReturnUsersFastEnough(){
        userClient.getUsers(2)
                .then()
                .statusCode(200)
                .time(lessThan(2000L));
    }

    @Test(groups = {"regression"})
    void shouldHaveCorrectResponseStructure(){
        userClient.getUsers(2)
                .then()
                .statusCode(200)
                .body("$", hasKey("page"))
                .body("$", hasKey("per_page"))
                .body("$", hasKey("total"))
                .body("$", hasKey("data"))
                .body("$", hasKey("total_pages"));
    }

    @Test(groups = {"regression"})
    void shouldHaveValidPaginationConsistency(){
        var response = userClient.getUsers(2)
                .then()
                .extract()
                .response();

        int page = response.path("page");
        int totalPages = response.path("total_pages");

        assertTrue(page <= totalPages);
    }

    @Test(groups = {"regression"})
    void shouldNotHaveNullUserObjects(){
        userClient.getUsers(2)
                .then()
                .statusCode(200)
                .body("data", everyItem(notNullValue()));
    }

    @Test(groups = {"regression"})
    void shouldRespectPerPageLimit(){
        var response = userClient.getUsers(2)
                .then()
                .extract()
                .response();

        int perPage = response.path("per_page");
        int size = response.path("data.size()");

        assertTrue(size <= perPage);
    }

    @Test(groups = {"regression"})
    void shouldReturnConsistentUserIdsOrder(){
        var firstCall = userClient.getUsers(2)
                .then()
                .extract()
                .path("data.id");

        var secondCall = userClient.getUsers(2)
                .then()
                .extract()
                .path("data.id");

        assertEquals(firstCall, secondCall);
    }

}
