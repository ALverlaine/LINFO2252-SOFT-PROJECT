package Models;

import Exceptions.NoMessagesFound;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Chat {

    private final User user1;
    private final User user2;
    List<Message> messageList = new ArrayList<>();
    List<Message> newMessageList1 = new ArrayList<>();
    List<Message> newMessageList2 = new ArrayList<>();


    public Chat(User user1, User user2) {
        this.user1 = user1;
        this.user2 = user2;
    }

    public List<User> getChatUsers() {
        return Collections.unmodifiableList(Arrays.asList(user1, user2));
    }

    public List<Message> findMessage(String messageToFind) {
        List<Message> foundMessages = new ArrayList<>();
        for (Message message : messageList) {
            if (message.getContent().contains(messageToFind)) {
                foundMessages.add(message);
            }
        }
        return foundMessages;
    }

    public void addMessage(Message message) {
        messageList.add(message);
    }

    public List<Message> getMessages() {
        return this.messageList;
    }

    public List<Message> getNewMessages(User user) {
        return user.equals(user1) ? newMessageList1 : newMessageList2;
    }

    public void removeNewMessages(User user) {
        if (user.equals(user1)) newMessageList1.clear();
        else newMessageList2.clear();
    }

    public void addNewMessage(Message message, User sender) {
        if (sender.equals(user1)) newMessageList2.add(message);
        else newMessageList1.add(message);
    }

    public void removeAllNewMessages(User connectedUser) {
        if (connectedUser.equals(user1)) newMessageList1.clear();
        else newMessageList2.clear();
    }
}