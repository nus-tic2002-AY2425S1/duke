package denny.command;

import denny.task.TaskList;
import denny.task.Task;
import denny.ui.Ui;
import denny.storage.Storage;
import denny.exception.DennyException;
import denny.task.ToDo;
import java.io.IOException;

/**
 * Command to add a new todo task to the task list.
 */
public class AddTodoCommand extends Command {
    private final String description;

    /**
     * Creates a new AddTodoCommand.
     * @param description Description of the todo task
     */
    public AddTodoCommand(String description) {
        this.description = description;
    }

    /**
     * Executes the add todo command.
     * @param tasks The current list of tasks
     * @param ui The user interface for displaying output
     * @param storage The storage manager for persisting changes
     * @throws DennyException if the task description is empty
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DennyException {
        if (description.isEmpty()) {
            throw new DennyException("The description of a todo cannot be empty.");
        }
        Task newTask = new ToDo(description);
        tasks.addTask(newTask);
        ui.showTaskAdded(newTask, tasks.size());
        try {
            storage.saveTasks(tasks.getAllTasks());
        } catch (IOException e) {
            throw new DennyException("Error saving tasks: " + e.getMessage());
        }
    }
}
