package Views;

import Exceptions.NoUserConnected;

import java.util.Scanner;

public class ViewsController {
    Scanner scanner = new Scanner(System.in);

    public ViewsController() {
    }


    public void run() throws NoUserConnected {
        AbstractView view = new AuthView(null);
        while (true) {
            view = view.run();
        }
    }
}
