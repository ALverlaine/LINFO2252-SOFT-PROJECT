package GuiWidget;

import Features.FeatureName;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

public class FeatureButton extends Button {
    private boolean isActivated;
    FeatureName featureName;

    private static final Color activatedColor = Color.GREEN;
    private static final Color deactivatedColor = Color.RED;

    private ButtonHandler<FeatureName> clickButtonHandler;


    public interface ButtonHandler<T> {
        void handle(T event);
    }

    public void setClickHandler(ButtonHandler<FeatureName> handler) {
        this.clickButtonHandler = handler;
    }

    public FeatureButton(FeatureName featureName, boolean isActivated) {
        super(featureName.toString());
        this.featureName = featureName;
        this.isActivated = isActivated;
        setColor();
        this.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                clickButtonHandler.handle(featureName);
            }
        });
    }

    public void changeActivationStatus(boolean newStatus) {
        isActivated = newStatus;
        setColor();
    }

    private void setColor() {
        if (isActivated)
            setStyle("-fx-background-color: " + toHex(activatedColor) + ";");
        else
            setStyle("-fx-background-color: " + toHex(deactivatedColor) + ";");
    }

    private String toHex(Color color) {
        return String.format("#%02X%02X%02X",
                (int) (color.getRed() * 255),
                (int) (color.getGreen() * 255),
                (int) (color.getBlue() * 255));
    }

}
