package edu.utdallas.cometbooks.frontend.screens.messages;

import edu.utdallas.cometbooks.backend.Controller;
import edu.utdallas.cometbooks.frontend.screens.Screen;
import edu.utdallas.cometbooks.frontend.screens.ScreenDisplay;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class MessagesOverviewScreen implements Screen {
    public static MessagesOverviewScreen createFor(String netId) {
        return new MessagesOverviewScreen(netId);
    }

    private final String netId;
    private final Map<String, String> recipientForOption = new HashMap<>();

    private MessagesOverviewScreen(String netId) {
        this.netId = netId;
    }

    @Override
    public void onOpen(Controller controller) {
        System.out.print("You are now viewing your messages. ");

        List<String> recipients = controller.fetchActiveChatLogs(netId);
        for (int i = 0; i < recipients.size(); i++) {
            recipientForOption.put(String.valueOf(i + 1), recipients.get(i));
        }
    }

    @Override
    public void handleInput(Scanner scanner, Controller controller, ScreenDisplay display) {
        if (recipientForOption.isEmpty()) {
            System.out.println("You have no active chats. What would you like to do?");
            System.out.println("1. Go back");
        } else {
            System.out.println("What would you like to do?");
            recipientForOption.forEach((option, recipient) -> System.out.println(option + ". Chat with " + recipient));
            System.out.println((recipientForOption.size() + 1) + ". Go back");
        }

        if (recipientForOption.isEmpty()) {
            if (scanner.nextLine().equals("1")) {
                display.goBack(controller);
            } else {
                invalidInput();
            }
        } else {
            String option = scanner.nextLine();
            if (recipientForOption.containsKey(option)) {
                String recipient = recipientForOption.get(option);
                display.switchScreen(ChatScreen.between(netId, recipient), controller);
            } else if (option.equals((recipientForOption.size() + 1) + "")) {
                display.goBack(controller);
            } else {
                invalidInput();
            }
        }
    }
}
