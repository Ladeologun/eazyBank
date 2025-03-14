package com.xproj.eazybank.controllers;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BalanceController {

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @GetMapping("/balance")
    public String getBalanceDetails() {
        return "Here are the balance details from the DB";
    }
}
