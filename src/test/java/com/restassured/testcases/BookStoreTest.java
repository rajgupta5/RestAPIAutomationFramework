package com.restassured.testcases;

import com.restassured.model.Book;
import com.restassured.responses.pojo.TokenResponse;
import com.restassured.responses.pojo.UserAccountResponse;
import com.restassured.responses.pojo.BooksResponse;
import com.restassured.requests.pojo.AddBooksRequest;
import com.restassured.requests.pojo.AuthorizationRequest;
import com.restassured.model.ISBN;
import com.restassured.requests.pojo.RemoveBookRequest;
import com.restassured.services.IRestResponse;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Hashtable;

public class BookStoreTest extends BaseTest {

    // https://bookstore.toolsqa.com/swagger/#/Account/AccountV1AuthorizedPost


    private static Response response;

    @Test (priority = 1)
    public void generateToken(Hashtable<String , String> data) {
        AuthorizationRequest authRequest = new AuthorizationRequest(getContext("username").toString(),getContext("password").toString());
        IRestResponse<TokenResponse> tokenResponse = getEndPoints().authenticateUser(authRequest);
        writeRequestAndResponseInReport(writer.toString(), tokenResponse.getResponse().prettyPrint());
    }

    @Test(priority = 2)
    public void getBooks(Hashtable<String , String> data) {
        IRestResponse<BooksResponse> booksResponse = getEndPoints().getBooks();
        Book book = booksResponse.getBody().books.get(0);
        setContext("book", book);
        System.out.println(book.isbn);
        writeRequestAndResponseInReport(writer.toString(), booksResponse.getResponse().prettyPrint());

    }

    @Test(priority = 3)
    public void addBook(Hashtable<String , String> data) {
        ISBN isbn = new ISBN( ((Book) getContext("book")).isbn);
        String userId = (String) getContext("userId");
        AddBooksRequest addBooksRequest = new AddBooksRequest(userId, isbn);
        IRestResponse<UserAccountResponse> userAccountResponse = getEndPoints().addBook(addBooksRequest);
        setContext("userAccountResponse", userAccountResponse);
        System.out.println(userAccountResponse.getStatusCode());
        System.out.println(userAccountResponse.getBody());
        Assert.assertEquals(201, userAccountResponse.getStatusCode());
        writeRequestAndResponseInReport(writer.toString(), userAccountResponse.getResponse().prettyPrint());

    }

    @Test(priority = 4)
    public void deleteBook(Hashtable<String , String> data) {
        Book book = (Book) getContext("book");
        String userId = (String) getContext("userId");
        RemoveBookRequest removeBookRequest = new RemoveBookRequest(userId, book.isbn);
        response = getEndPoints().removeBook(removeBookRequest);
        Assert.assertEquals(204, response.getStatusCode());
        writeRequestAndResponseInReport(writer.toString(), response.prettyPrint());

    }

    @Test(priority = 5)
    public void getUser(Hashtable<String , String> data) {
        String userId = (String) getContext("userId");
        IRestResponse<UserAccountResponse> userAccountResponse = getEndPoints().getUserAccount(userId);
        Assert.assertEquals(200, userAccountResponse.getStatusCode());
        Assert.assertEquals(0, userAccountResponse.getBody().books.size());
        writeRequestAndResponseInReport(writer.toString(), userAccountResponse.getResponse().prettyPrint());

    }
}
