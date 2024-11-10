package snitch.command;

import snitch.task.TaskList;
import snitch.task.Task;
import snitch.task.Deadline;
import snitch.task.Todo;
import snitch.task.Event;
import snitch.Ui;
import snitch.Storage;
import snitch.SnitchException;

/**
 * Represents a command to add a task.
 * This command handles adding Tdo, Deadline, and Event task.
 */
public class AddCommand implements Command {
    private final String fullCommand;

    /**
     * Constructs an AddCommand with the given user input.
     *
     * @param fullCommand The full user input representing the add command.
     */
    public AddCommand(String fullCommand) {
        this.fullCommand = fullCommand;
    }

    /**
     * Executes the AddCommand by adding a task to the task list.
     * Supports adding Todo, Deadline, and Event tasks based on the command type.
     *
     * @param tasks   The TaskList to which the task will be added.
     * @param ui      The Ui instance for interacting with the user.
     * @param storage The Storage instance for saving the updated task list.
     * @throws SnitchException If the command format is invalid or the task description is empty.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws SnitchException {
        if (fullCommand.startsWith("todo")) {
            String description = fullCommand.substring(5).trim();
            if (description.isEmpty()) {
                throw new SnitchException("Come on man!!! The description of a todo cannot be empty.");
            }
            tasks.add(new Todo(description));
        } else if (fullCommand.startsWith("deadline")) {
            String[] parts = fullCommand.split("/by ");
            if (parts.length < 2 || parts[0].substring(9).trim().isEmpty()) {
                throw new SnitchException("Come on man!!! The description of a deadline cannot be empty.");
            }
            String description = parts[0].substring(9).trim();
            String by = parts[1].trim();
            tasks.add(new Deadline(description, by));
        } else if (fullCommand.startsWith("event")) {
            String[] parts = fullCommand.split("/from ");
            if (parts.length < 2 || !parts[1].contains("/to ")) {
                throw new SnitchException("Invalid event format. Use: event task /from d/M/yyyy HHmm /to d/M/yyyy HHmm.");
            }
            String description = parts[0].substring(6).trim();
            if (description.isEmpty()) {
                throw new SnitchException("Come on man!!! The description of an event cannot be empty.");
            }
            String[] timeParts = parts[1].split(" /to ");
            String from = timeParts[0].trim();
            String to = timeParts[1].trim();
            tasks.add(new Event(description, from, to));
        } else {
            throw new SnitchException("Invalid command for adding tasks.");
        }

        // Display the added task
        ui.showTaskAdded(tasks.get(tasks.size() - 1), tasks.size());

        // Save tasks
        storage.save(tasks.getAllTasks());
    }
}