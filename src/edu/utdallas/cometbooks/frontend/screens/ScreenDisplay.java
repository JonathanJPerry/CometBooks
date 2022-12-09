package edu.utdallas.cometbooks.frontend.screens;

import edu.utdallas.cometbooks.backend.Controller;

import java.util.ArrayDeque;
import java.util.Deque;

public final class ScreenDisplay {
    public static ScreenDisplay createBlank() {
        return new ScreenDisplay(null);
    }

    private final Deque<Screen> screenStack = new ArrayDeque<>();

    private ScreenDisplay(Screen screen) {
        if (screen != null) {
            screenStack.add(screen);
        }
    }

    public Screen getCurrentScreen() {
        return screenStack.peekLast();
    }

    public void switchScreen(Screen screen, Controller controller) {
        screenStack.add(screen);
        screen.onOpen(controller);
    }

    public void goBack(Controller controller) {
        screenStack.pollLast();

        if (screenStack.isEmpty()) {
            return;
        }

        getCurrentScreen().onOpen(controller);
    }
}
