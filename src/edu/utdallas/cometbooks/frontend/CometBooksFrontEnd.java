package edu.utdallas.cometbooks.frontend;

import edu.utdallas.cometbooks.backend.CometBooksBackEnd;
import edu.utdallas.cometbooks.backend.Controller;
import edu.utdallas.cometbooks.frontend.screens.MainMenuScreen;
import edu.utdallas.cometbooks.frontend.screens.Screen;
import edu.utdallas.cometbooks.frontend.screens.ScreenDisplay;

import java.util.Scanner;

public final class CometBooksFrontEnd {
    public static final Screen INITIAL_SCREEN = MainMenuScreen.create();

    private static final Scanner SCANNER = new Scanner(System.in);
    private static final ScreenDisplay SCREEN_DISPLAY = ScreenDisplay.createBlank();

    public static void main(String[] args) {
        CometBooksBackEnd backEnd = CometBooksBackEnd.initialize();
        Controller controller = backEnd.createControllerInstance();

        SCREEN_DISPLAY.switchScreen(INITIAL_SCREEN, controller);
        while (true) {
            SCREEN_DISPLAY.getCurrentScreen().handleInput(SCANNER, controller, SCREEN_DISPLAY);
        }
    }
}
