package edu.utdallas.cometbooks.ui;

import edu.utdallas.cometbooks.Controller;

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

    }
}
