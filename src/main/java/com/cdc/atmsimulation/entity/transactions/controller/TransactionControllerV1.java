package com.cdc.atmsimulation.entity.transactions.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cdc.atmsimulation.entity.users.domain.Account;
import com.cdc.atmsimulation.entity.users.service.AccountService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping(value = "api/v1")
public class TransactionControllerV1 {

    private static final String URLVERSION = "/api/v1";

    private final AccountService accountService;

    @PostMapping(value = "{accountNumber}/transaction")
    public String showTransactionPage(ModelMap model, @PathVariable String accountNumber) {
        Account account = accountService.getById(accountNumber);

        model.put("credit", account.getBalance());
        model.put("fullname", account.getName());
        model.put("accountNumber", account.getAccountNumber());
        model.put("urlversion", URLVERSION);

        return "transaction";
    }
}
