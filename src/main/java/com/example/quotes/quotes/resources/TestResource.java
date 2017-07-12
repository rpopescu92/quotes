package com.example.quotes.quotes.resources;

import com.example.quotes.quotes.model.QuotesLoggedUser;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class TestResource {

    @RequestMapping(value = "/logged-user", method = RequestMethod.GET)
    public ResponseEntity<QuotesLoggedUser> get() {
        String username = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return new ResponseEntity<>(QuotesLoggedUser.builder().username(username).build(), HttpStatus.OK);
    }
}
