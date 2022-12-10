package edu.utdallas.cometbooks.frontend.screens;

import edu.utdallas.cometbooks.backend.Controller;
import edu.utdallas.cometbooks.data.LogInResponse;

import java.util.Scanner;

public class LogInScreen implements Screen {
    public static LogInScreen create() {
        return new LogInScreen();
    }

    private LogInScreen() {
    }

    @Override
    public void onOpen(Controller controller) {
        System.out.println("Welcome to CometBooks! Please log in.");
    }

    @Override
    public void handleInput(Scanner scanner, Controller controller, ScreenDisplay display) {
        System.out.print("Net ID / Username: ");
        String username = scanner.nextLine();

        System.out.print("Password: ");
        String password = scanner.nextLine();

        LogInResponse response = controller.logIn(username, password);
        if (response == LogInResponse.SUCCESS) {
            System.out.println("Successfully logged in!");
            System.out.println();

            display.switchScreen(MainMenuScreen.create(), controller);
        } else if (response == LogInResponse.INVALID_CREDENTIALS) {
            System.out.println("Invalid username or password.");
        }
    }
}
