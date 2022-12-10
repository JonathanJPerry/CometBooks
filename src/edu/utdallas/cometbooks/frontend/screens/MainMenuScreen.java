package edu.utdallas.cometbooks.frontend.screens;

import edu.utdallas.cometbooks.backend.Controller;

import java.util.Scanner;

public final class MainMenuScreen implements Screen {
    public static MainMenuScreen createFor(String netId, String name) {
        return new MainMenuScreen(netId, name);
    }

    private final String netId;
    private final String name;

    private MainMenuScreen(String netId, String name) {
        this.netId = netId;
        this.name = name;
    }

    @Override
    public void onOpen(Controller controller) {
        System.out.println("Welcome to CometBooks, " + name + "!");
    }

    @Override
    public void handleInput(Scanner scanner, Controller controller, ScreenDisplay display) {
        System.out.println("Select a menu option to choose which tab to go to:");
        System.out.println("1. My Books for Sale");
        System.out.println("2. Transactions");

        switch (scanner.next()) {
            case "1" -> display.switchScreen(MyBooksForSaleScreen.createFor(netId), controller);
            case "2" -> display.switchScreen(TransactionsScreen.createFor(netId), controller);
            default -> invalidInput();
        }
    }
}
