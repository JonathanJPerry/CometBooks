package edu.utdallas.cometbooks.ui;

import edu.utdallas.cometbooks.CometBooks;
import edu.utdallas.cometbooks.Controller;

import java.util.Scanner;

public final class MainMenuScreen implements Screen {
    public static MainMenuScreen create() {
        return new MainMenuScreen();
    }

    private MainMenuScreen() {
    }

    @Override
    public void onOpen(Controller controller) {
        System.out.println("Welcome to CometBooks!");
    }

    @Override
    public void handleInput(Scanner scanner, Controller controller, ScreenDisplay display) {
        System.out.println("Select a menu option to choose which tab to go to:");
        System.out.println("1. My Books for Sale");
        System.out.println("2. Transactions");

        String option = scanner.next();
        if (option.equals("1")) {
            display.switchScreen(MyBooksForSaleScreen.create(), controller);
        } else if (option.equals("2")) {
            display.switchScreen(TransactionsScreen.create(), controller);
        } else {
            System.out.println("Invalid input, please try again.");
        }
    }
}
