/*
package Test;


import Exceptions.AlreadyHasChat;
import Exceptions.IncorrectPassword;
import Exceptions.NoUserConnected;
import Exceptions.UserDoesntExist;
import Models.User;
import Services.AuthService;
import Services.Database;
import Services.FeatureService;
import Utils.AppState;
import Views.ChatView;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import javax.swing.text.View;

import static org.junit.jupiter.api.Assertions.*;


public class FeatureActivationTest {

    AuthService authService = AuthService.getInstance();
    FeatureService featureService = FeatureService.getInstance();

    @BeforeAll
    public static void setUp() throws NoUserConnected, UserDoesntExist, AlreadyHasChat, IncorrectPassword {
        AppState appState = AppState.getInstance();
        Database.populateUsersDB(appState);
        Database.populateChatsDB(appState);
        authService.login("kim", "123");
    }

    @Test
    public void testLoginAndResearchActivated() {
        // Arrange
        ChatView view = new ChatView(null); // Provide your actual implementation


    }

}
*/