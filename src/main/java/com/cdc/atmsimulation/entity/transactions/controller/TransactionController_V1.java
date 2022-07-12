package com.cdc.atmsimulation.entity.transactions.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping(value = "api/v1")
public class TransactionController_V1 {

    @RequestMapping(value = { "/transaction" }, method = RequestMethod.POST)
    public String showTransactionPage(ModelMap model, @RequestParam String accountNumber) {
        model.put("name", accountNumber);
        return "transaction";
    }
}
