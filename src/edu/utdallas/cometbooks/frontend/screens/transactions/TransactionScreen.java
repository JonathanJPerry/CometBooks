package edu.utdallas.cometbooks.frontend.screens.transactions;

import edu.utdallas.cometbooks.backend.Controller;
import edu.utdallas.cometbooks.data.chat.Message;
import edu.utdallas.cometbooks.data.listing.BookListingEntry;
import edu.utdallas.cometbooks.data.transactions.Transaction;
import edu.utdallas.cometbooks.frontend.screens.Screen;
import edu.utdallas.cometbooks.frontend.screens.ScreenDisplay;
import edu.utdallas.cometbooks.frontend.screens.messages.ChatScreen;

import java.util.List;
import java.util.Scanner;

public final class TransactionScreen implements Screen {
    public static TransactionScreen createFor(String netId, Transaction transaction) {
        return new TransactionScreen(netId, transaction);
    }

    private final String netId;
    private final Transaction transaction;

    private TransactionScreen(String netId, Transaction transaction) {
        this.netId = netId;
        this.transaction = transaction;
    }

    @Override
    public void onOpen(Controller controller) {
        BookListingEntry listing = transaction.getListing();

        System.out.println("You are now viewing the transaction for \"" + listing.getBookRecord().getTitle() + "\" between you and " + transaction.getOther(netId) + ".");
        System.out.println();
        System.out.println("Book condition: " + listing.getCondition().getDisplayName());
        System.out.println("Book description: \"" + listing.getDescription() + "\"");
        System.out.printf("Book price: %.2f\n", listing.getPrice());
        System.out.println();
    }

    @Override
    public void handleInput(Scanner scanner, Controller controller, ScreenDisplay display) {
        System.out.println("What would you like to do?");
        System.out.println("1. Complete transaction");
        System.out.println("2. Go back");

        String option = scanner.nextLine();
        switch (option) {
            case "1" -> System.out.println("This feature has not been implemented yet.");
            case "2" -> display.goBack(controller);
            default -> invalidInput();
        }
    }
}
