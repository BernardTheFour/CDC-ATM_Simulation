package pages;

import java.io.IOException;
import java.util.Optional;

import domains.Account;
import pattern.IState;
import pattern.Singleton;
import pattern.StateController;

public class WelcomeScreen extends Page implements IState {

    @Override
    public void init(StateController controller) {
        this.controller = controller;
        nextPage = Pages.WELCOME;
    }

    @Override
    public void logic() {
        System.out.println("\n\n--Automated Teller Machine--");

        try {
            System.out.print("Account number: ");
            String accountNumber = checkAccountNumber(input.nextLine());

            System.out.print("PIN number:");
            String pinNumber = checkPinNumber(input.nextLine());

            Optional<Account> checkUser = Singleton.getAccounts().stream()
                    .filter(i -> accountNumber.equals(i.getAccountNumber()))
                    .filter(i -> pinNumber.equals(i.getPin()))
                    .findAny();

            if (checkUser.isEmpty()) {
                System.out.println("\nAccount number or PIN wrong");
                nextPage = Pages.WELCOME;
                return;
            }

            System.out.printf("%nWelcome %s", checkUser.get().getName());

            nextPage = Pages.TRANSACTION;

        } catch (Exception e) {
            nextPage = Pages.WELCOME;

            if (e instanceof IOException) {
                System.out.println(e.getMessage());
                return;
            }

            e.printStackTrace();
        }
    }

    @Override
    public void navigate() {
        switch (nextPage) {
            case WELCOME:
                controller.nextState(Singleton.WelcomeScreen());
                break;
            case TRANSACTION:
                controller.nextState(Singleton.TransactionScreen());
                break;
            default:
                System.out.println("Option unavailable");
                controller.nextState(Singleton.WelcomeScreen());
                break;
        }
    }

    private String checkAccountNumber(String accountNumber) throws IOException {
        if (accountNumber.length() != 6) {
            throw new IOException("Account number should have 6 digits length");
        }
        if (accountNumber.matches("[a-zA-Z]+")) {
            throw new IOException("Account number should contains only numbers");
        }
        return accountNumber;
    }

    private String checkPinNumber(String pinNumber) throws IOException {
        if (pinNumber.length() != 6) {
            throw new IOException("PIN should have 6 digits length");
        }
        if (pinNumber.matches("[a-zA-Z]+")) {
            throw new IOException("PIN should only contains numbers");
        }
        return pinNumber;
    }
}
