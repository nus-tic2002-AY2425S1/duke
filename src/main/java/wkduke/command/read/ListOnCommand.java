package wkduke.command.read;

import wkduke.command.Command;
import wkduke.parser.TimeParser;
import wkduke.storage.Storage;
import wkduke.task.Task;
import wkduke.task.TaskList;
import wkduke.task.TimeAware;
import wkduke.ui.Ui;
import wkduke.ui.UiTaskGroup;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static wkduke.common.Messages.MESSAGE_TASK_LIST_TIPS;
import static wkduke.ui.Ui.INDENT_HELP_MSG_NUM;

/**
 * Represents a command to list all tasks occurring on a specified date.
 */
public class ListOnCommand extends Command {
    private static final String COMMAND_WORD = "list";
    public static final String MESSAGE_USAGE = COMMAND_WORD + " /on {datetime}\n"
            + "Description:".indent(INDENT_HELP_MSG_NUM)
            + "  - Lists all tasks in your task list. Optionally, filter tasks scheduled for a specific date.".indent(INDENT_HELP_MSG_NUM)
            + "Format:".indent(INDENT_HELP_MSG_NUM)
            + "  list".indent(INDENT_HELP_MSG_NUM)
            + "  list /on {dateTime}".indent(INDENT_HELP_MSG_NUM)
            + TimeParser.MESSAGE_USAGE
            + "Example:".indent(INDENT_HELP_MSG_NUM)
            + "  list".indent(INDENT_HELP_MSG_NUM)
            + "  list /on 2024-11-05".indent(INDENT_HELP_MSG_NUM)
            + "Constraints:".indent(INDENT_HELP_MSG_NUM)
            + "  - If /on is omitted, all tasks will be listed.".indent(INDENT_HELP_MSG_NUM)
            + "  - If /on is provided, only time aware tasks occurring on the".indent(INDENT_HELP_MSG_NUM)
            + "    specified date will be listed.".indent(INDENT_HELP_MSG_NUM);

    private static final String MESSAGE_SUCCESS = "Here are the tasks in your list on '%s':";
    private static final String MESSAGE_FAILED = "Your task list is currently empty on '%s'.";
    private final LocalDateTime on;

    /**
     * Constructs a ListOnCommand with the specified date and time to filter tasks.
     *
     * @param on The date and time for which tasks should be listed.
     */
    public ListOnCommand(LocalDateTime on) {
        this.on = on;
    }

    /**
     * Finds all tasks in the task list that occur on the specified date.
     *
     * @param taskList The task list to search.
     * @return A list of tasks occurring on the specified date.
     */
    private List<Task> findOnDateTasks(TaskList taskList) {
        List<Task> matchingTasks = new ArrayList<>();
        for (Task task : taskList.getTasks()) {
            if (task instanceof TimeAware timeAwareTask && timeAwareTask.isOccursOnDate(on)) {
                matchingTasks.add(task);
            }
        }
        return matchingTasks;
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
        assert taskList != null : "Precondition failed: 'taskList' cannot be null";
        assert ui != null : "Precondition failed: 'ui' cannot be null";

        List<Task> matchingTasks = findOnDateTasks(taskList);
        if (matchingTasks.isEmpty()) {
            ui.printMessages(String.format(MESSAGE_FAILED, on.format(TimeParser.CLI_DATE_FORMATTER)));
            return;
        }
        assert !matchingTasks.isEmpty() : "Postcondition failed: 'tasks' cannot be empty";
        ui.printUiTaskGroup(taskList, new UiTaskGroup(String.format(MESSAGE_SUCCESS,
                on.format(TimeParser.CLI_DATE_FORMATTER)), MESSAGE_TASK_LIST_TIPS, matchingTasks)
        );
    }
}
