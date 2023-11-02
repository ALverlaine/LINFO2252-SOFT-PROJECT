package State.Models;

import java.util.Date;

public record Message(String sender, String receiver, String content, Date date) {
}
