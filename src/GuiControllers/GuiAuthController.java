package GuiControllers;

import ControllersAbstract.AbstractAuthController;
import GUI.GuiLoginViewController;
import GuiInterfaces.Views.IAppViewController;
import ViewsAbstract.IAuthView;

public class GuiAuthController extends AbstractAuthController {

    private final IAuthView view;

    public GuiAuthController(IAuthView view) {
        super(view);
        this.view = view;
    }

}
