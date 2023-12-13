package Utils;

import GUI.AbstractViewController;
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

public class GuiAppState extends Application implements IAppViewController {
    public static final String cssPath = "/lightMode.css";
    private static final String APP_TITLE = "SoftwareMaintenance";
    private static final double MIN_WIDTH = 900;
    private static final double MIN_HEIGHT = 600;


    private Stage primaryStage;

    @Override
    public void start(Stage stage){
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
                System.out.println(e.getMessage() + "sioss");
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
        return switch (page) {
            case SIGN_IN -> "/Login/loginLightMode.fxml";
            case SIGN_UP -> "/Register/register.fxml";
            case CHAT_LIST -> "/ChatList/chatList.fxml";
            case CHAT -> "/SingleChat/singlechat.fxml";
            default -> throw new IllegalArgumentException("Invalid page");
        };
    }
}
