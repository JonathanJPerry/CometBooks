package edu.utdallas.cometbooks.frontend.screens.messages;

import edu.utdallas.cometbooks.backend.Controller;
import edu.utdallas.cometbooks.data.chat.Message;
import edu.utdallas.cometbooks.data.chat.Notification;
import edu.utdallas.cometbooks.frontend.screens.Screen;
import edu.utdallas.cometbooks.frontend.screens.ScreenDisplay;

import java.util.List;
import java.util.Scanner;

public class ChatScreen implements Screen {
    public static ChatScreen between(String netId, String recipient) {
        return new ChatScreen(netId, recipient);
    }

    private final String netId;
    private final String recipient;

    private ChatScreen(String netId, String recipient) {
        this.netId = netId;
        this.recipient = recipient;
    }

    @Override
    public void onOpen(Controller controller) {
        List<Message> messages = controller.fetchMessagesBetween(netId, recipient);
        System.out.println("You are now chatting with \"" + recipient + "\".");
        for (Message message : messages) {
            System.out.println(formatMessage(message));
        }
    }

    @Override
    public void handleInput(Scanner scanner, Controller controller, ScreenDisplay display) {
        System.out.println("What would you like say? To go back, type \"back to messages\". To print all messages in this chat again, type \"print all\".");
        String text = scanner.nextLine();

        if (text.equalsIgnoreCase("back to messages")) {
            display.goBack(controller);
            return;
        }

        if (text.equalsIgnoreCase("print all")) {
            List<Message> messages = controller.fetchMessagesBetween(netId, recipient);
            for (Message message : messages) {
                System.out.println(formatMessage(message));
            }
            return;
        }

        sendConfirmationMessage(text);

        String option = scanner.nextLine();
        while (!option.equals("1")) {
            if (option.equals("2")) {
                return;
            }
            invalidInput();

            sendConfirmationMessage(text);
            option = scanner.nextLine();
        }

        controller.sendMessage(netId, recipient, text);

        Notification notification = Notification.builder()
                .text(netId + " sent you a message.")
                .build();
        controller.sendNotification(recipient, notification);

        Message message = controller.fetchLatestMessageBetween(netId, recipient);
        System.out.println(formatMessage(message));
    }

    private String formatMessage(Message message) {
        // todo add date to this
        return "\t" + message.getFromNetId() + ": " + message.getText();
    }

    private void sendConfirmationMessage(String text) {
        System.out.println("Are you sure you want to say \"" + text + "\" to " + recipient + "?");
        System.out.println("1. Yes");
        System.out.println("2. No");
    }
}
