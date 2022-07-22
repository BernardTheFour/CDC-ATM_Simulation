package com.cdc.atmsimulation.entity.transactions.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cdc.atmsimulation.entity.transactions.service.TransactionService;
import com.cdc.atmsimulation.entity.users.domain.Account;
import com.cdc.atmsimulation.entity.users.service.AccountService;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
@RequestMapping(value = "api/v1")
public class TransferControllerV1 {

    private static final String URLVERSION = "/api/v1";

    private final AccountService accountService;
    private final TransactionService transactionService;

    @GetMapping(value = "{accountNumber}/transfer")
    public String showTransferForm(ModelMap model,
            @PathVariable(value = "accountNumber") String accountNumber) {

        model.put("urlversion", URLVERSION);
        model.put("accountNumber", accountNumber);

        return "transfer-form";
    }

    @PostMapping(value = "{accountNumber}/transfer")
    public String processFundTransfer(ModelMap model,
            @PathVariable(value = "accountNumber") String accountNumber,
            @RequestParam(value = "receiverNumber") String receiverNumber,
            @RequestParam(value = "amount") int transferAmount) {

        return "forward";
    }

    @GetMapping(value = "{accountNumber}/show-transfer-confirm")
    public String showConfirmPage(
            @PathVariable(value = "accountNumber") String accountNumber,
            @RequestParam(value = "receiverNumber") String receiverNumber,
            @RequestParam(value = "amount") int transferAmount,
            @RequestParam(value = "reference") int referenceNumber) {

        return "transfer-confirmation";
    }

    @PostMapping(value = "{accountNumber}/transfer-confirm")
    public String confirmTrasfer(
            @PathVariable(value = "accountNumber") String accountNumber,
            @RequestParam(value = "receiverNumber") String receiverNumber,
            @RequestParam(value = "amount") int transferAmount,
            @RequestParam(value = "reference") int referenceNumber) {

        return "transfer-confirmation";
    }

    @GetMapping(value = "{accountNumber}/transfer-summary")
    public String transferSummary(
            @PathVariable(value = "accountNumber") String accountNumber,
            @RequestParam(value = "receiverNumber") String receiverNumber,
            @RequestParam(value = "amount") int transferAmount,
            @RequestParam(value = "reference") int referenceNumber) {

        return "summary-transfer";
    }

}
