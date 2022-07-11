package app.pattern.singletons;

import app.pages.consolepages.OtherWithdrawScreen;
import app.pages.consolepages.SummaryScreen;
import app.pages.consolepages.TransactionHistoryScreen;
import app.pages.consolepages.TransactionScreen;
import app.pages.consolepages.TransferScreen;
import app.pages.consolepages.TransferSummaryScreen;
import app.pages.consolepages.WelcomeScreen;
import app.pages.consolepages.WithdrawScreen;
import app.pattern.StateController;

public class SingletonScreen {

    private static SingletonScreen instance;

    private WelcomeScreen welcomeScreen;
    private TransactionScreen transactionScreen;
    private SummaryScreen summaryScreen;
    private TransferSummaryScreen transferSummaryScreen;
    private WithdrawScreen withdrawScreen;
    private OtherWithdrawScreen otherWithdrawScreen;
    private TransferScreen transferScreen;
    private TransactionHistoryScreen transactionHistoryScreen;

    private String dateTimeFormat = "yyy-MM-dd hh:mm a";

    public static void init(StateController controller) {
        if (instance == null) {
            instance = new SingletonScreen();
            instance.welcomeScreen = new WelcomeScreen(controller);
            instance.transactionScreen = new TransactionScreen(controller);
            instance.summaryScreen = new SummaryScreen(controller);
            instance.transferSummaryScreen = new TransferSummaryScreen(controller);
            instance.withdrawScreen = new WithdrawScreen(controller);
            instance.otherWithdrawScreen = new OtherWithdrawScreen(controller);
            instance.transferScreen = new TransferScreen(controller);
            instance.transactionHistoryScreen = new TransactionHistoryScreen(controller);
        }
    }

    public static String getDateFormat() {
        return instance.dateTimeFormat;
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

    public static TransactionHistoryScreen TransactionHistoryScreen(){
        return instance.transactionHistoryScreen;
    }
}
