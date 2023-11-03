package State.Models;

import java.util.Date;



public class Message {
    private User sender;
    private User receiver;
    private String content;
    private Date date;
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