package State.Users.User;

import State.Users.Users;

public class User extends Users {
    public String name;
    public String password;
    int ID;
    public User(String name, String password){
        this.name = name;
        this.password = password;
        this.ID = generateID();
    }

}
