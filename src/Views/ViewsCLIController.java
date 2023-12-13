package Views;

import Exceptions.NoUserConnected;
import ViewsAbstract.AbstractCLIView;

import java.util.Scanner;

public class ViewsCLIController {
    Scanner scanner = new Scanner(System.in);

    public ViewsCLIController() {
    }


    public void run() throws NoUserConnected {
        AbstractCLIView view = new AuthCLIView(null);
        while (true) {
            view = view.run();
        }
    }
}
