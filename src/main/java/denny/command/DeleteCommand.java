package denny.command;

import denny.task.TaskList;
import denny.task.Task;
import denny.ui.Ui;
import denny.storage.Storage;
import denny.exception.DennyException;
import java.io.IOException;

/**
 * Command to delete a task from the task list.
 */
public class DeleteCommand extends Command {
    private final String arguments;

    /**
     * Creates a new DeleteCommand.
     * @param arguments String containing the task index to delete
     */
    public DeleteCommand(String arguments) {
        this.arguments = arguments;
    }

    /**
     * Executes the delete command.
     * @param tasks The current list of tasks
     * @param ui The user interface for displaying output
     * @param storage The storage manager for persisting changes
     * @throws DennyException if the task index is invalid
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DennyException {
        try {
            int index = Integer.parseInt(arguments) - 1;
            Task deletedTask = tasks.getTask(index);
            tasks.deleteTask(index);
            ui.showTaskDeleted(deletedTask, tasks.size());
            storage.saveTasks(tasks.getAllTasks());
        } catch (NumberFormatException | IndexOutOfBoundsException e) {
            throw new DennyException("Invalid task number.");
        } catch (IOException e) {
            throw new DennyException("Error saving tasks: " + e.getMessage());
        }
    }
}
