package State;

public class Command {
    private boolean state;
    public Command(){
        this.state = true;
    }

     public boolean isActivated(){
         return this.state;
     }
}

