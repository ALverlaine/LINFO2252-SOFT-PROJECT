package GuiWidget;

import Utils.GuiApp;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;

import java.util.Objects;

public class ResearchDialog extends Dialog<String> {

    private final TextField toSearch = new TextField();

    public ResearchDialog() {
        Label nameLabel = new Label("Search a message");

        VBox vbox = new VBox(
                nameLabel, toSearch
        );

        vbox.setSpacing( 10.0d );

        DialogPane dp = getDialogPane();

        nameLabel.getStylesheets().add(Objects.requireNonNull(getClass().getResource(GuiApp.cssPath)).toExternalForm());
        nameLabel.getStyleClass().add("textLabel");

        dp.getStylesheets().add(Objects.requireNonNull(getClass().getResource(GuiApp.cssPath)).toExternalForm());
        dp.getStyleClass().add("myDialog");

        setTitle("Research a message");

        setResultConverter( this::formResult );

        ButtonType bt = new ButtonType("Search", ButtonBar.ButtonData.OK_DONE);
        dp.getButtonTypes().addAll(bt, ButtonType.CANCEL);
        dp.setContent(vbox);
    }

    private String formResult(ButtonType bt) {
        String chatReceiver = null;
        if( bt.getButtonData() == ButtonBar.ButtonData.OK_DONE ) {
            chatReceiver = toSearch.getText();
        }
        return chatReceiver;
    }
}