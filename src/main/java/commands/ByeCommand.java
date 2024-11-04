package commands;

import storage.Storage;
import task.TaskList;
import ui.Ui;

/**
 * Represents a command to terminate the program.
 * 
 * <p>
 * The ByeCommand class is responsible for executing the action of terminating the application. 
 * When executed, it communicates with the user interface to display a goodbye message.
 * </p>
 * 
 * <p>
 * This command does not modify the {@link TaskList} or {@link Storage}.
 * </p>
 */
public class ByeCommand extends Command {
    
    public static final String COMMAND_WORD = "bye";
    
    /**
     * Executes the command by displaying a goodbye message to the user.
     * 
     * @param taskList The current list of tasks. (not used in this command)
     * @param ui The user interface to handle interactions with the user.
     * @param storage The storage manager to handle interactions with (reading from and writing to) the task file. (not used in this command)
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        ui.showBye();
    }

    /**
     * Flag to indicate whether the command is a "bye" command.
     * 
     * @return true because this command indicates the termination of the program.
     */
    @Override
    public boolean isBye() {
        return true;
    }
}
