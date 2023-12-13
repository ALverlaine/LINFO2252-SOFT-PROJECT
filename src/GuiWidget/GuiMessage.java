package GuiWidget;

import Models.Message;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;

import java.io.IOException;

public class GuiMessage extends HBox {
    private Message message;
    @FXML
    Text messageContent;
    public GuiMessage(Message message, boolean fromUser) throws IOException {
        if (fromUser)
            setAlignment(Pos.CENTER_RIGHT);
        else
            setAlignment(Pos.CENTER_LEFT);
        Text text = new Text(message.getContent());
        TextFlow textFlow = new TextFlow(text);

        textFlow.setStyle("-fx-background-color: rgb(233,233,235);" +
                "-fx-background-radius: 20px");
        textFlow.setPadding(new Insets(5, 10, 5, 10));

        getChildren().add(textFlow);

    }
}
