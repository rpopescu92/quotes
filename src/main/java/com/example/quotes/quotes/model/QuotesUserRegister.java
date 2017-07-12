package com.example.quotes.quotes.model;

import lombok.Data;

@Data
public class QuotesUserRegister {
    private String userName;
    private String password;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private String address;
}
