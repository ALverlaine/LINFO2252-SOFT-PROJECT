package State.Views;

public abstract class AbstractView {
    public AbstractView() {}
    public abstract void run();
    public void clearAll() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
}
