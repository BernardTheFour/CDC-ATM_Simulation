package app.pages;

import java.io.IOException;
import java.util.Optional;

import app.domains.Account;
import app.pattern.IState;
import app.pattern.SingletonScreen;
import app.pattern.StateController;
import app.services.AccountService;

public class WelcomeScreen extends Page implements IState {

    public WelcomeScreen(StateController controller) {
        super(controller);
    }

    @Override
    public void init() {
        System.out.println("\n-------------------------------");
        nextPage = Pages.DEFAULT;
        loggedAccount = null;

        Account.setData(AccountService.getAll());
    }

    @Override
    public void logic() {
        System.out.println("--Automated Teller Machine--");

        try {
            System.out.print("Account number: ");
            String accountNumber = checkAccountNumber(cmdInput.nextLine());

            System.out.print("PIN number: ");
            String pinNumber = checkPinNumber(cmdInput.nextLine());

            Optional<Account> checkUser = Account.getData().stream()
                    .filter(i -> accountNumber.equals(i.getAccountNumber()))
                    .filter(i -> pinNumber.equals(i.getPin()))
                    .findAny();

            if (checkUser.isEmpty()) {
                System.out.println("\nWrong PIN or account number");
                return;
            }

            loggedAccount = checkUser.get();

            System.out.printf("%nWelcome %s",
                    loggedAccount.getName());

            nextPage = Pages.TRANSACTION;

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
        switch (nextPage) {
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
