package pattern;

import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.util.Set;

import domains.Account;
import pages.OtherWithdrawScreen;
import pages.SummaryScreen;
import pages.TransactionScreen;
import pages.TransferScreen;
import pages.TransferSummaryScreen;
import pages.WelcomeScreen;
import pages.WithdrawScreen;

public class Singleton {

    public static Singleton instance;

    private Account loggedUser = new Account();;
    private Set<Account> accounts = new HashSet<>();

    private WelcomeScreen welcomeScreen = new WelcomeScreen();
    private TransactionScreen transactionScreen = new TransactionScreen();
    private SummaryScreen summaryScreen = new SummaryScreen();
    private TransferSummaryScreen transferSummaryScreen = new TransferSummaryScreen();
    private WithdrawScreen withdrawScreen = new WithdrawScreen();
    private OtherWithdrawScreen otherWithdrawScreen = new OtherWithdrawScreen();

    private TransferScreen transferScreen = new TransferScreen();

    private String dateTimeFormat = "yyy-MM-dd hh:mm a";

    public static void init() throws Exception {
        if (instance == null) {
            instance = new Singleton();
        } else {
            throw new Exception("Only one singleton at a time!");
        }
    }

    public static String getDateFormat() {
        return instance.dateTimeFormat;
    }

    public static Account getLoggedUser() {
        return instance.loggedUser;
    }

    public static void setLoggedUser(Account loggedUser) {
        instance.loggedUser = loggedUser;
    }

    public static Set<Account> getAccounts() {
        return instance.accounts;
    }

    public static void setAccounts(Set<Account> accounts) {
        instance.accounts = accounts;
    }

    public static WelcomeScreen WelcomeScreen() {
        return instance.welcomeScreen;
    }

    public static TransactionScreen TransactionScreen() {
        return instance.transactionScreen;
    }

    public static SummaryScreen SummaryScreen() {
        return instance.summaryScreen;
    }

    public static TransferScreen TransferScreen() {
        return instance.transferScreen;
    }

    public static TransferSummaryScreen TransferSummaryScreen() {
        return instance.transferSummaryScreen;
    }

    public static WithdrawScreen WithdrawScreen() {
        return instance.withdrawScreen;
    }

    public static OtherWithdrawScreen OtherWithdrawScreen() {
        return instance.otherWithdrawScreen;
    }
}
