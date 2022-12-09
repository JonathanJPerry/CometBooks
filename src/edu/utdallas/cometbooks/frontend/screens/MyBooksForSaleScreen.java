package edu.utdallas.cometbooks.frontend.screens;

import edu.utdallas.cometbooks.backend.Controller;

import java.util.Scanner;

public final class MyBooksForSaleScreen implements Screen {
    public static MyBooksForSaleScreen create() {
        return new MyBooksForSaleScreen();
    }

    private MyBooksForSaleScreen() {
    }

    @Override
    public void onOpen(Controller controller) {
        System.out.println("The my books for sale tab has been open.");
    }

    @Override
    public void handleInput(Scanner scanner, Controller controller, ScreenDisplay display) {
        System.out.println("Select a menu option to choose what to do:");
        System.out.println("1. Add a new book for sale.");
        System.out.println("2. Go back.");

        switch (scanner.next()) {
            case "1" -> display.switchScreen(AddBookForSaleScreen.create(), controller);
            case "2" -> display.goBack(controller);
            default -> invalidInput();
        }
    }
}
