package edu.utdallas.cometbooks.ui;

import edu.utdallas.cometbooks.Controller;

public final class ScreenDisplay {
    public static ScreenDisplay createBlank() {
        return new ScreenDisplay(null);
    }

    private Screen screen;

    private ScreenDisplay(Screen screen) {
        this.screen = screen;
    }

    public Screen getScreen() {
        return screen;
    }

    public void switchScreen(Screen screen, Controller controller) {
        this.screen = screen;
        screen.onOpen(controller);
    }
}
