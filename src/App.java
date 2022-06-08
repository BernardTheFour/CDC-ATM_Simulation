import java.util.HashSet;
import java.util.Set;

import domains.Account;
import pattern.Singleton;
import pattern.StateController;

public class App {

    private static StateController screenNavigator;

    public static void main(String[] args) throws Exception {
        Singleton.init();

        Singleton.setAccounts(initDummyData());

        screenNavigator = new StateController(
                Singleton.WelcomeScreen());

        screenNavigator.run();
    }

    private static Set<Account> initDummyData() {
        Set<Account> account = new HashSet<Account>();

        account.add(new Account("112233", "012108", "John Doe", 100));
        account.add(new Account("112244", "932012", "Jane Doe", 30));

        return account;
    }
}
