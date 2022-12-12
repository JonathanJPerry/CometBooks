package edu.utdallas.cometbooks.frontend.screens.listing;

import edu.utdallas.cometbooks.backend.Controller;
import edu.utdallas.cometbooks.data.listing.BookCondition;
import edu.utdallas.cometbooks.data.listing.BookListingEntry;
import edu.utdallas.cometbooks.frontend.screens.Screen;
import edu.utdallas.cometbooks.frontend.screens.ScreenDisplay;

import java.util.*;

public final class EditBookForSaleScreen implements Screen {
    public static EditBookForSaleScreen createFor(String netId, BookListingEntry listing) {
        return new EditBookForSaleScreen(netId, listing);
    }

    private final String netId;
    private final BookListingEntry originalListing;

    private boolean firstTime;
    private BookCondition bookCondition;
    private Double price;
    private String description;

    private EditBookForSaleScreen(String netId, BookListingEntry listing) {
        this.netId = netId;
        originalListing = listing;

        bookCondition = originalListing.getCondition();
        price = originalListing.getPrice();
        description = originalListing.getDescription();
    }

    @Override
    public void onOpen(Controller controller) {
        firstTime = false;
    }

    @Override
    public void handleInput(Scanner scanner, Controller controller, ScreenDisplay display) {
        if (bookCondition == null) {
            inquireForBookCondition(scanner, controller, display);
        }
        if (price == null) {
            inquireForPrice(scanner, controller, display);
        }
        if (description == null) {
            inquireForDescription(scanner, controller, display);
        }

        if (firstTime) {
            firstTime = false;
            System.out.println("What do you want to edit?");
            System.out.println("1. Change condition");
            System.out.println("2. Change price");
            System.out.println("3. Change description");
            System.out.println("4. Trash listing");
            System.out.println("5. Go back");
        } else {
            System.out.println("Are you sure you finished editing this book?");
            System.out.println("1. Yes");
            System.out.println("2. Change condition");
            System.out.println("3. Change price");
            System.out.println("4. Change description");
            System.out.println("5. Trash listing");
            System.out.println("6. Cancel");
        }

        String choice = scanner.nextLine();
        if (!firstTime && choice.equals("1")) {
            BookListingEntry updatedListing = BookListingEntry.builder()
                    .sellerNetId(netId)
                    .book(originalListing.getBookRecord())
                    .condition(bookCondition)
                    .description(description)
                    .price(price)
                    .build();

            controller.editListing(originalListing, updatedListing);
            System.out.println("Successfully edited book for sale!");
            System.out.println();

            display.goBack(controller);
        } else if (firstTime && choice.equals("1") || !firstTime && choice.equals("2")) {
            bookCondition = null;
        } else if (firstTime && choice.equals("2") || !firstTime && choice.equals("3")) {
            price = null;
        } else if (firstTime && choice.equals("3") || !firstTime && choice.equals("4")) {
            description = null;
        } else if (firstTime && choice.equals("4") || !firstTime && choice.equals("5")) {
            controller.trashListing(originalListing);
        } else if (firstTime && choice.equals("5") || !firstTime && choice.equals("6")) {
            display.goBack(controller);
        }
    }

    private void inquireForBookCondition(Scanner scanner, Controller controller, ScreenDisplay display) {
        System.out.println("Now we need to know the book's condition. Your options are:");
        Arrays.stream(BookCondition.values()).forEach(condition -> System.out.println((condition.ordinal() + 1) + ". " + condition.getDisplayName()));

        System.out.print("Enter the number of the condition of the book you want to sell. If you aren't interested anymore, type \"go back\": ");
        String conditionNumber = scanner.nextLine();

        if (conditionNumber.equalsIgnoreCase("go back")) {
            display.goBack(controller);
            return;
        }

        Optional<BookCondition> bookConditionOptional = Arrays.stream(BookCondition.values())
                .filter(condition -> condition.ordinal() == Integer.parseInt(conditionNumber) - 1)
                .findFirst();

        if (bookConditionOptional.isEmpty()) {
            System.out.println("You entered an invalid book condition. Try again.");
            return;
        }

        bookCondition = bookConditionOptional.get();
        System.out.println("You chose to sell " + originalListing.getBookRecord().getTitle() + " in " + bookCondition.getDisplayName().toLowerCase() + " condition.");
        System.out.println();
    }

    private void inquireForPrice(Scanner scanner, Controller controller, ScreenDisplay display) {
        double suggestedPrice = controller.fetchSuggestedPrice(originalListing.getBookRecord().getIsbn());
        System.out.print("Enter the price you want to sell the book for. We suggest you sell it for $"
                + String.format("%.2f", suggestedPrice) + ". If you aren't interested anymore, type \"go back\": ");

        String priceString = scanner.nextLine();

        if (priceString.equalsIgnoreCase("go back")) {
            display.goBack(controller);
            return;
        }

        try {
            price = Double.parseDouble(priceString);
        } catch (NumberFormatException e) {
            System.out.println("You entered an invalid price. Try again.");
            return;
        }

        System.out.println("You chose to sell " + originalListing.getBookRecord().getTitle() + " for $" + String.format("%.2f", price) + ".");
        System.out.println();
    }

    private void inquireForDescription(Scanner scanner, Controller controller, ScreenDisplay display) {
        System.out.print("Enter a description for the book. If you aren't interested anymore, type \"go back\": ");
        String descriptionString = scanner.nextLine();

        if (descriptionString.equalsIgnoreCase("go back")) {
            display.goBack(controller);
            return;
        }

        description = descriptionString;
        System.out.println("You chose to sell " + originalListing.getBookRecord().getTitle() + " with the description \"" + description + "\".");
        System.out.println();
    }
}
