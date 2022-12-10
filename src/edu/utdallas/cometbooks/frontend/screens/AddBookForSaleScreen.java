package edu.utdallas.cometbooks.frontend.screens;

import com.sun.security.jgss.GSSUtil;
import edu.utdallas.cometbooks.backend.Controller;
import edu.utdallas.cometbooks.data.book.BookRecord;
import edu.utdallas.cometbooks.data.listing.BookCondition;

import java.util.*;

public final class AddBookForSaleScreen implements Screen {
    public static AddBookForSaleScreen createFor(String netId) {
        return new AddBookForSaleScreen(netId);
    }

    private final String netId;
    private final Map<Integer, BookRecord> bookRecordForChoice = new HashMap<>();

    private BookRecord bookRecord;
    private BookCondition bookCondition;
    private Double price;
    private String description;

    private AddBookForSaleScreen(String netId) {
        // todo suggested price
        this.netId = netId;
    }

    @Override
    public void onOpen(Controller controller) {
        List<BookRecord> books = controller.fetchBooks(netId);
        for (int i = 0; i < books.size(); i++) {
            bookRecordForChoice.put(i + 1, books.get(i));
        }
    }

    @Override
    public void handleInput(Scanner scanner, Controller controller, ScreenDisplay display) {
        if (bookRecord == null) {
            inquireForBook(scanner, controller, display);
        }
        if (bookCondition == null) {
            inquireForBookCondition(scanner, controller, display);
        }
        if (price == null) {
            inquireForPrice(scanner, controller, display);
        }
        if (description == null) {
            inquireForDescription(scanner, controller, display);
        }

        System.out.println("Are you sure you want to add this book for sale?");
        System.out.println("1. Yes");
        System.out.println("2. Change book");
        System.out.println("3. Change condition");
        System.out.println("4. Change price");
        System.out.println("5. Change description");
        System.out.println("6. Cancel");

        String choice = scanner.nextLine();
        switch (choice) {
            case "1" -> {
                // todo add listing to database
                display.goBack(controller);
            }
            case "2" -> bookRecord = null;
            case "3" -> bookCondition = null;
            case "4" -> price = null;
            case "5" -> description = null;
            case "6" -> display.goBack(controller);
        }
    }

    private void inquireForBook(Scanner scanner, Controller controller, ScreenDisplay display) {
        System.out.println("Your options for books to sell are:");
        bookRecordForChoice.forEach((choice, book) -> System.out.println(choice + ". " + book.getTitle()));

        System.out.print("Enter the number of the book you want to sell. If you aren't interested anymore, type \"go back\": ");
        String choice = scanner.nextLine();

        if (choice.equalsIgnoreCase("go back")) {
            display.goBack(controller);
            return;
        }

        Optional<BookRecord> bookRecordOptional = bookRecordForChoice.entrySet().stream()
                .filter(entry -> entry.getKey().toString().equals(choice))
                .map(Map.Entry::getValue)
                .findFirst();

        if (bookRecordOptional.isEmpty()) {
            System.out.println("You entered an invalid book to sell. Try again.");
            return;
        }

        bookRecord = bookRecordOptional.get();
        System.out.println("You chose to sell " + bookRecord.getTitle() + ".");
    }

    private void inquireForBookCondition(Scanner scanner, Controller controller, ScreenDisplay display) {
        System.out.println("Now we need to know the book's condition. Your options are:");
        Arrays.stream(BookCondition.values()).forEach(condition -> System.out.println((condition.ordinal() + 1) + ". " + condition.name()));

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
        System.out.println("You chose to sell " + bookRecord.getTitle() + " in " + bookCondition.name().toLowerCase() + " condition.");
    }

    private void inquireForPrice(Scanner scanner, Controller controller, ScreenDisplay display) {
        System.out.print("Enter the price you want to sell the book for. If you aren't interested anymore, type \"go back\": ");
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

        System.out.println("You chose to sell " + bookRecord.getTitle() + " for $" + String.format("%.2f", price) + ".");
    }

    private void inquireForDescription(Scanner scanner, Controller controller, ScreenDisplay display) {
        System.out.print("Enter a description for the book. If you aren't interested anymore, type \"go back\": ");
        String descriptionString = scanner.nextLine();

        if (descriptionString.equalsIgnoreCase("go back")) {
            display.goBack(controller);
            return;
        }

        description = descriptionString;
        System.out.println("You chose to sell " + bookRecord.getTitle() + " with the description \"" + description + "\".");
    }
}
