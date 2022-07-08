package app.pages;

import java.util.Scanner;

import app.domains.Account;
import app.pattern.StateController;
import app.services.ServiceFactory;

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

    protected static Scanner cmdInput = new Scanner(System.in);
    protected static StateController controller;
    protected static Page.Pages nextPage = Pages.DEFAULT;
    protected static Account loggedAccount = null;
    protected static ServiceFactory services = new ServiceFactory();
    
    public Page(StateController controller){
        Page.controller = controller;
    }
}
