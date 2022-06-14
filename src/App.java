import java.io.File;
import java.util.HashSet;
import java.util.Set;

import domains.Account;
import pattern.SingletonData;
import pattern.SingletonPath;
import pattern.SingletonScreen;
import pattern.StateController;
import util.FileManagement;

public class App {

    private static StateController screenNavigator;

    public static void main(String[] args) {

        Initialization();

        FileManagement.extractPath(args[0]);

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

    private static void Initialization() {
        try {
            SingletonPath.init();
            SingletonData.init();
            SingletonScreen.init();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
