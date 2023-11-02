package State.Models;

public record User(String name, String password) {
    @Override
    public String toString() {
        return "Username: " + name + "\n";
    }
}
