package com.example.quotes.quotes.domain;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface QuotesUserRepository extends MongoRepository<QuotesUser, String> {

    Optional<QuotesUser> findByUserName(String userName);

}
