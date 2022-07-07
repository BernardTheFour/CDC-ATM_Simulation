package app.domains;

public class Account {

    private String accountNumber;
    private String pin;
    private String name;
    private int balance;

    public Account(){}

    public Account(String accountNumber, String pin, String name, int balance) {
        this.name = name;
        this.pin = pin;
        this.balance = balance;
        this.accountNumber = accountNumber;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPin() {
        return this.pin;
    }

    public void setPin(String pin) {
        this.pin = pin;
    }

    public int getBalance() {
        return this.balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    public String getAccountNumber() {
        return this.accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    @Override
    public String toString() {
        return "{" +
            " accountNumber='" + getAccountNumber() + "'" +
            ", pin='" + getPin() + "'" +
            ", name='" + getName() + "'" +
            ", balance='" + getBalance() + "'" +
            "}";
    }
}
