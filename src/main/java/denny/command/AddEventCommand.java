package denny.command;

import denny.task.TaskList;
import denny.task.Task;
import denny.ui.Ui;
import denny.storage.Storage;
import denny.exception.DennyException;
import denny.task.Event;
import java.io.IOException;

/**
 * Command to add a new event task to the task list.
 */
public class AddEventCommand extends Command {
    private final String arguments;

    /**
     * Creates a new AddEventCommand.
     * @param arguments String containing the description and event times
     */
    public AddEventCommand(String arguments) {
        this.arguments = arguments;
    }

    /**
     * Executes the add event command.
     * @param tasks The current list of tasks
     * @param ui The user interface for displaying output
     * @param storage The storage manager for persisting changes
     * @throws DennyException if the format is invalid or required fields are missing
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DennyException {
        String[] parts = arguments.split(" /from | /to ");
        if (parts.length < 3) {
            throw new DennyException("Please provide the event details in the format: " +
                    "event <description> /from d/M/yyyy HHmm /to d/M/yyyy HHmm");
        }
        String description = parts[0].trim();
        String from = parts[1].trim();
        String to = parts[2].trim();
        if (description.isEmpty() || from.isEmpty() || to.isEmpty()) {
            throw new DennyException("The description, start date, and end date cannot be empty.");
        }
        try {
            Task newTask = new Event(description, from, to);
            tasks.addTask(newTask);
            ui.showTaskAdded(newTask, tasks.size());
            storage.saveTasks(tasks.getAllTasks());
        } catch (IllegalArgumentException e) {
            throw new DennyException("Invalid date format or dates. Error: " + e.getMessage() +
                    "\nPlease use the format: d/M/yyyy HHmm (e.g., 2/12/2019 1800)");
        } catch (IOException e) {
            throw new DennyException("Error saving tasks: " + e.getMessage());
        }
    }
}