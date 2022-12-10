package edu.utdallas.cometbooks.frontend.screens;

import edu.utdallas.cometbooks.backend.Controller;
import edu.utdallas.cometbooks.data.listing.BookListingEntry;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public final class MyBooksForSaleScreen implements Screen {
    public static MyBooksForSaleScreen createFor(String netId) {
        return new MyBooksForSaleScreen(netId);
    }

    private final String netId;

    private final Map<String, BookListingEntry> listingForOption = new HashMap<>();

    private MyBooksForSaleScreen(String netId) {
        this.netId = netId;
    }

    @Override
    public void onOpen(Controller controller) {
        listingForOption.clear();
        System.out.print("The my books for sale tab has been open. ");

        List<BookListingEntry> listings = controller.fetchBookListingsBy(netId);
        if (listings.isEmpty()) {
            System.out.println("You have no books for sale.");
        } else {
            System.out.println("You have the following books for sale:");

            for (int i = 0; i < listings.size(); i++) {
                BookListingEntry listing = listings.get(i);
                System.out.println("- " + listing.getBookRecord().getTitle());

                listingForOption.put((i + 2) + "", listing);
            }
        }
        System.out.println();
    }

    @Override
    public void handleInput(Scanner scanner, Controller controller, ScreenDisplay display) {
        System.out.println("Select a menu option to choose what to do:");
        System.out.println("1. Add a new book for sale.");

        listingForOption.forEach((key, value) -> System.out.println(key + ". Edit \"" + value.getBookRecord().getTitle() + "\""));

        final String goBackOption = (listingForOption.size() + 2) + "";
        System.out.println(goBackOption + ". Go back.");

        String choice = scanner.nextLine();
        if (choice.equals("1")) {
            display.switchScreen(AddBookForSaleScreen.createFor(netId), controller);
        } else if (listingForOption.containsKey(choice)) {
            System.out.println("This feature is not yet implemented. Please select another option.");
            // todo: implement this
        } else if (choice.equals(goBackOption)) {
            display.goBack(controller);
        } else {
            invalidInput();
        }
    }
}
