package Views;

import Controllers.AuthController;
import Exceptions.IncorrectPassword;
import Exceptions.NoUserConnected;
import Exceptions.UserDoesntExist;
import Features.Theme;
import Models.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public class AuthView extends AbstractView {

    private final AuthController controller;

    public AuthView(AbstractView previousView) {
        super(previousView);
        controller = new AuthController(this);
    }

    @Override
    public AbstractView run() {
        controller.reset();
        previousView = this;
        while (!exit) {
            try {
                displayOptions();
                int command = Integer.parseInt(scanner.nextLine());
                controller.parseAuthInput(command);
            }
            catch (NumberFormatException input) {
                System.out.println(Theme.BG + "The input is badly formatted" );
                scanner = new Scanner(System.in);
            }
        }
        exit = false;
        return nextView;
    }

    @Override
    protected List<String> getMenuOptions() {
        return new ArrayList<>(List.of(
                "Login",
                "Register"
        ));
    }


    public void login() {
        System.out.print(Theme.BG + "Enter username: " );
        String username = scanner.nextLine();
        System.out.print(Theme.BG + "Enter password: " );
        String password = scanner.nextLine();

        controller.login(username, password);
    }

    public void register() {
        System.out.print(Theme.BG + "Enter username: ");
        String username = scanner.nextLine();

        System.out.print(Theme.BG + "Enter password: ");
        String password = scanner.nextLine();

        System.out.print(Theme.BG + "Confirm password: " );
        String confirmPassword = scanner.nextLine();

        if (Objects.equals(password, confirmPassword)) controller.register(username, password);
        else System.out.println(Theme.BG + "The passwords don't match");
    }

    public void authSuccessful() {
        exit = true;
        nextView = new MenuView(this);
        System.out.println(Theme.BG + "You are now connected!" );
    }

    public void loginUnsuccessful(IncorrectPassword e) {
        System.out.println(Theme.BG + "Wrong password!" );
    }

    public void loginUnsuccessful(UserDoesntExist e) {
        System.out.println(Theme.BG +"The username doesn't exist!" );
    }

    public void registerUnsuccessful() {
        System.out.println(Theme.BG + "The user already exists!" );
    }

    public void exit() {
        System.exit(0);
    }


}

