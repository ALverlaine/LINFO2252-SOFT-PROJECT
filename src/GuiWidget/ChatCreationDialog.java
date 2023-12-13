package GuiWidget;

import Utils.GuiAppState;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;

import java.util.ArrayList;
import java.util.Objects;

public class ChatCreationDialog extends Dialog<String> {

    private final TextField username = new TextField();

    public ChatCreationDialog() {
        Label nameLabel = new Label("New contact");

        VBox vbox = new VBox(
                nameLabel, username
        );

        vbox.setSpacing( 10.0d );

        DialogPane dp = getDialogPane();

        nameLabel.getStylesheets().add(Objects.requireNonNull(getClass().getResource(GuiAppState.cssPath)).toExternalForm());
        nameLabel.getStyleClass().add("textLabel");

        dp.getStylesheets().add(Objects.requireNonNull(getClass().getResource(GuiAppState.cssPath)).toExternalForm());
        dp.getStyleClass().add("myDialog");

        setTitle("Add a chat");

        setResultConverter( this::formResult );

        ButtonType bt = new ButtonType("Save", ButtonBar.ButtonData.OK_DONE);
        dp.getButtonTypes().addAll(bt, ButtonType.CANCEL);
        dp.setContent(vbox);
    }

    private String formResult(ButtonType bt) {
        String chatReceiver = null;
        if( bt.getButtonData() == ButtonBar.ButtonData.OK_DONE ) {
            chatReceiver = username.getText();
        }
        return chatReceiver;
    }
}