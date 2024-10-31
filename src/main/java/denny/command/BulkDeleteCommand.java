package denny.command;

import denny.task.TaskList;
import denny.task.Task;
import denny.ui.Ui;
import denny.storage.Storage;
import denny.exception.DennyException;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Command to delete multiple tasks from the task list.
 */
public class BulkDeleteCommand extends Command {
    private final String arguments;

    /**
     * Creates a new BulkDeleteCommand.
     * @param arguments String containing task indices to delete
     */
    public BulkDeleteCommand(String arguments) {
        this.arguments = arguments;
    }

    /**
     * Executes the bulk delete command.
     * @param tasks The current list of tasks
     * @param ui The user interface for displaying output
     * @param storage The storage manager for persisting changes
     * @throws DennyException if task indices are invalid
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DennyException {
        try {
            // Split indices by comma or space, and trim each
            List<Integer> indicesToDelete = Arrays.stream(arguments.split("[,\\s]+"))
                    .map(String::trim)
                    .map(Integer::parseInt)
                    .sorted((a, b) -> b - a) // Sort in descending order to avoid index shifting
                    .collect(Collectors.toList());

            // Validate indices
            for (Integer index : indicesToDelete) {
                if (index < 1 || index > tasks.size()) {
                    throw new IndexOutOfBoundsException("Invalid task number: " + index);
                }
            }

            // Store tasks to be deleted for output
            List<Task> deletedTasks = indicesToDelete.stream()
                    .map(index -> tasks.getTask(index - 1))
                    .collect(Collectors.toList());

            // Delete tasks in descending order to avoid index shifting
            for (Integer index : indicesToDelete) {
                tasks.deleteTask(index - 1);
            }

            // Show detailed deletion report
            ui.showBulkTasksDeleted(deletedTasks, tasks.size());
            storage.saveTasks(tasks.getAllTasks());

        } catch (NumberFormatException e) {
            throw new DennyException("Please provide valid task numbers for bulk deletion.");
        } catch (IndexOutOfBoundsException e) {
            throw new DennyException(e.getMessage());
        } catch (IOException e) {
            throw new DennyException("Error saving tasks: " + e.getMessage());
        }
    }
}