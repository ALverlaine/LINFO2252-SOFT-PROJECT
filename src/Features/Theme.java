package Features;
import java.time.LocalTime;

public class Theme extends Feature {
    public static String BG;

    public static final String RESET = "\033[0m";  // Text Reset
    public static final String BLACK_BACKGROUND = "\033[40m";  // BLACK
    public static final String WHITE_BACKGROUND = "\033[47m";  // WHITE

    public static void determineBackgroundColor(LocalTime currentTime) {
        int startHour = 10;
        int endHour = 17;

        int currentHour = currentTime.getHour();

        // Set white background if the current time is between 10:00 and 17:00
        if (currentHour >= startHour && currentHour < endHour) {
            BG = WHITE_BACKGROUND;
        } else {
            // Otherwise, set black background
            BG = BLACK_BACKGROUND;
        }
    }


    @Override
    public void logout() {

    }
}
