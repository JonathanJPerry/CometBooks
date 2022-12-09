package edu.utdallas.cometbooks;

import edu.utdallas.cometbooks.ui.MainMenuScreen;
import edu.utdallas.cometbooks.ui.Screen;
import edu.utdallas.cometbooks.ui.ScreenDisplay;

import java.util.Scanner;

public final class CometBooks {
    public static final Screen INITIAL_SCREEN = MainMenuScreen.create();

    private static final Scanner SCANNER = new Scanner(System.in);
    private static final ScreenDisplay SCREEN_DISPLAY = ScreenDisplay.createBlank();
    private static final Controller CONTROLLER = Controller.create();

    public static void main(String[] args) {
        SCREEN_DISPLAY.switchScreen(INITIAL_SCREEN, CONTROLLER);
        while (true) {
            SCREEN_DISPLAY.getScreen().handleInput(SCANNER, CONTROLLER, SCREEN_DISPLAY);
        }
    }
}
