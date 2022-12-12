package edu.utdallas.cometbooks.frontend.screens.transactions;

import edu.utdallas.cometbooks.backend.Controller;
import edu.utdallas.cometbooks.data.transactions.Transaction;
import edu.utdallas.cometbooks.frontend.screens.Screen;
import edu.utdallas.cometbooks.frontend.screens.ScreenDisplay;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class TransactionsOverviewScreen implements Screen {
    public static TransactionsOverviewScreen createFor(String netId) {
        return new TransactionsOverviewScreen(netId);
    }

    private final String netId;
    private final Map<String, Transaction> transactionsForOption = new HashMap<>();

    private TransactionsOverviewScreen(String netId) {
        this.netId = netId;
    }

    @Override
    public void onOpen(Controller controller) {
        System.out.print("The transactions tab has been open. ");

        List<Transaction> transactions = controller.fetchActiveTransactionsInvolving(netId);
        for (int i = 0; i < transactions.size(); i++) {
            transactionsForOption.put((i + 1) + "", transactions.get(i));
        }
    }

    @Override
    public void handleInput(Scanner scanner, Controller controller, ScreenDisplay display) {
        if (transactionsForOption.isEmpty()) {
            System.out.println("You have no active transactions. What would you like to do?");
            System.out.println("1. Go back");
        } else {
            int count = transactionsForOption.size();
            System.out.println("You have " + count + " active transactions. What would you like to do?");

            for (Map.Entry<String, Transaction> transaction : transactionsForOption.entrySet()) {
                String with = transaction.getValue().getBuyerNetId().equals(netId) ? transaction.getValue().getSellerNetId() : transaction.getValue().getBuyerNetId();
                System.out.println(transaction.getKey() + ". View transaction with " + with);
            }

            System.out.println((transactionsForOption.size() + 1) + ". Go back");
        }

        if (transactionsForOption.isEmpty()) {
            if (scanner.nextLine().equals("1")) {
                display.goBack(controller);
            } else {
                invalidInput();
            }
        } else {
            String option = scanner.nextLine();
            if (transactionsForOption.containsKey(option)) {
                display.switchScreen(TransactionScreen.createFor(netId, transactionsForOption.get(option)), controller);
            } else if (option.equals((transactionsForOption.size() + 1) + "")) {
                display.goBack(controller);
            } else {
                invalidInput();
            }
        }
    }
}
