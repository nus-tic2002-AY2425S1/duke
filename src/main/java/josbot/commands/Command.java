package josbot.commands;

import josbot.JosBotException;
import josbot.storage.FileStorage;
import josbot.task.TaskList;
import josbot.ui.UI;

/**
 * Command is the parent class used to identify the command executed by the user and relevant errors generated.
 */

public class Command {

    protected String commandType;
    protected String description;
    protected boolean isExit;


    public Command() {
    }
    /**
     *
     * Takes in the type and description of the command and storing it.
     *
     * @param commandType The type of Command (e.g. Todo , Deadline and more ..)
     * @param description The description of the command
     */
    public Command(String commandType, String description) {
        this.commandType = commandType;
        this.description = description;
        this.isExit = false;
    }

    public void execute(TaskList tasks, UI ui, FileStorage file) throws JosBotException {}

    /**
     * Return the description of the command
     *
     * @return description of the command
     */
    public String getDescription() {
        return description;
    }

    /**
     * Return boolean of isExit value
     *
     * @return isExit
     */

    public boolean isExit() {
        return isExit;
    }
}