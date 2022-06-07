import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

import domains.Account;
import pages.WelcomePage;

public class App {

    private static Scanner input = new Scanner(System.in);
    private static WelcomePage welcomePage = new WelcomePage();

    public static void main(String[] args) throws Exception {
        boolean exit = false;

        Set<Account> accounts = initDummyData();
        Account loggedAccount = new Account();

        while (!exit) {
            loggedAccount = welcomePage.show(accounts);
            exit = true;
        }

        System.out.println(loggedAccount.toString());
    }

    private static Set<Account> initDummyData() {
        Set<Account> account = new HashSet<Account>();

        account.add(new Account("112233", "012108", "John Doe", 100));
        account.add(new Account("112244", "932012", "Jane Doe", 30));

        return account;
    }

    private static void print(Set<Account> accounts){
        accounts.forEach(i -> System.out.println(i));
    }
}
