package app.pages;

import java.util.Scanner;

import app.pattern.StateController;

public class Page {
    public static enum Pages {
        DEFAULT,
        SUMMARY,
        TRANSACTION,
        OTHER_WITHDRAW,
        TRANSFER,
        TRANSFER_SUMMARY,
        WELCOME,
        WITHDRAW,
        TRANSACTION_HISTORY
    }

    protected Scanner input = new Scanner(System.in);
    protected StateController controller;
    protected Page.Pages nextPage = Pages.DEFAULT;
}
