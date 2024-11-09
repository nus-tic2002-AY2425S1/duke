package wkduke.command;

import wkduke.parser.TimeParser;
import wkduke.storage.Storage;
import wkduke.task.Task;
import wkduke.task.TaskList;
import wkduke.ui.Ui;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents a command to list all tasks occurring on a specified date.
 */
public class ListOnCommand extends Command {
    public static final String COMMAND_WORD = "list";
    public static final String MESSAGE_USAGE = COMMAND_WORD + " /on {dateTime}";
    public static final String MESSAGE_SUCCESS = "Here are the tasks in your list on '%s':";
    public static final String MESSAGE_FAILED = "Your task list is currently empty on '%s'.";
    protected LocalDateTime on;

    /**
     * Constructs a ListOnCommand with the specified date and time to filter tasks.
     *
     * @param on The date and time for which tasks should be listed.
     */
    public ListOnCommand(LocalDateTime on) {
        this.on = on;
    }

    /**
     * Checks if this ListOnCommand is equal to another object.
     * A ListOnCommand is considered equal if it is of the same type and has the same date for listing tasks.
     *
     * @param obj The object to compare with this ListOnCommand.
     * @return {@code true} if the specified object is a ListOnCommand with an equal date; otherwise, {@code false}.
     */
    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof ListOnCommand command)) {
            return false;
        }
        return on.equals(command.on);
    }

    /**
     * Executes the list command by retrieving all tasks occurring on the specified date.
     * Displays the list of tasks or a message if no tasks are found.
     *
     * @param taskList The task list containing all tasks.
     * @param ui       The user interface for displaying messages to the user.
     * @param storage  The storage being used (not used in this command).
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        List<Task> tasks = taskList.getAllTaskOnDate(on);
        if (tasks.isEmpty()) {
            ui.printMessages(
                    String.format(MESSAGE_FAILED, on.format(TimeParser.CLI_FORMATTER))
            );
            return;
        }

        List<String> messages = new ArrayList<>();
        messages.add(
                String.format(MESSAGE_SUCCESS, on.format(TimeParser.CLI_FORMATTER))
        );
        for (Task task : tasks) {
            messages.add(String.format("%d.%s", taskList.getTaskIndex(task) + 1, task));
        }
        ui.printMessages(messages.toArray(new String[0]));
    }
}
