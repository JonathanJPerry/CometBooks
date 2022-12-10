package edu.utdallas.cometbooks.frontend.screens;

import edu.utdallas.cometbooks.backend.Controller;
import edu.utdallas.cometbooks.data.listing.BookListingEntry;

import java.util.*;

public final class MainMenuScreen implements Screen {
    public static MainMenuScreen createFor(String netId, String name) {
        return new MainMenuScreen(netId, name);
    }

    private final String netId;
    private final String name;

    private Map<String, BookListingEntry> bookListingsForOption = new HashMap<>();

    private MainMenuScreen(String netId, String name) {
        this.netId = netId;
        this.name = name;
    }

    @Override
    public void onOpen(Controller controller) {
        bookListingsForOption.clear();

        System.out.println("Welcome to CometBooks, " + name + "!");

        List<BookListingEntry> bookListings = controller.fetchRelevantListings(netId);
        for (int i = 0; i < bookListings.size(); i++) {
            bookListingsForOption.put((i + 2) + "", bookListings.get(i));
        }
    }

    @Override
    public void handleInput(Scanner scanner, Controller controller, ScreenDisplay display) {
        System.out.println("Select a menu option to choose which tab to go to:");
        System.out.println("1. My Books for Sale");
        System.out.println("2. Transactions");

        if (bookListingsForOption.size() > 0) {
            System.out.println();
            System.out.println("The following books are for sale which may be of interest to you as well:");
            bookListingsForOption.forEach((option, bookListing) ->
                    System.out.println(option + ". View listing by seller \"" + bookListing.getSellerNetId() +
                            "\" for \"" + bookListing.getBookRecord().getTitle() + "\""));
        }

        String option = scanner.nextLine();
        if (option.equals("1")) {
            display.switchScreen(MyBooksForSaleScreen.createFor(netId), controller);
        } else if (option.equals("2")) {
            display.switchScreen(TransactionsScreen.createFor(netId), controller);
        } else if (bookListingsForOption.containsKey(option)) {
            System.out.println("This feature is not yet implemented. Please select another option.");
            // todo: implement this
        } else {
            invalidInput();
        }
    }
}
