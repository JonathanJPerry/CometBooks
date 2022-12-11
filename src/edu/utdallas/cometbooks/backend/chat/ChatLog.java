package edu.utdallas.cometbooks.backend.chat;

import edu.utdallas.cometbooks.backend.student.UTDStudent;
import edu.utdallas.cometbooks.data.chat.Message;

import java.util.ArrayList;
import java.util.List;

public class ChatLog {
    public static ChatLog between(UTDStudent seller, UTDStudent buyer) {
        return new ChatLog(seller, buyer);
    }

    private final UTDStudent seller;
    private final UTDStudent buyer;
    private final List<Message> messages = new ArrayList<>();

    private ChatLog(UTDStudent seller, UTDStudent buyer) {
        this.seller = seller;
        this.buyer = buyer;
    }

    public void enableChattingWith(UTDStudent user) {

    }

    public void sendMessage(String text)    {

    }

    public List<Message> getMessages() {
        return messages;
    }

    public void add(Message message) {
        messages.add(message);
    }
}
