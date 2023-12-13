package Utils;

import GUI.EPages;
import GuiInterfaces.Views.IAppViewController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

public class GuiApp extends Application implements IAppViewController {
    public static String cssPath = "/lightMode.css";

    private static final String APP_TITLE = "SoftwareMaintenance";
    private static final double MIN_WIDTH = 900;
    private static final double MIN_HEIGHT = 600;
    private boolean lightMode = false;

    public void setTitle(String title) {
        primaryStage.setTitle(title);
    }

    private Stage primaryStage;

    @Override
    public void start(Stage stage){
        if (lightMode)
            cssPath = "/base.css";
        else
            cssPath = "/lightMode.css";
        primaryStage = stage;
        handleViewChange(EPages.SIGN_IN);
        stage.setTitle(APP_TITLE);
        stage.setMinWidth(MIN_WIDTH);
        stage.setMinHeight(MIN_HEIGHT);
        stage.show();
    }


    @Override
    public Window getWindow() {
        return primaryStage.getScene().getWindow();
    }

    public static void main(String[] args) {
        launch(args);
    }

    public void handleViewChange(EPages page) {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource(getFxmlPathForPage(page)));

        loader.setControllerFactory(controllerClass -> {
            try {
                return controllerClass.getDeclaredConstructor(IAppViewController.class).newInstance(this);
            } catch (InstantiationException | IllegalAccessException | IllegalArgumentException
                     | InvocationTargetException | NoSuchMethodException | SecurityException e) {
                return null;
            }
        });

        try {
            Parent parent = loader.load();
            Scene scene = primaryStage.getScene();
            if (scene == null) {
                scene = new Scene(parent);
                primaryStage.setScene(scene);
            } else {
                scene.setRoot(parent);
            }
        } catch (IllegalStateException | IOException e) {
            throw new RuntimeException(e);
        }
    }

    private String getFxmlPathForPage(EPages page) {
        if (lightMode)
            return switch (page) {
                case SIGN_IN -> "/Login/loginLightMode.fxml";
                case SIGN_UP -> "/Register/registerLightMode.fxml";
                case CHAT_LIST -> "/ChatList/chatListLightMode.fxml";
                case CHAT -> "/SingleChat/singlechat.fxml";
                case MENU -> "/Menu/menuLightMode.fxml";

                default -> throw new IllegalArgumentException("Invalid page");
            };
        else
            return switch (page) {
                case SIGN_IN -> "/Login/login.fxml";
                case SIGN_UP -> "/Register/register.fxml";
                case CHAT_LIST -> "/ChatList/chatList.fxml";
                case CHAT -> "/SingleChat/singlechat.fxml";
                case MENU -> "/Menu/menu.fxml";

                default -> throw new IllegalArgumentException("Invalid page");
            };
    }
}
