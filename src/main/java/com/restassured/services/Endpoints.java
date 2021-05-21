package com.restassured.services;

import com.restassured.requests.pojo.AddBooksRequest;
import com.restassured.requests.pojo.AuthorizationRequest;
import com.restassured.requests.pojo.RemoveBookRequest;
import com.restassured.responses.pojo.BooksResponse;
import com.restassured.responses.pojo.TokenResponse;
import com.restassured.responses.pojo.UserAccountResponse;
import io.restassured.RestAssured;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.apache.http.HttpStatus;

import java.io.PrintStream;

public class Endpoints {

    private final RequestSpecification request;

    public Endpoints(String baseUrl, PrintStream captor) {
        RestAssured.baseURI = baseUrl;
        request = RestAssured.given().filter(new RequestLoggingFilter(captor)).log().all();
        request.header("Content-Type", "application/json");
    }

    public IRestResponse<TokenResponse> authenticateUser(AuthorizationRequest authRequest) {
        Response response = request.body(authRequest).post(Routes.generateToken());
        if(response.getStatusCode()!= HttpStatus.SC_OK) {
            throw new RuntimeException("Authentication failed. Content of failed response: " + response.toString() + " status code: "+response.getStatusCode());
        }
        TokenResponse tokenResponse = response.as(TokenResponse.class);
        request.header("Authorization", "Bearer " + tokenResponse.token);
        return new RestResponse<>(TokenResponse.class, response);
    }

    public IRestResponse<BooksResponse> getBooks() {
        Response response = request.get(Routes.books());
        return new RestResponse<>(BooksResponse.class, response);
    }

    public IRestResponse<UserAccountResponse> addBook(AddBooksRequest addBooksRequest) {
        Response response = request.body(addBooksRequest).post(Routes.books());
        return new RestResponse(UserAccountResponse.class, response);
    }

    public Response removeBook(RemoveBookRequest removeBookRequest) {
        Response response = request.body(removeBookRequest).delete(Routes.book());
        return response;
    }

    public IRestResponse<UserAccountResponse> getUserAccount(String userId) {
        Response response = request.get(Routes.userAccount(userId));
        return new RestResponse(UserAccountResponse.class, response);
    }
}
