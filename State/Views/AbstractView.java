package State.Views;

import State.Exceptions.NoUserConnected;

import java.util.Scanner;

public abstract class AbstractView {

    Scanner scanner = new Scanner(System.in);
    AbstractView previousView;
    AbstractView nextView;
    protected boolean exit = false;

    public AbstractView(AbstractView previousView) {
        this.previousView = previousView;
    }

    public abstract AbstractView run() throws NoUserConnected;

    public void clearAll() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    public void inputNotRecognized() {
        System.out.println("Command not found, enter again");
        clearAll();
    }

    public void setPreviousView(AbstractView previousView) {
        this.previousView = previousView;
    }

    public void goBack() {
        nextView = previousView;
        exit = true;
    }

    public void exit() {
        System.out.println("Exiting...");
        System.exit(0);
    }
}
