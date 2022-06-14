package domains;

import java.time.LocalDateTime;

public class Transactions {

    public static enum Type{
        WITHDRAW,
        TRANSFER
    }

    private String accountNumber;
    private Type transactionType;
    private String transferTo;
    private String amount;
    private LocalDateTime date;

    public String getAccountNumber() {
        return this.accountNumber;
    }

    public Type getTransactionType() {
        return this.transactionType;
    }

    public String getTransferTo() {
        return this.transferTo;
    }

    public String getAmount() {
        return this.amount;
    }

    public LocalDateTime getDate() {
        return this.date;
    }

    public Transactions(String accountNumber, Type transactionType, String transferTo, String amount, LocalDateTime date) {
        this.accountNumber = accountNumber;
        this.transactionType = transactionType;
        this.transferTo = transferTo;
        this.amount = amount;
        this.date = date;
    }

    public Transactions(String accountNumber, Type transactionType, String amount, LocalDateTime date) {
        this.accountNumber = accountNumber;
        this.transactionType = transactionType;
        this.amount = amount;
        this.date = date;
    }        
}
