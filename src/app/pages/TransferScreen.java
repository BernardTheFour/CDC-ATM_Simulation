package app.pages;

import java.io.IOException;

import app.domains.Account;
import app.pattern.IState;
import app.pattern.SingletonScreen;
import app.pattern.StateController;
import app.services.AccountService;

public class TransferScreen extends Page implements IState {

    public TransferScreen(StateController controller) {
        super(controller);
    }

    @Override
    public void init() {
        System.out.println("\n-------------------------------");
        nextPage = Pages.DEFAULT;
    }

    @Override
    public void logic() {

        System.out.println("--Transfer--");

        String destination = getDestination();
        if (destination == null) {
            nextPage = Pages.TRANSACTION;
            return;
        }

        int amount = getAmount();
        if (amount == -1) {
            nextPage = Pages.TRANSACTION;
            return;
        }

        int referenceNumber = getReferenceNumber();
        if (referenceNumber == -1) {
            nextPage = Pages.TRANSACTION;
            return;
        }

        boolean confirmation = transferConfirmation(destination, amount, referenceNumber);
        if (!confirmation) {
            nextPage = Pages.TRANSACTION;
            return;
        }

        boolean transferSuccess = processTransfer(destination, amount, referenceNumber);
        if (!transferSuccess) {
            nextPage = Pages.TRANSACTION;
            return;
        }

        nextPage = Pages.TRANSFER_SUMMARY;
    }

    @Override
    public void navigate() {
        switch (nextPage) {
            case TRANSACTION:
                controller.nextState(SingletonScreen.TransactionScreen());
                break;
            case TRANSFER_SUMMARY:
                controller.nextState(SingletonScreen.TransferSummaryScreen());
                break;
            default:
                controller.nextState(controller.getCurrentState());
        }
    }

    private String getDestination() {
        try {
            System.out.println("\nPlease enter destination account and");
            System.out.println("press enter to continue or");
            System.out.println("type (Q/q)  to go back to Transaction\n");

            String answer = cmdInput.nextLine();
            if (!answer.matches("[qQ]")) {
                return checkAccountNumber(answer);
            }
        } catch (Exception e) {
            if (e instanceof IOException) {
                System.out.println(e.getMessage());
                return getDestination();
            }

            e.printStackTrace();
        }
        return null;
    }

    private int getAmount() {
        System.out.println("\nPlease enter transfer amount and");
        System.out.println("press enter to continue or");
        System.out.println("type (Q/q)  to go back to Transaction");
        System.out.print("\n$");

        String answer = cmdInput.nextLine();
        if (answer.matches("[qQ]")) {
            return -1;
        }

        if (!answer.matches("[0-9]+")) {
            System.out.println("Invalid amount: please input only number");
            return getAmount();
        }

        int amount = Integer.parseInt(answer);
        if (amount <= 0) {
            System.out.println("Invalid amount: cannot less than or equal to 0");
            return getAmount();
        }
        return amount;
    }

    private int getReferenceNumber() {
        int randomInt = (int) (Math.random() * 1000000);

        System.out.println("\nReference Number: " + randomInt);
        System.out.println("press enter to continue or");
        System.out.println("type (Q/q)  to go back to Transaction\n");

        String answer = cmdInput.nextLine();

        if (answer.matches("[qQ]")) {
            return -1;
        } else if (answer.isEmpty() || answer.isBlank()) {
            return randomInt;
        } else {
            System.out.println("Invalid input: please don't do unnecessary");
            return getReferenceNumber();
        }
    }

    private boolean transferConfirmation(String destination, int amount, int referenceNumber) {
        System.out.println("\nTransfer Confirmation");
        System.out.println("Destination Account: " + destination);
        System.out.println("Transfer Amount: $" + amount);
        System.out.println("Reference Number: " + referenceNumber);

        System.out.println("\n1. Confirm transfer");
        System.out.println("2. Cancel transfer");
        System.out.print("\n\nChoose option: ");

        String answer = cmdInput.nextLine();

        switch (answer) {
            case "1":
                return true;
            case "2":
                return false;
            default:
                System.out.println("Invalid option");
                return transferConfirmation(destination, amount, referenceNumber);
        }
    }

    private boolean processTransfer(String destination, int amount, int referenceNumber) {
        Account destinationAccount = AccountService.getById(destination);

        System.out.println(destinationAccount);

        if (destinationAccount == null) {
            System.out.println("Invalid Account: destination account not found");
            return false;
        }

        if (amount > 1000) {
            System.out.println("Invalid Amount: maximal transfer is $1000");
            return false;
        }

        if (amount < 1) {
            System.out.println("Invalid Amount: minimal transfer is $1");
            return false;
        }

        if (amount > loggedAccount.getBalance()) {
            System.out.println("Insufficient Balance: cannot transfer $" + amount);
            return false;
        }

        SingletonScreen.TransferSummaryScreen().setInfo(destinationAccount, amount, referenceNumber);

        return true;
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
}
