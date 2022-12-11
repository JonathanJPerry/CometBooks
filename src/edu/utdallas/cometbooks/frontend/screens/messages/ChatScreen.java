package edu.utdallas.cometbooks.frontend.screens.messages;

import edu.utdallas.cometbooks.backend.Controller;
import edu.utdallas.cometbooks.data.chat.Message;
import edu.utdallas.cometbooks.data.chat.Notification;
import edu.utdallas.cometbooks.frontend.screens.Screen;
import edu.utdallas.cometbooks.frontend.screens.ScreenDisplay;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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

    private void sendConfirmationMessage(String text) {
        System.out.println("Are you sure you want to say \"" + text + "\" to " + recipient + "?");
        System.out.println("1. Yes");
        System.out.println("2. No");
    }

    private String formatMessage(Message message) {
        String date = formatDate(message.getDate());
        return "\t" + message.getFromNetId() + " (" + date + "): " + message.getText();
    }

    private String formatDate(LocalDateTime date) {
        LocalDate today = LocalDate.now();
        LocalDate yesterday = today.minusDays(1);

        if (date.toLocalDate().isEqual(yesterday)) {
            String time = DateTimeFormatter.ofPattern("h:mm a").format(date);
            return "Yesterday, " + time;
        } else if (date.toLocalDate().isEqual(today)) {
            return DateTimeFormatter.ofPattern("h:mm a").format(date);
        }

        return DateTimeFormatter.ofPattern("MM/dd/yyyy, h:mm a").format(date);
    }
}
