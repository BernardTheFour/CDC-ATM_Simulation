package com.cdc.atmsimulation.entity.transactions.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.cdc.atmsimulation.entity.users.domain.Account;
import com.cdc.atmsimulation.entity.users.repository.FileRepoAccount;
import com.cdc.atmsimulation.entity.users.service.AccountService;
import com.cdc.atmsimulation.pattern.singletons.SingletonFile;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping(value = "api/v1")
public class TransactionController_V1 {

    private final AccountService accountService;

    @RequestMapping(value = { "/transaction-show" }, method = RequestMethod.POST)
    public String showTransactionPage(ModelMap model, @RequestParam String accountNumber) {
        Account account = accountService.getById(accountNumber);

        model.put("credit", account.getBalance());
        model.put("fullname", account.getName());
        return "transaction";
    }
}
