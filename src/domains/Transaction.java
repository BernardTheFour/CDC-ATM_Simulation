package domains;

import java.time.LocalDateTime;

public class Transaction {

    public static enum Type{
        WITHDRAW("WITHDRAW"),
        TRANSFER("TRANSFER"),
        RECEIVE("RECEIVE");

        String value;

        Type(String value){
            this.value = value;
        }
    }

    private String accountNumber;
    private Type transactionType;
    private String Associate;
    private int amount;
    private LocalDateTime date;

    public String getAccountNumber() {
        return this.accountNumber;
    }

    public Type getTransactionType() {
        return this.transactionType;
    }

    public String getAssociate() {
        return this.Associate;
    }

    public int getAmount() {
        return this.amount;
    }

    public LocalDateTime getDate() {
        return this.date;
    }

    public Transaction(String accountNumber, Type transactionType, String transferTo, int amount, LocalDateTime date) {
        this.accountNumber = accountNumber;
        this.transactionType = transactionType;
        this.Associate = transferTo;
        this.amount = amount;
        this.date = date;
    }     
}
