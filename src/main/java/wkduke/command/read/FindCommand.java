package wkduke.command.read;

import wkduke.command.Command;
import wkduke.storage.Storage;
import wkduke.task.Task;
import wkduke.task.TaskList;
import wkduke.ui.Ui;
import wkduke.ui.UiTaskGroup;

import java.util.List;

import static wkduke.common.Messages.MESSAGE_TASK_LIST_TIPS;
import static wkduke.ui.Ui.INDENT_HELP_MSG_NUM;

/**
 * Represents a command to update the priority of a specified task in the task list.
 */
public class FindCommand extends Command {
    public static final String COMMAND_WORD = "find";
    public static final String MESSAGE_USAGE = COMMAND_WORD + " {keywords}\n"
            + "Description:".indent(INDENT_HELP_MSG_NUM)
            + "  - Search for tasks that contain specified keywords in description.".indent(INDENT_HELP_MSG_NUM)
            + "Example:".indent(INDENT_HELP_MSG_NUM)
            + "  find report, assignment".indent(INDENT_HELP_MSG_NUM)
            + "  find meeting".indent(INDENT_HELP_MSG_NUM)
            + "Constraints:".indent(INDENT_HELP_MSG_NUM)
            + "  - Multiple keywords should be separated by commas.".indent(INDENT_HELP_MSG_NUM)
            + "  - At least one keyword must be specified.".indent(INDENT_HELP_MSG_NUM);
    private static final String MESSAGE_SUCCESS = "Here are the tasks in your list with the keyword '%s':";
    private static final String MESSAGE_FAILED = "No tasks found with the keyword: %s";
    private final List<String> keywords;

    public FindCommand(List<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof FindCommand command)) {
            return false;
        }
        return keywords.equals(command.keywords);
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        assert taskList != null : "Precondition failed: 'taskList' cannot be null";
        assert ui != null : "Precondition failed: 'ui' cannot be null";
        List<Task> matchingTasks = taskList.findTasks(keywords);
        if (matchingTasks.isEmpty()) {
            ui.printMessages(String.format(MESSAGE_FAILED, keywords));
            return;
        }
        assert !matchingTasks.isEmpty() : "Postcondition failed: 'matchingTasks' cannot be empty";
        ui.printUiTaskGroup(taskList, new UiTaskGroup(
                        String.format(MESSAGE_SUCCESS, keywords), MESSAGE_TASK_LIST_TIPS, matchingTasks
                )
        );
    }
}
