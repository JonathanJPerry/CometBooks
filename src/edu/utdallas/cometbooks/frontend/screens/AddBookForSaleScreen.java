package edu.utdallas.cometbooks.frontend.screens;

import edu.utdallas.cometbooks.backend.Controller;

import java.util.Scanner;

public final class AddBookForSaleScreen implements Screen {
    public static AddBookForSaleScreen createFor(String netId) {
        return new AddBookForSaleScreen(netId);
    }

    private final String netId;

    private AddBookForSaleScreen(String netId) {
        this.netId = netId;
    }

    @Override
    public void onOpen(Controller controller) {
    }

    @Override
    public void handleInput(Scanner scanner, Controller controller, ScreenDisplay display) {

    }
}
