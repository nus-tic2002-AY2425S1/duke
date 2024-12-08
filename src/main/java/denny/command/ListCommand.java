package denny.command;

import denny.task.TaskList;
import denny.ui.Ui;
import denny.storage.Storage;

/**
 * Command to display all tasks in the task list.
 */
public class ListCommand extends Command {

    /**
     * Executes the list command.
     * @param tasks The current list of tasks
     * @param ui The user interface for displaying output
     * @param storage The storage manager for persisting changes
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showTaskList(tasks.getAllTasks());
    }
}
