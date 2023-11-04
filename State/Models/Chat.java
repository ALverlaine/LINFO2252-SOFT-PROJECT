package State.Models;

import State.Exceptions.NoMessagesFound;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Chat {

    private User user1;
    private User user2;
    List<Message> messageList;

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
        return messageList;
    }
}
