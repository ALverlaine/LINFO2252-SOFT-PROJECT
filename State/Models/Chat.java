package State.Models;

import State.Exceptions.NoMessagesFound;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Chat {

    private final User user1;
    private final User user2;
    List<Message> messageList = new ArrayList<>();

    public Chat(User user1, User user2) {
        this.user1 = user1;
        this.user2 = user2;
    }

    public List<User> getChatUsers() {
        return Collections.unmodifiableList(Arrays.asList(user1, user2));
    }

    public Message findMessage(String messageToFind) throws NoMessagesFound {
        return null;
    }

    public void addMessage(Message message) {
        messageList.add(message);
    }

    public List<Message> getMessages() {
        if(!this.messageList.isEmpty()) return this.messageList;
        else return null;
    }
}
