package ViewsAbstract;

public interface IChatListView {
    void goToChat();
    void userDoesntExist(String receiver);
    void hasNoChat(String receiver);
    void alreadyHasChat(String receiver);
}
