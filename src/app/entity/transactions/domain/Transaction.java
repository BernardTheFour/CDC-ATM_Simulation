package app.entity.transactions.domain;

import java.time.LocalDateTime;

public class Transaction {

    public static enum Type {
        WITHDRAW("WITHDRAW"),
        TRANSFER("TRANSFER"),
        RECEIVES("RECEIVES");

        String value;

        Type(String value) {
            this.value = value;
        }
    }

    private String account;
    private Type transactionType;
    private String associate;
    private int amount;
    private LocalDateTime date;

    public String getAccount() {
        return this.account;
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
    }

    public Transaction(String accountNumber, Type transactionType, String associate, int amount, LocalDateTime date) {
        this.account = accountNumber;
        this.transactionType = transactionType;
        this.associate = associate;
        this.amount = amount;
        this.date = date;
    }

    @Override
    public String toString() {
        return "{" +
                " accountNumber='" + getAccount() + "'" +
                ", type='" + getTransactionType() + "'" +
                ", associateAccountNumber='" + getAssociate() + "'" +
                ", amount='" + getAmount() + "'" +
                ", date='" + getDate() + "'" +
                "}";
    }
}
