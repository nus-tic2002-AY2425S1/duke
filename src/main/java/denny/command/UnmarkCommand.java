package denny.command;

import denny.task.TaskList;
import denny.task.Task;
import denny.ui.Ui;
import denny.storage.Storage;
import denny.exception.DennyException;
import java.io.IOException;

/**
 * Command to mark a task as not completed.
 */
public class UnmarkCommand extends Command {
    private final String arguments;

    /**
     * Creates a new UnmarkCommand.
     * @param arguments String containing the task index to unmark
     */
    public UnmarkCommand(String arguments) {
        this.arguments = arguments;
    }

    /**
     * Executes the unmark command.
     * @param tasks The current list of tasks
     * @param ui The user interface for displaying output
     * @param storage The storage manager for persisting changes
     * @throws DennyException if the task index is invalid
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DennyException {
        try {
            int index = Integer.parseInt(arguments) - 1;
            Task task = tasks.getTask(index);
            task.markAsNotDone();
            ui.showTaskUnmarked(task);
            storage.saveTasks(tasks.getAllTasks());
        } catch (NumberFormatException | IndexOutOfBoundsException e) {
            throw new DennyException("Invalid task number.");
        } catch (IOException e) {
            throw new DennyException("Error saving tasks: " + e.getMessage());
        }
    }
}