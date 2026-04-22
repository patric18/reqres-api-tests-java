package api.factory;

import api.config.Config;
import io.restassured.RestAssured;
import io.restassured.specification.RequestSpecification;

public class RequestFactory {

    public static RequestSpecification baseRequest(){
        return RestAssured
                .given()
                .baseUri(Config.baseUrl())
                .header("x-api-key", Config.apiKey())
                .header("X-Reqres-Env", Config.env())
                .contentType("application/json");
    }
}
