package com.cdc.atmsimulation.entity.users.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.cdc.atmsimulation.entity.users.service.LoginService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping(value = "api/v1/")
public class LoginControllerV1 {

    private static final String URLVERSION = "/api/v1";

    private final LoginService loginService;

    @GetMapping(value = { "", "/login" })
    public String showLoginPage(ModelMap model) {
        model.put("urlversion", URLVERSION);
        return "login";
    }

    @PostMapping(value = "/transaction")
    public String showTransactionPage(ModelMap model,
            @RequestParam String fieldAccountNumber,
            @RequestParam String fieldPinNumber) {
        try {
            loginService.validateLoginInput(fieldAccountNumber, fieldPinNumber);
            return "forward:" + fieldAccountNumber + "/transaction";

        } catch (Exception e) {
            return "redirect:login";
        }
    }
}
