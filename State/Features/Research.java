package State.Features;

public class Research extends Feature{

    public Research() {
        super();
        deactivate();
    }

    @Override
    public void logout() {
        deactivate();
    }
}
