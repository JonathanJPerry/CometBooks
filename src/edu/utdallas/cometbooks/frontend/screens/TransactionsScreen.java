package edu.utdallas.cometbooks.frontend.screens;

import edu.utdallas.cometbooks.backend.Controller;

import java.util.Scanner;

public class TransactionsScreen implements Screen {
    public static TransactionsScreen createFor(String netId) {
        return new TransactionsScreen(netId);
    }

    private final String netId;

    private TransactionsScreen(String netId) {
        this.netId = netId;
    }

    @Override
    public void onOpen(Controller controller) {
        System.out.println("The transactions tab has been open.");
    }

    @Override
    public void handleInput(Scanner scanner, Controller controller, ScreenDisplay display) {

    }
}
