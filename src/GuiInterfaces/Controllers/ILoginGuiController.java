package GuiInterfaces.Controllers;

public interface ILoginGuiController extends IController{
    void login(String username, String password);
    void goToSignUp();
}
