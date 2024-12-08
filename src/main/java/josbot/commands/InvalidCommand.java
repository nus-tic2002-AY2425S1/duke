package josbot.commands;

import josbot.JosBotException;

/**
 * This Command is initialised when user executes am invalid command
 */

public class InvalidCommand extends Command {

    /**
     *
     * Used by other Command child classes when there is an error detected
     *
     * @param comment An error comment type
     * @throws JosBotException this is exception will be caught at JosBot
     */
    public InvalidCommand(String comment) throws JosBotException {
        throw new JosBotException(comment);
    }
}
