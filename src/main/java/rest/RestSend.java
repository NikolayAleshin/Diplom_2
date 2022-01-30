package rest;

import io.qameta.allure.Step;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class RestSend extends RestAssuredClient {

    @Step
    public <T> Response postRequestAndReturnResponse(T requestBody, RestUrl url) {

        return given()
                .spec(getBaseSpec())
                .and()
                .body(requestBody)
                .when()
                .post(url.getId());
    }

    @Step
    public <T> Response patchRequestAndReturnResponse(T requestBody, RestUrl url, String token) {

        return given()
                .spec(getBaseSpec())
                .header("authorization", token)
                .and()
                .body(requestBody)
                .when()
                .patch(url.getId());
    }

    @Step
    public Response getRequestAndReturnResponse(RestUrl url, String token) {

        return given()
                .spec(getBaseSpec())
                .header("authorization", token)
                .when()
                .get(url.getId());
    }

    @Step
    public <T> Response deleteCourierByIdAndReturnResponse(T requestBody, int id, RestUrl url) {

        return given()
                .spec(getBaseSpec())
                .and()
                .body(requestBody)
                .when()
                .delete(url.getId() + id);
    }
}
