import java.util.HashSet;
import java.util.Set;

import domains.Account;
import pattern.Singleton;
import pattern.StateController;

public class App {

    private static StateController screenNavigator;
    private static Singleton singleton = new Singleton();

    public static void main(String[] args) throws Exception {
        Singleton.init(singleton);

        Singleton.setAccounts(initDummyData());

        screenNavigator = new StateController(
                Singleton.getWelcomeScreen());

        screenNavigator.showCurrent();
    }

    private static Set<Account> initDummyData() {
        Set<Account> account = new HashSet<Account>();

        account.add(new Account("112233", "012108", "John Doe", 100));
        account.add(new Account("112244", "932012", "Jane Doe", 30));

        return account;
    }
}