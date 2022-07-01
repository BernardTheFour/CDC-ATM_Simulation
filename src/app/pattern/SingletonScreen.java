package app.pattern;

import app.pages.OtherWithdrawScreen;
import app.pages.SummaryScreen;
import app.pages.TransactionHistoryScreen;
import app.pages.TransactionScreen;
import app.pages.TransferScreen;
import app.pages.TransferSummaryScreen;
import app.pages.WelcomeScreen;
import app.pages.WithdrawScreen;

public class SingletonScreen {

    private static SingletonScreen instance;

    private WelcomeScreen welcomeScreen = new WelcomeScreen();
    private TransactionScreen transactionScreen = new TransactionScreen();
    private SummaryScreen summaryScreen = new SummaryScreen();
    private TransferSummaryScreen transferSummaryScreen = new TransferSummaryScreen();
    private WithdrawScreen withdrawScreen = new WithdrawScreen();
    private OtherWithdrawScreen otherWithdrawScreen = new OtherWithdrawScreen();
    private TransferScreen transferScreen = new TransferScreen();
    private TransactionHistoryScreen transactionHistoryScreen = new TransactionHistoryScreen();

    private String dateTimeFormat = "yyy-MM-dd hh:mm a";

    public static void init() {
        if (instance == null) {
            instance = new SingletonScreen();
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
