package State.UI;
import java.util.Scanner;

public class Input {
    private static Input instance;
    private final Scanner scanner;

    private Input() { this.scanner = new Scanner(System.in); }
    private Input(String[] commands) {
        StringBuilder allCMDs = new StringBuilder();
        for (String command : commands) { allCMDs.append(command).append("\n"); }
        this.scanner = new Scanner(allCMDs.toString());
    }

    public static void setInput(String[] commands) { if (instance == null) { instance = new Input(commands); } }
    public static void setInput() { if (instance == null) { instance = new Input(); } }

    public static Scanner getScanner() {
        setInput();
        return instance.scanner;
    }

    public static String getInputs() {
        setInput();
        return instance.scanner.nextLine();
    }
}

