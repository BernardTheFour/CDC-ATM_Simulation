import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import domains.Account;
import pattern.SingletonData;
import pattern.SingletonScreen;
import pattern.StateController;

public class App {

    private static StateController screenNavigator;

    public static void main(String[] args) throws Exception {

        
        SingletonData.init();
        SingletonScreen.init();

        SingletonData.setAccounts(initDummyData());

        screenNavigator = new StateController(
                SingletonScreen.WelcomeScreen());

        screenNavigator.run();
    }

    private static Set<Account> initDummyData() {
        Set<Account> account = new HashSet<Account>();

        account.add(new Account("112233", "012108", "John Doe", 100));
        account.add(new Account("112244", "932012", "Jane Doe", 30));

        return account;
    }
}
