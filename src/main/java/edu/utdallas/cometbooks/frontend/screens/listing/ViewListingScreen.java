package edu.utdallas.cometbooks.frontend.screens.listing;

import edu.utdallas.cometbooks.backend.Controller;
import edu.utdallas.cometbooks.data.listing.BookListingEntry;
import edu.utdallas.cometbooks.frontend.screens.Screen;
import edu.utdallas.cometbooks.frontend.screens.ScreenDisplay;

import java.util.Scanner;

public final class ViewListingScreen implements Screen {
    public static ViewListingScreen createFor(String netId, BookListingEntry listing) {
        return new ViewListingScreen(netId, listing);
    }

    private final String netId;
    private final BookListingEntry listing;

    private ViewListingScreen(String netId, BookListingEntry listing) {
        this.netId = netId;
        this.listing = listing;
    }

    @Override
    public void onOpen(Controller controller) {
        System.out.println("You are now viewing the listing by \"" + listing.getSellerNetId() + "\" for \"" + listing.getBookRecord().getTitle() + "\".");
    }

    @Override
    public void handleInput(Scanner scanner, Controller controller, ScreenDisplay display) {
        System.out.println("What would you like to do?");
        System.out.println("1. Buy book");
        System.out.println("2. Go back");

        switch (scanner.nextLine()) {
            case "1" -> {
                controller.buyBook(listing, netId);
                System.out.println("We've let the seller know that you're interested in buying the book!");

                display.goBack(controller);
            }
            case "2" -> display.goBack(controller);
            default -> invalidInput();
        }
    }
}
