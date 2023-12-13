import Exceptions.AlreadyHasChat;
import Exceptions.NoUserConnected;
import Exceptions.UserDoesntExist;
import Services.Database;
import Utils.GuiAppState;
import Views.ViewsCLIController;
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
        ViewsCLIController view = new ViewsCLIController();
        //view.run();
        GuiAppState.main(args);
    }
}
