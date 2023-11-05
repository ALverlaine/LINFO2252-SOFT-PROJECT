package State.Views;

import State.Controllers.AuthController;
import State.Exceptions.IncorrectPassword;
import State.Exceptions.UserDoesntExist;

import java.util.Objects;
import java.util.Scanner;

public class AuthView extends AbstractView {

    private final AuthController controller;

    boolean authSuccessful = false;
    public AuthView() {
        super();
        controller = new AuthController(this);
    }

    @Override
    public void run() {
        while (!authSuccessful) {
            try {
                printAuthChoice();
                int command = Integer.parseInt(scanner.nextLine());
                controller.parseAuthInput(command);
            }
            catch (NumberFormatException input) {
                System.out.println("The input is badly formatted");
            }
        }
    }

    public void printAuthChoice() {
        System.out.print("""
                        Do you want to :\s
                         (1) Login\s
                         (2) Register\s
                         Enter the number you want to select:  """);

    }

    public void login() {
        System.out.print("Enter username: ");
        String username = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();

        controller.login(username, password);
    }

    public void register() {
        System.out.print("Enter username: ");
        String username = scanner.nextLine();

        System.out.print("Enter password: ");
        String password = scanner.nextLine();

        System.out.print("Confirm password: ");
        String confirmPassword = scanner.nextLine();

        if (Objects.equals(password, confirmPassword)) controller.register(username, password);
        else System.out.println("The passwords don't match");
    }



    public void authSuccessful() {
        authSuccessful = true;

        System.out.println("You are now connected!");
        ViewsController homepage;
        homepage = new ViewsController();
        homepage.displayOption();
        //ICI ON SORT DE LOGIN
        // Go to homepage
    }

    public void loginUnsuccessful(IncorrectPassword e) {
        System.out.println("Wrong password!");
    }

    public void loginUnsuccessful(UserDoesntExist e) {
        System.out.println("The username doesn't exist!");
    }

    public void registerUnsuccessful() {
        System.out.println("The user already exists!");
    }

    public void exit() {
        //Kill the app
    }


}

