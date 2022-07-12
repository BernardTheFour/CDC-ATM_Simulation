package com.cdc.atmsimulation.entity.users.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.cdc.atmsimulation.entity.users.service.LoginService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping(value = "api/v1")
public class LoginController_V1 {

    private final LoginService loginService;

    @RequestMapping(value = { "", "/"}, method = RequestMethod.GET)
    public String showLoginPage(ModelMap model) {
        return "login";
    }

    @RequestMapping(value = { "", "/"}, method = RequestMethod.POST)
    public String showTransactionPage(ModelMap model,
            @RequestParam String fieldAccountNumber,
            @RequestParam String fieldPinNumber) {

        try {
            loginService.validateLoginInput(fieldAccountNumber, fieldPinNumber);
            return "forward:transaction?accountNumber=" + fieldAccountNumber;
        } catch (Exception e) {
            model.put("errorMsg", "</br>Invalid input: " + e.getMessage() + "</br>");
        }

        return "login";
    }
}
