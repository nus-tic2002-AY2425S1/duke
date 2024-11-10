package snitch.command;

import snitch.task.TaskList;
import snitch.task.Task;
import snitch.Ui;
import snitch.Storage;
import snitch.SnitchException;

/**
 * Represents a command to delete a task from the task list.
 */
public class DeleteCommand implements Command {
    private final int taskIndex;

    /**
     * Constructs a DeleteCommand with the specified task number.
     *
     * @param taskIndex The 1-based index of the task to be deleted.
     */
    public DeleteCommand(int taskIndex) {
        this.taskIndex = taskIndex - 1; // Convert to 0-based index
    }

    /**
     * Executes the DeleteCommand by removing the specified task from the TaskList.
     * Displays a confirmation message and saves the updated task list to storage.
     *
     * @param tasks   The TaskList from which the task will be deleted.
     * @param ui      The Ui instance for interacting with the user.
     * @param storage The Storage instance for saving the updated task list.
     * @throws SnitchException If the task number is invalid.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws SnitchException {
        try {
            Task taskToDelete = tasks.remove(taskIndex);
            ui.showTaskRemoved(taskToDelete, tasks.size());
            storage.save(tasks.getAllTasks());
        } catch (IndexOutOfBoundsException e) {
            throw new SnitchException("Invalid task number.");
        }
    }
}