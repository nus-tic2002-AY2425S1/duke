package josbot.commands;

import josbot.JosBotException;
import josbot.ui.UI;

public class InvalidCommand extends Command {

    public InvalidCommand(String comment) throws JosBotException {
        throw new JosBotException(comment);
    }
}
