package com.example.quotes.quotes.resources;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class TestResource {

    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public ResponseEntity<String> get() {
        return new ResponseEntity<>("hello word", HttpStatus.OK);
    }
}
