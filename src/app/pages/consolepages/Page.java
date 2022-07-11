package app.pages.consolepages;

import java.util.Scanner;

import app.entity.accounts.domain.Account;
import app.pattern.ServiceFactory;
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

    protected static Scanner cmdInput = new Scanner(System.in);
    protected static StateController controller;
    protected static Page.Pages nextPage = Pages.DEFAULT;
    protected static Account loggedAccount = null;
    protected static ServiceFactory services = new ServiceFactory();
    
    public Page(StateController controller){
        Page.controller = controller;
    }
}
