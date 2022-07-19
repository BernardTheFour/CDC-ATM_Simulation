package com.cdc.atmsimulation.pattern.singletons;

import com.cdc.atmsimulation.pages.consolepages.OtherWithdrawScreen;
import com.cdc.atmsimulation.pages.consolepages.SummaryScreen;
import com.cdc.atmsimulation.pages.consolepages.TransactionHistoryScreen;
import com.cdc.atmsimulation.pages.consolepages.TransactionScreen;
import com.cdc.atmsimulation.pages.consolepages.TransferScreen;
import com.cdc.atmsimulation.pages.consolepages.TransferSummaryScreen;
import com.cdc.atmsimulation.pages.consolepages.WelcomeScreen;
import com.cdc.atmsimulation.pages.consolepages.WithdrawScreen;
import com.cdc.atmsimulation.pattern.StateController;

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
