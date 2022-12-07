import java.util.Date;

class Message {
    public String text;
    public Date date;
}

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
