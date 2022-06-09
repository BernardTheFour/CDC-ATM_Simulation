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
        super.controller = controller;
    }

    @Override
    public void logic() {
        System.out.println("\n\n--Automated Teller Machine--");

        try {
            System.out.print("Account number: ");
            String accountNumber = checkAccountNumber(super.input.nextLine());

            System.out.print("PIN number: ");
            String pinNumber = checkPinNumber(super.input.nextLine());

            Optional<Account> checkUser = Singleton.getAccounts().stream()
                    .filter(i -> accountNumber.equals(i.getAccountNumber()))
                    .filter(i -> pinNumber.equals(i.getPin()))
                    .findAny();

            if (checkUser.isEmpty()) {
                System.out.println("\nWrong PIN or account number");
                return;
            }

            Singleton.setLoggedUser(checkUser.get());

            System.out.printf("%nWelcome %s",
                    Singleton.getLoggedUser().getName());

            super.nextPage = Pages.TRANSACTION;

        } catch (Exception e) {

            if (e instanceof IOException) {
                System.out.println(e.getMessage());
                return;
            }

            e.printStackTrace();
        }
    }

    @Override
    public void navigate() {
        switch (super.nextPage) {
            case TRANSACTION:
                controller.nextState(Singleton.TransactionScreen());
                break;
            default:
                controller.nextState(controller.getCurrentState());
                break;
        }
        super.nextPage = Pages.DEFAULT;
    }

    private String checkAccountNumber(String accountNumber) throws IOException {
        if (accountNumber.length() != 6) {
            throw new IOException("Account number should have 6 digits length");
        }
        if (!accountNumber.matches("[0-9]+")) {
            throw new IOException("Account number should contains only numbers");
        }
        return accountNumber;
    }

    private String checkPinNumber(String pinNumber) throws IOException {
        if (pinNumber.length() != 6) {
            throw new IOException("PIN should have 6 digits length");
        }
        if (!pinNumber.matches("[0-9]+")) {
            throw new IOException("PIN should only contains numbers");
        }
        return pinNumber;
    }
}
