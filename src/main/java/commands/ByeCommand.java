package commands;

import storage.Storage;
import task.TaskList;
import ui.Ui;

/**
 * Represents a command to terminate the program.
 * The ByeCommand class is responsible for executing the action of terminating the application.
 * When executed, it communicates with the user interface to display a goodbye message.
 * This command does not modify the {@link TaskList} or {@link Storage}.
 */
public class ByeCommand extends Command {

    public static final String COMMAND_WORD = "bye";

    /**
     * Executes the command by displaying a goodbye message to the user.
     *
     * @param taskList represents the current list of tasks. (not used in this command)
     * @param ui       represents the user interface to handle interactions with the user.
     * @param storage  represents the storage manager to handle interactions with the user.
     *                 (reading from and writing to) the task file. (not used in this command)
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        ui.showBye();
    }

    /**
     * Flags to indicate whether the command is a "bye" command.
     *
     * @return true because this command indicates the termination of the program.
     */
    @Override
    public boolean isBye() {
        return true;
    }
}
