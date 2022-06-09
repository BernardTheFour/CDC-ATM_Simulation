package pages;

import java.io.IOException;
import java.util.Random;

import pattern.IState;
import pattern.Singleton;
import pattern.StateController;

public class TransferScreen extends Page implements IState {

    @Override
    public void init(StateController controller) {
        super.controller = controller;
        super.nextPage = Pages.DEFAULT;
    }

    @Override
    public void logic() {

        System.out.println("\n--Transfer--");

        String destination = getDestination();
        if (destination == null) {
            super.nextPage = Pages.TRANSACTION;
            return;
        }

        int amount = getAmount();
        if (amount == -1) {
            super.nextPage = Pages.TRANSACTION;
            return;
        }

        int referenceNumber = getReferenceNumber();
        if (referenceNumber == -1) {
            super.nextPage = Pages.TRANSACTION;
            return;
        }

        boolean confirmation = transferConfirmation(destination, amount, referenceNumber);
        if (!confirmation) {
            super.nextPage = Pages.TRANSACTION;
            return;
        }
    }

    @Override
    public void navigate() {
        switch (super.nextPage) {
            case TRANSACTION:
                controller.nextState(Singleton.TransactionScreen());
            default:
                controller.nextState(controller.getCurrentState());
        }
    }

    private String getDestination() {
        try {
            System.out.println("\nPlease enter destination account and");
            System.out.println("press enter to continue or");
            System.out.println("type (Q/q)  to go back to Transaction");

            String answer = super.input.nextLine();
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
        try {
            System.out.println("\nPlease enter transfer amount and");
            System.out.println("press enter to continue or");
            System.out.println("type (Q/q)  to go back to Transaction");

            String answer = super.input.nextLine();
            if (answer.matches("[qQ]")) {
                return -1;
            }

            if (!answer.matches("[0-9]+")) {
                throw new IOException("Invalid amount: please input only number");
            }

            int amount = Integer.parseInt(answer);
            if (amount <= 0) {
                throw new IOException("Invalid amount: cannot less than or equal to 0");
            }
            return amount;
        } catch (Exception e) {
            if (e instanceof IOException) {
                System.out.println(e.getMessage());
                return getAmount();
            }

            e.printStackTrace();
        }
        return 0;
    }

    private int getReferenceNumber() {
        int randomInt = (int) Math.random() * 1000000;

        System.out.println("\nReference Number: " + randomInt);
        System.out.println("press enter to continue or");
        System.out.println("type (Q/q)  to go back to Transaction");

        String answer = super.input.nextLine();

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

        String answer = super.input.nextLine();

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
