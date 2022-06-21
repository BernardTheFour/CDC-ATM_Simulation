package domains;

import java.time.LocalDateTime;

public class Transactions {

    public static enum Type{
        WITHDRAW("WITHDRAW"),
        TRANSFER("TRANSFER");

        String value;

        Type(String value){
            this.value = value;
        }
    }

    private String accountNumber;
    private Type transactionType;
    private String transferTo;
    private int amount;
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

    public int getAmount() {
        return this.amount;
    }

    public LocalDateTime getDate() {
        return this.date;
    }

    public Transactions(String accountNumber, Type transactionType, String transferTo, int amount, LocalDateTime date) {
        this.accountNumber = accountNumber;
        this.transactionType = transactionType;
        this.transferTo = transferTo;
        this.amount = amount;
        this.date = date;
    }     
}
