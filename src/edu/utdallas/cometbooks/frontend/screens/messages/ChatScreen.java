package edu.utdallas.cometbooks.frontend.screens.messages;

import edu.utdallas.cometbooks.backend.Controller;
import edu.utdallas.cometbooks.data.chat.Message;
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
        for (Message message : messages) {
            // todo add date to this
            System.out.println(message.getFromNetId() + ": " + message.getText());
        }
        System.out.println();
    }

    @Override
    public void handleInput(Scanner scanner, Controller controller, ScreenDisplay display) {
        System.out.println("What would you like say? To go back, type \"back to messages\".");
        String text = scanner.nextLine();

        if (text.equals("back to messages")) {
            display.goBack(controller);
            return;
        }

        controller.sendMessage(netId, recipient, text);
    }
}
