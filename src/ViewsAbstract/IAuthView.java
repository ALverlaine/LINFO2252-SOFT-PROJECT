package ViewsAbstract;

import Exceptions.IncorrectPassword;
import Exceptions.UserDoesntExist;

public interface IAuthView {
    void authSuccessful();
    void authError(Exception e);
}
