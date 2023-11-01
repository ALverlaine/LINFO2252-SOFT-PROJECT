package State.Commands.Contacts;

import State.Commands.CommandsDirector;
import State.DynamicCommands.Contacts.AddContact;
import State.DynamicCommands.Contacts.RemoveContact;

public class ContactsCommand extends CommandsDirector {
    @Override
    public void addAllCommand() {
        addCommand("add", new AddContact());
        addCommand("remove", new RemoveContact());
    }
}
