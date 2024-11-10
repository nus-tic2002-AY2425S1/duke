package snitch.command;

import snitch.task.TaskList;
import snitch.Ui;
import snitch.Storage;

/**
 * Represents a command to list all tasks in the task list.
 */
public class ListCommand implements Command {

    /**
     * Executes the ListCommand by displaying all tasks in the TaskList.
     * If the TaskList is empty, an error message is shown.
     *
     * @param tasks   The TaskList containing the tasks to be listed.
     * @param ui      The Ui instance for interacting with the user.
     * @param storage The Storage instance (not used in this command).
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        if (tasks.isEmpty()) {
            ui.showError("Your task list is empty.");
        } else {
            ui.showTasks(tasks);
        }
    }
}