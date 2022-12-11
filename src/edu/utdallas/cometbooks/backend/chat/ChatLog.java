package edu.utdallas.cometbooks.backend.chat;

import edu.utdallas.cometbooks.backend.student.UTDStudent;

public class ChatLog {
    public static ChatLog between(UTDStudent seller, UTDStudent buyer) {
        return new ChatLog(seller, buyer);
    }

    private final UTDStudent seller;
    private final UTDStudent buyer;

    private ChatLog(UTDStudent seller, UTDStudent buyer) {
        this.seller = seller;
        this.buyer = buyer;
    }

    public void enableChattingWith(UTDStudent user) {

    }

    public void sendMessage(String text)    {
        Message m = new Message();
        m.text = text;
    }
}
