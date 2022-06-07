package pages;
import java.io.IOException;
import java.util.Scanner;
import java.util.Set;

import domains.Account;

public class WelcomePage {

    private Scanner input = new Scanner(System.in);

    public Account show(Set<Account> accounts) {
        System.out.println("\n\nWelcome to Automated Teller Machine");

        try {
            System.out.print("Account number: ");
            String accountNumber = checkAccountNumber(input.nextLine());

            System.out.print("PIN number:");
            String pinNumber = checkPinNumber(input.nextLine());

            return accounts.stream()
                    .filter(i -> accountNumber.equals(i.getAccountNumber()))
                    .filter(i -> pinNumber.equals(i.getPin()))
                    .findAny().get();
        } catch (Exception e) {
            if (e instanceof IOException) {
                System.out.println(e.getMessage());
            }

            return show(accounts);
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
