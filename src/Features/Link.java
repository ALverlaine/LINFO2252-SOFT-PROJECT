package Features;

public class Link extends Feature {

    @Override
    public void logout() {
        deactivate();
    }
}
