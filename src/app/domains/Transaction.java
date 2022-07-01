package app.domains;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Transaction {

    private static List<Transaction> transactions;

    public static enum Type {
        WITHDRAW("WITHDRAW"),
        TRANSFER("TRANSFER"),
        RECEIVE("RECEIVE");

        String value;

        Type(String value) {
            this.value = value;
        }
    }

    private String accountNumber;
    private Type transactionType;
    private String associate;
    private int amount;
    private LocalDateTime date;

    public static void set(List<Transaction> newTransaction) {
        transactions = newTransaction;
    }

    public static List<Transaction> get() {
        return transactions;
    }

    public String getAccountNumber() {
        return this.accountNumber;
    }

    public Type getTransactionType() {
        return this.transactionType;
    }

    public String getAssociate() {
        return this.associate;
    }

    public int getAmount() {
        return this.amount;
    }

    public LocalDateTime getDate() {
        return this.date;
    }

    public Transaction() {
        if (transactions == null) {
            transactions = new ArrayList<>();
        }
    }

    public Transaction(String accountNumber, Type transactionType, String associate, int amount, LocalDateTime date) {
        this.accountNumber = accountNumber;
        this.transactionType = transactionType;
        this.associate = associate;
        this.amount = amount;
        this.date = date;
    }

    @Override
    public String toString() {
        return "{" +
                " accountNumber='" + getAccountNumber() + "'" +
                ", type='" + getTransactionType() + "'" +
                ", associateAccountNumber='" + getAssociate() + "'" +
                ", amount='" + getAmount() + "'" +
                ", date='" + getDate() + "'" +
                "}";
    }
}
