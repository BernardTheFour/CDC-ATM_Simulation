package pages;

import java.util.Scanner;

import pattern.StateController;

public class Page{
    public enum Pages {
        SUMMARY,
        TRANSACTION,
        OTHER_WITHDRAW,
        TRANSFER,
        TRANSFER_SUMMARY,
        WELCOME,
        WITHDRAW
    }

    protected Scanner input = new Scanner(System.in);
    protected StateController controller;
    protected Page.Pages nextPage;
}

