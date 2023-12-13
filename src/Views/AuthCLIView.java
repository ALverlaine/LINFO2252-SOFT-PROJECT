package Views;

import CliControllers.CliAuthController;
import Exceptions.IncorrectPassword;
import Exceptions.UserDoesntExist;
import Features.Theme;
import ViewsAbstract.IAuthView;
import ViewsAbstract.AbstractCLIView;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;


public class AuthCLIView extends AbstractCLIView implements IAuthView {

    private final CliAuthController controller;

    public AuthCLIView(AbstractCLIView previousView) {
        super(previousView);
        controller = new CliAuthController(this);
    }

    @Override
    public AbstractCLIView run() {
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
        nextView = new MenuCLIView(this);
        System.out.println(Theme.BG + "You are now connected!" );
    }

    @Override
    public void authError(Exception e) {
        System.out.println(Theme.BG + "Error logging you in" );
    }


    public void exit() {
        System.exit(0);
    }


}

