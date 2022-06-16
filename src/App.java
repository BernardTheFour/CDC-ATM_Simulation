import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashSet;
import java.util.Locale;
import java.util.Scanner;
import java.util.Set;

import dao.AccountDao;
import domains.Account;
import pattern.SingletonData;
import pattern.SingletonPath;
import pattern.SingletonScreen;
import pattern.SingletonUtils;
import pattern.StateController;
import util.DataAccessObject;
import util.FileManagement;

public class App {

    private static StateController screenNavigator;

    public static void main(String[] args) {

        CheckPath(args);

        Initialization();

        try {
            FileManagement.extractPath(args[0]);
        } catch (Exception e) {
            if (e instanceof FileNotFoundException) {
                System.out.println(e.getMessage());
            } else {
                e.printStackTrace();
                System.exit(0);
            }
        }

        try {
            AccountDao daoAccount = new AccountDao(SingletonPath.getAccount());
            System.out.println(daoAccount.getAccount(5));
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        // try {
        // Scanner scanner = new Scanner(SingletonPath.getAccount());

        // scanner.useDelimiter("\r\n"); // windows newline delimiter (EOL)

        // while (scanner.hasNext()) {
        // String[] data = scanner.next().split(";");

        // for (int i = 0; i < data.length; i++) {
        // System.out.print(data[i] + " | ");
        // }
        // System.out.println("--");
        // }
        // scanner.close();
        // } catch (Exception e) {
        // e.printStackTrace();
        // }

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

    private static void CheckPath(String[] path) {
        File file = path.length > 0 ? new File(path[0]) : null;

        if (file == null) {
            System.out.println("\nPlease input directory path argument\n");
            System.exit(0);
        }

        if (!file.isDirectory()) {
            System.out.println("Pathfile should be folder directory, not file.");
            System.exit(0);
        }
    }

    private static void Initialization() {
        System.out.println("\nProgram starting...");
        SingletonPath.init();
        SingletonData.init();
        SingletonScreen.init();
        SingletonUtils.init();
    }
}
