package State.Views;

import java.util.Scanner;

public abstract class AbstractView {

    Scanner scanner = new Scanner(System.in);

    public AbstractView() {}

    public abstract void run();

    public void clearAll() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    public void inputNotRecognized() {
        System.out.println("Command not found, enter again");
        clearAll();
    }

}
