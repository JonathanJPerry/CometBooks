package edu.utdallas.cometbooks.ui;

import edu.utdallas.cometbooks.CometBooks;
import edu.utdallas.cometbooks.Controller;

import java.util.Scanner;

public interface Screen {
    void onOpen(Controller controller);

    void handleInput(Scanner scanner, Controller controller, ScreenDisplay display);
}
