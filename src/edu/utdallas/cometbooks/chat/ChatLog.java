package edu.utdallas.cometbooks.chat;

import edu.utdallas.cometbooks.student.UTDStudent;

import java.util.Date;

public class ChatLog {
    public UTDStudent sender;
    public UTDStudent reciever;

    public void enableChattingWith(UTDStudent user) {

    }

    public void sendMessage(String text)    {
        Message m = new Message();
        m.text = text;
    }
}
