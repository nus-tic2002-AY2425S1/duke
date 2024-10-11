package denny.command;

import denny.task.TaskList;
import denny.task.Task;
import denny.ui.Ui;
import denny.storage.Storage;
import denny.exception.DennyException;
import denny.task.Event;
import java.io.IOException;

public class AddEventCommand extends Command {
    private final String arguments;

    public AddEventCommand(String arguments) {
        this.arguments = arguments;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DennyException {
        String[] parts = arguments.split(" /from | /to ");
        if (parts.length < 3) {
            throw new DennyException("Please provide the event details in the format: event <description> /from <start date> /to <end date>");
        }
        String description = parts[0].trim();
        String from = parts[1].trim();
        String to = parts[2].trim();
        if (description.isEmpty() || from.isEmpty() || to.isEmpty()) {
            throw new DennyException("The description, start date, and end date cannot be empty.");
        }
        Task newTask = new Event(description, from, to);
        tasks.addTask(newTask);
        ui.showTaskAdded(newTask, tasks.size());
        try {
            storage.saveTasks(tasks.getAllTasks());
        } catch (IOException e) {
            throw new DennyException("Error saving tasks: " + e.getMessage());
        }
    }
}