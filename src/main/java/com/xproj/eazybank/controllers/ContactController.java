package com.xproj.eazybank.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ContactController {

    @GetMapping("/contact")
    public String saveContactInquiryDetails() {
        return "Here are the contact details from the DB";
    }
}
