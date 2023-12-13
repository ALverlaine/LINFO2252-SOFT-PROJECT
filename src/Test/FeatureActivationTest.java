package Test;


import Exceptions.AlreadyHasChat;
import Exceptions.IncorrectPassword;
import Exceptions.NoUserConnected;
import Exceptions.UserDoesntExist;
import Features.Feature;
import Features.FeatureName;
import Features.Status;
import Features.Theme;
import Models.Message;
import Models.User;
import Services.AuthService;
import Services.Database;
import Services.FeatureService;
import Utils.AppState;
import Views.MenuCLIView;
import org.junit.jupiter.api.*;
import java.time.ZoneId;

import java.time.Clock;
import java.time.Instant;
import java.time.LocalTime;
import java.util.List;
import java.util.TreeMap;

import static org.junit.jupiter.api.Assertions.*;


public class FeatureActivationTest {

    AuthService authService = AuthService.getInstance();
    FeatureService featureService = FeatureService.getInstance();
    AppState appState = AppState.getInstance();

    @BeforeAll
    public static void setUp() throws NoUserConnected, UserDoesntExist, AlreadyHasChat, IncorrectPassword {
        AppState appState = AppState.getInstance();
        Database.populateUsersDB(appState);
        Database.populateChatsDB(appState);
    }

    @BeforeEach
    public void login() throws UserDoesntExist, IncorrectPassword, NoUserConnected {
        assertThrows(NoUserConnected.class, () -> appState.getConnectedUser());
        authService.login("kim", "123");
        assertEquals("kim", appState.getConnectedUserName());
    }

    @AfterEach
    public void logout() {
        appState.setConnectedUser(null);
    }

    @Test
    @DisplayName("Test getMenuOptions before and after starting to drive")
    public void testDriving(){

        MenuCLIView menu = new MenuCLIView(null);
        List<String> options = menu.getMenuOptions();
        assertEquals(5, options.size());
        featureService.changeFeatureState(FeatureName.Driving.ordinal() + 1, true);
        options = menu.getMenuOptions();
        assertEquals(4, options.size());
    }

    @Test
    @DisplayName("Test online and not connected status")
    public void testGoOnline() throws UserDoesntExist {
        User user = appState.getUser("kim");
        TreeMap<FeatureName, Feature> features = user.getFeatures();
        Status status = (Status) features.get(FeatureName.Status);
        Status.Activity activity = status.getCurrentActivity();
        assertEquals(activity, Status.Activity.Offline);

        featureService.changeFeatureState(FeatureName.Status.ordinal() + 1, true);

        status = (Status) features.get(FeatureName.Status);
        activity = status.getCurrentActivity();
        assertEquals(activity, Status.Activity.Online);
    }

    @Test
    @DisplayName("Test light and dark mode")
    public void testTheme() throws UserDoesntExist {
        Clock clock = Clock.fixed(Instant.parse("2014-12-22T10:15:30.00Z"), ZoneId.of("UTC"));
        LocalTime dateTime = LocalTime.now(clock);
        Theme.determineBackgroundColor(dateTime);

        assertEquals(Theme.BG, Theme.WHITE_BACKGROUND);

        clock = Clock.fixed(Instant.parse("2014-12-22T00:15:30.00Z"), ZoneId.of("UTC"));
        dateTime = LocalTime.now(clock);
        Theme.determineBackgroundColor(dateTime);

        assertEquals(Theme.BG, Theme.BLACK_BACKGROUND);
    }

    @Test
    @DisplayName("Test link protection")
    public void testLinkProtection() {
        User user = new User("a", "b");
        User user2 = new User("c", "d");
        Message message = new Message(user, user2, "www.google.com https://google.com", "now");
        boolean containsURL = message.extractUrls();

        assertTrue(containsURL, "No output was produced.");
        message = new Message(user, user2, "a", "now");
        containsURL = message.extractUrls();

        assertFalse(containsURL, "A link was found");

    }

}