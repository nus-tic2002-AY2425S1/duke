package denny.command;

import denny.task.TaskList;
import denny.task.Task;
import denny.ui.Ui;
import denny.storage.Storage;
import denny.exception.DennyException;
import denny.task.Deadline;
import java.io.IOException;

public class AddDeadlineCommand extends Command {
    private final String arguments;

    public AddDeadlineCommand(String arguments) {
        this.arguments = arguments;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DennyException {
        String[] parts = arguments.split(" /by ", 2);
        if (parts.length < 2) {
            throw new DennyException("Please provide the deadline in the format: deadline <description> /by <date>");
        }
        String description = parts[0].trim();
        String by = parts[1].trim();
        if (description.isEmpty() || by.isEmpty()) {
            throw new DennyException("The description and deadline cannot be empty.");
        }
        Task newTask = new Deadline(description, by);
        tasks.addTask(newTask);
        ui.showTaskAdded(newTask, tasks.size());
        try {
            storage.saveTasks(tasks.getAllTasks());
        } catch (IOException e) {
            throw new DennyException("Error saving tasks: " + e.getMessage());
        }
    }
}
