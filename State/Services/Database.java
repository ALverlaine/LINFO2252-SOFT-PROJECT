package State.Services;

import State.Models.User;
import State.State;
public class Database {
    public static void populateUsersDB(State state) {
        User a = new User("a", "a");
        User b = new User("b", "b");
        User c = new User("c", "c");
        User d = new User("d", "d");
        state.addUser(a);
        state.addUser(b);
        state.addUser(c);
        state.addUser(d);

    }

}
