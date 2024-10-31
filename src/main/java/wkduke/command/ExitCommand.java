package wkduke.command;

import wkduke.storage.Storage;
import wkduke.task.TaskList;
import wkduke.ui.Ui;

/**
 * Represents a command to exit the application.
 */
public class ExitCommand extends Command {
    public static final String COMMAND_WORD = "bye";

    /**
     * Executes the exit command by displaying a goodbye message to the user.
     *
     * @param taskList The task list being managed (not used in this command).
     * @param ui       The user interface to interact with the user.
     * @param storage  The storage being used (not used in this command).
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        ui.showGoodbyeMessage();
    }

    /**
     * Indicates that this command will terminate the application.
     *
     * @return {@code true} to signal the application to exit.
     */
    @Override
    public boolean isExit() {
        return true;
    }
}
