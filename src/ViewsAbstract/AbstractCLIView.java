package ViewsAbstract;

import Exceptions.NoUserConnected;
import Features.Theme;

import java.util.List;
import java.util.Scanner;

public abstract class AbstractCLIView  {

    protected Scanner scanner = new Scanner(System.in);
    protected AbstractCLIView previousView;
    protected AbstractCLIView nextView;
    protected boolean exit = false;

    public AbstractCLIView(AbstractCLIView previousView) {
        this.previousView = previousView;
    }

    public abstract AbstractCLIView run() throws NoUserConnected;

    public void clearAll() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    public void inputNotRecognized() {
        System.out.println(Theme.BG + "Command not found, enter again");
        clearAll();
    }

    public void setPreviousView(AbstractCLIView previousView) {
        this.previousView = previousView;
    }

    public void goBack() {
        nextView = previousView;
        exit = true;
    }

    protected abstract List<String> getMenuOptions();

    public void displayOptions() {
        List<String> menuOptions = getMenuOptions();
        printMenu(menuOptions);
    }

    public void exit() {
        System.out.println(Theme.BG + "Exiting..." );
        System.exit(0);
    }

    protected void printMenu(List<String> options) {
        System.out.println(Theme.BG + "Do you want to:" );
        int optionNumber = 1;
        for (String option : options) {
            System.out.printf(Theme.BG + "(%d) %s%n" , optionNumber++, option);
        }
        System.out.println(Theme.BG + "(0) Exit");
        System.out.print(Theme.BG + "Enter the number you want to select: ");
    }
}