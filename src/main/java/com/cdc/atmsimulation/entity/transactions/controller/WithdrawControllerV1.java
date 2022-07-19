package com.cdc.atmsimulation.entity.transactions.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.cdc.atmsimulation.entity.users.domain.Account;
import com.cdc.atmsimulation.entity.users.service.AccountService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping(value = "api/v1")
public class WithdrawControllerV1 {

    private static final String URLVERSION = "/api/v1";

    private final AccountService accountService;

    @GetMapping(value = "{accountNumber}/withdraw")
    public String showWithdrawPage(ModelMap model,
            @PathVariable String accountNumber) {
        Account account = accountService.getById(accountNumber);

        model.put("credit", account.getBalance());
        model.put("accountNumber", account.getAccountNumber());
        model.put("urlversion", URLVERSION);

        return "withdraw";
    }

    @PostMapping(value = "{accountNumber}/withdraw")
    public String withdrawCash(ModelMap model,
            @PathVariable String accountNumber,
            @RequestParam String amount) {

        Account account = accountService.getById(accountNumber);
        model.put("credit", account.getBalance());
        model.put("accountNumber", account.getAccountNumber());
        model.put("urlversion", URLVERSION);

        switch (amount) {
            case "10":
                return "forward:withdraw-summary?amount=10";
            case "50":
                return "forward:withdraw-summary?amount=50";
            case "100":
                return "forward:withdraw-summary?amount=100";
            case "other":
                return "otherwithdraw";
            default:
                return "forward:" + URLVERSION + "/" + accountNumber + "/transaction";
        }
    }

    @PostMapping(value = "{accountNumber}/withdraw-summary")
    public String withdrawSummary(ModelMap model,
            @PathVariable String accountNumber,
            @RequestParam int amount) {
        System.out.println("PRINT: " + accountNumber);
        System.out.println("PRINT: " + amount);
        return null;
    }
}
