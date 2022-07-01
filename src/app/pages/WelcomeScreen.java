package app.pages;

import java.io.IOException;
import java.util.Optional;

import app.domains.Account;
import app.pattern.IState;
import app.pattern.SingletonData;
import app.pattern.SingletonScreen;
import app.pattern.StateController;

public class WelcomeScreen extends Page implements IState {

    @Override
    public void init(StateController controller) {
        System.out.println("\n-------------------------------");
        super.controller = controller;
        super.nextPage = Pages.DEFAULT;
    }

    @Override
    public void logic() {
        System.out.println("--Automated Teller Machine--");

        try {
            System.out.print("Account number: ");
            String accountNumber = checkAccountNumber(super.input.nextLine());

            System.out.print("PIN number: ");
            String pinNumber = checkPinNumber(super.input.nextLine());

            Optional<Account> checkUser = SingletonData.getAccounts().stream()
                    .filter(i -> accountNumber.equals(i.getAccountNumber()))
                    .filter(i -> pinNumber.equals(i.getPin()))
                    .findAny();

            if (checkUser.isEmpty()) {
                System.out.println("\nWrong PIN or account number");
                return;
            }

            SingletonData.setLoggedUser(checkUser.get());

            System.out.printf("%nWelcome %s",
                    SingletonData.getLoggedUser().getName());

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
                controller.nextState(SingletonScreen.TransactionScreen());
                break;
            default:
                controller.nextState(controller.getCurrentState());
                break;
        }
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
