package State.Models;



public class User {

    private String name;
    private String password;

    public User(String name, String password) {
        this.name = name;
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    @Override
    public String toString () {
        return "Username: " + name + "\n";
    }

    public String asMessageString() {
        return "From: " + name + " ";
    }
}