package edu.utdallas.cometbooks.frontend.screens;

import edu.utdallas.cometbooks.backend.Controller;

import java.util.Scanner;

public interface Screen {
    void onOpen(Controller controller);

    void handleInput(Scanner scanner, Controller controller, ScreenDisplay display);

    default void invalidInput() {
        System.out.println("Invalid input, please try again.");
    }
}
