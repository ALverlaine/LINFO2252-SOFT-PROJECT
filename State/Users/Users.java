package State.Users;

import State.Users.User.User;

import java.util.ArrayList;

public class Users {
    public ArrayList<User> users = new ArrayList<User>();
    public Users(){
    }

    public int generateID(){
        return getNumUsers() + 1;
    }
    public int getNumUsers(){
        return users.size();
    }
    public boolean exists(User user){
        return users.contains(user);
    }


}
