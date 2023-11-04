package State.Models;

import java.util.Date;



public class Message {
    private final User sender;
    private User receiver;
    private final String content;
    private final Date date;

    public Message(User sender, User receiver, String content, Date date) {
        this.sender = sender;
        this.receiver = receiver;
        this.content = content;
        this.date = date;
    }

    @Override
    public String toString () {
        return date.toString() + sender + content;
    }
}