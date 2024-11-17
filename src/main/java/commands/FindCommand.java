package commands;

import common.Constants;
import common.Messages;
import storage.Storage;
import task.TaskList;
import ui.Ui;

/**
 * Represents a command to find tasks in a task list based on a specified description.
 * The command searches for tasks that matches the given description and displays the list to the user.
 */
public class FindCommand extends Command {

    public static final String COMMAND_WORD = "find";

    public static final String MESSAGE_USAGE = COMMAND_WORD + SPACE + Constants.DESCRIPTION_IN_ANGLE_BRACKETS;
    private static final String MATCHING_TASKS = "matching tasks";
    private static final String MESSAGE_EMPTY_LIST =
        "There are no" + Constants.SPACE + MATCHING_TASKS + Messages.MESSAGE_IN_YOUR_LIST + Constants.DOT;
    private static final String MESSAGE_SHOW_SUCCESS_PRE =
        Messages.HERE_ARE_THE + SPACE + MATCHING_TASKS + Messages.MESSAGE_IN_YOUR_LIST_COLON;

    private final String description;

    /**
     * Constructs a FindCommand with the given description.
     *
     * @param description represents the description to search for in the task list.
     */
    public FindCommand(String description) {
        assert description != null && !description.trim().isEmpty() : "Description cannot be null or empty";
        this.description = description;
    }

    /**
     * Returns the description associated with the FindCommand.
     *
     * @return the description that this command will search for in the task list.
     */
    private String getDescription() {
        return description;
    }

    /**
     * Executes the find command and searches for tasks in the task list that matches the given description.
     *
     * @param taskList represents the task list to search for matching tasks descriptions.
     * @param ui       represents the user interface to print messages to the user.
     * @param storage  represents the storage to handle any storage operations (not used in this command).
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        assertExecuteParams(taskList, ui, storage);

        String taskDescription = getDescription();
        TaskList tasksWithMatchingDescription = taskList.getAllTasksWithMatchingDescription(taskDescription);

        ui.printTaskListMessage(tasksWithMatchingDescription, MESSAGE_EMPTY_LIST, MESSAGE_SHOW_SUCCESS_PRE);

    }

}
