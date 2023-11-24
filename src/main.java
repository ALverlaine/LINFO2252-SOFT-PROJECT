import Exceptions.AlreadyHasChat;
import Exceptions.NoUserConnected;
import Exceptions.UserDoesntExist;
import Services.Database;
import Views.ViewsController;
import Utils.AppState;
import Features.Theme;

import java.time.LocalTime;

public class main {
    public static void main(String[] args) throws NoUserConnected, UserDoesntExist, AlreadyHasChat {
        AppState appState = AppState.getInstance();
        LocalTime currentTime = LocalTime.now();
        Theme.determineBackgroundColor(currentTime);
        Database.populateUsersDB(appState);
        Database.populateChatsDB(appState);
        ViewsController view = new ViewsController();
        view.run();
    }
}
