package api.client;


import api.factory.RequestFactory;
import io.restassured.response.Response;

import java.util.HashMap;
import java.util.Map;

public class UserClient {

    public Response getUsers(int page){
        return RequestFactory.baseRequest()
                .queryParam("page", page)
                .when()
                .get("/users");
    }

    public Response getUser(int id){
        return RequestFactory.baseRequest()
                .when()
                .get("/users/" +id);
    }

    public Response createUser(String name, String job){

        Map<String,String> body = new HashMap<>();
        body.put("name", name);
        body.put("job", job);

        return RequestFactory.baseRequest()
                .body(body)
                .when()
                .post("/users");
    }

    public Response createUser(Map<String,String> body){

        return RequestFactory.baseRequest()
                .body(body)
                .when()
                .post("/users");
    }

    public Response updateUser(int id, String name, String job){

        Map<String,String> body = new HashMap<>();
        body.put("name", name);
        body.put("job", job);

        return RequestFactory.baseRequest()
                .body(body)
                .when()
                .put("/users/" +id);
    }

    public Response updateUser(int id, Map<String,Object> body){
        return RequestFactory.baseRequest()
                .body(body)
                .when()
                .put("/users/" +id);
    }

    public Response patchUser(int id, Map<String,Object> updates){

        return RequestFactory.baseRequest()
                .body(updates)
                .when()
                .patch("/users/" +id);

    }

    public Response deleteUser(int id){

        return RequestFactory.baseRequest()
                .when()
                .delete("/users/" +id);

    }

    public Response registerUser(String email, String password){

        Map<String,String> body = new HashMap<>();
        body.put("email", email);
        body.put("password", password);

        return RequestFactory.baseRequest()
                .body(body)
                .post("/register");

    }

    public Response registerUser(Map<String,Object> body){

        return RequestFactory.baseRequest()
                .body(body)
                .post("/register");

    }

    public Response loginUser(String email, String password){

        Map<String,String> body = new HashMap<>();
        body.put("email", email);
        body.put("password", password);

        return RequestFactory.baseRequest()
                .body(body)
                .post("/login");

    }

    public Response loginUser(Map<String,Object> body){

        return RequestFactory.baseRequest()
                .body(body)
                .post("/login");

    }


}