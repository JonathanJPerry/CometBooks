package edu.utdallas.cometbooks.frontend.screens;

import edu.utdallas.cometbooks.backend.Controller;

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

        switch (scanner.next()) {
            case "1":
                display.switchScreen(MyBooksForSaleScreen.create(), controller);
                break;
            case "2":
                display.switchScreen(TransactionsScreen.create(), controller);
                break;
            default:
                invalidInput();
                break;
        }
    }
}
