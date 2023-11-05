package State.Models;

import java.util.Date;



public class Message {
    private final User sender;
    private User receiver;
    private final String content;
    private final String date;

    public Message(User sender, User receiver, String content, String date) {
        this.sender = sender;
        this.receiver = receiver;
        this.content = content;
        this.date = date;
    }

    @Override
    public String toString () {
        return date + "\n" + sender.asMessageString() + "Content: " + content + "\n";
    }
}