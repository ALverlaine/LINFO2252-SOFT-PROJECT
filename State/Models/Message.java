package State.Models;

import java.util.Date;

public record Message(User sender, User receiver, String content, Date date) {
    @Override
    public String toString() {
        return date.toString() + sender + content;
    }
}
