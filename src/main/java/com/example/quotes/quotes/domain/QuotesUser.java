package com.example.quotes.quotes.domain;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.UUID;

@Data
@Document
public class QuotesUser {

    @Id
    private String _id = UUID.randomUUID().toString();

    private String userName;
    private String password;
    private Date createdDate = new Date();
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private String address;
}
