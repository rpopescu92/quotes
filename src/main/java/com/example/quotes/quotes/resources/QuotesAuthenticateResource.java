package com.example.quotes.quotes.resources;

import com.example.quotes.quotes.model.QuotesLoginAccess;
import com.example.quotes.quotes.model.QuotesUserLogin;
import com.example.quotes.quotes.model.QuotesUserRegister;
import com.example.quotes.quotes.services.QuotesUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class QuotesAuthenticateResource {

    private final QuotesUserService userService;

    @Autowired
    public QuotesAuthenticateResource(QuotesUserService userService) {
        this.userService = userService;
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public ResponseEntity register(@RequestBody QuotesUserRegister register) {
        this.userService.register(register);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResponseEntity<QuotesLoginAccess> login(@RequestBody QuotesUserLogin login) {
        return new ResponseEntity<>(userService.login(login), HttpStatus.OK);
    }

}
