package com.cdc.atmsimulation.entity.transactions.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cdc.atmsimulation.entity.transactions.domain.Transaction;
import com.cdc.atmsimulation.entity.transactions.service.TransactionService;
import com.cdc.atmsimulation.entity.users.service.AccountService;
import com.cdc.atmsimulation.pattern.singletons.SingletonUtils;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping(value = "api/v1")
public class TransactionHistoryControllerV1 {
    private static final String URLVERSION = "/api/v1";

    private final AccountService accountService;
    private final TransactionService transactionService;

    @GetMapping(value = "{accountNumber}/transaction-history")
    public String showHistoryPage(ModelMap model,
            @PathVariable(value = "accountNumber") String accountNumber) {
        String table = "";

        List<Transaction> transactions = transactionService.getAllById(accountNumber);

        for (Transaction transaction : transactions) {
            table += "<tr>";
            table += "<td>" + transaction.getAmount() + "</td>";
            table += "<td>" + transaction.getTransactionType()
                    .toString()
                    .toLowerCase() + "</td>";

            String transferName = "-";

            if (!transaction.getAssociate().equals("null")) {
                transferName = "(" + transaction.getAccount() + ") ";
                transferName += accountService.getById(transaction.getAssociate()).getName();
            }

            table += "<td>" + transferName + "</td>";
            table += "<td>" + transaction.getDate().format(SingletonUtils.getDateTimeFormat()) + "</td>";
            table += "</tr>";
        }

        model.put("table", table);
        model.put("urlversion", URLVERSION);
        return "history";
    }
}
