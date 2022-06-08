package pages;

import java.util.Scanner;

import pattern.IStatePattern;
import pattern.StateController;

public class TransactionScreen implements IStatePattern{    

    private Scanner input = new Scanner(System.in);

    @Override
    public void show(StateController controller){
        System.out.println("\n--Transactions--");
        System.out.println("1. Withdraw");
        System.out.println("2. Fund Transfer");
        System.out.println("3. Exit");

        String userInput = input.nextLine();

        switch(userInput){
            case "1":
            break;
            case "2":
            break;
            case "3":
            break;
            default: controller.nextState(this);;
        }
    }
}
