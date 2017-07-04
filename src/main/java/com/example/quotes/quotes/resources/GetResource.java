package com.example.quotes.quotes.resources;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class GetResource {

    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public String get() {
        return "hello word";
    }
}
