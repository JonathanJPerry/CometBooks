package edu.utdallas.cometbooks.backend.chat;

import edu.utdallas.cometbooks.backend.student.UTDStudent;

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
