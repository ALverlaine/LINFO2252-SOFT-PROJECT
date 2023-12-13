package ViewsAbstract;

import Models.Message;

import java.util.List;

public interface IChatView {
    void displayNewMessageAfterSend(Message message);
    void displayMessages(List<Message> messages);
    String enterMessageToSearch();
}
