package com.restassured.responses.pojo;

import com.restassured.model.Book;

import java.util.List;

public class UserAccountResponse {
    public String userId;
    public String username;
    public List<Book> books;
}
