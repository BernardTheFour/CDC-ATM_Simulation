package com.cdc.atmsimulation.entity.users.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping(value = "api/v1")
public class LoginController_V1 {
    

    @RequestMapping(value = {"", "/", "/login" }, method = RequestMethod.GET)
    public String showLoginPage(ModelMap model) {
        return "login";
    }

    @RequestMapping(value = {"", "/", "/login" }, method = RequestMethod.POST)
    public String showTransactionPage(ModelMap mode,
            @RequestParam String accountNumber,
            @RequestParam String pin) {

        return "transaction";
    }
}
