package com.xproj.eazybank.controllers;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CardsController {

    @GetMapping("/cards")
    public String getCardsDetails() {
        return "Here are the cards details from the DB";
    }
}
