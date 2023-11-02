package State.Views;

public class ViewsController {
    AbstractView currentView;

    public ViewsController(AbstractView firstView) {
        this.currentView = firstView;
    }
}
