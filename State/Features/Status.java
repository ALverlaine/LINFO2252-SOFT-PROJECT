package State.Features;

public class Status extends Feature {
    enum Activity {
        Online,
        Offline;
    }

    Activity currentActivity = Activity.Offline;

    @Override
    public void activate() {
        super.activate();
        goOnline();
    }

    public void goOnline() {
        currentActivity = Activity.Online;

    }

    public void goOffline() {
        currentActivity = Activity.Offline;
    }

    @Override
    public void logout() {
        goOffline();
    }

    public Activity getCurrentActivity() {
        return currentActivity;
    }
    @Override
    public String toString() {
        return currentActivity.toString();
    }
}
