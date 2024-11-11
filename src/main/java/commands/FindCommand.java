package commands;

import common.Constants;
import storage.Storage;
import task.Task;
import task.TaskList;
import ui.Ui;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a command to find tasks in a task list based on a specified description.
 * The command searches for tasks that matches the given description and displays the list to the user.
 */
public class FindCommand extends Command {

    public static final String COMMAND_WORD = "find";
    public static final String MESSAGE_USAGE = COMMAND_WORD + SPACE + Constants.DESCRIPTION_IN_ANGLE_BRACKETS;
    private static final String MESSAGE_EMPTY_LIST = "There are no matching tasks in your list.";
    private static final String MESSAGE_SHOW_SUCCESS_PRE = "Here are the matching tasks in your list:";

    private final String description;

    /**
     * Constructs a FindCommand with the given description.
     *
     * @param description represents the description to search for in the task list.
     */
    public FindCommand(String description) {
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

        String taskDescription = getDescription();
        List<Task> tasksWithMatchingDescription = taskList.getAllTasksWithMatchingDescription(taskDescription);

        // Check if there are no tasks with the specified description
        if (tasksWithMatchingDescription.isEmpty()) {
            String[] messages = {MESSAGE_EMPTY_LIST};
            ui.printMessage(messages);
            return;
        }

        // Prepare the message to display to the user
        ArrayList<String> messages = new ArrayList<>(List.of(MESSAGE_SHOW_SUCCESS_PRE));

        for (int i = 0; i < tasksWithMatchingDescription.size(); i++) {
            Task currentTask = taskList.getTask(i);         // taskList.get(i) contains the checkbox
            String currentTaskDescription = currentTask.getDescription();
            if (currentTaskDescription.contains(taskDescription)) {
                String index = Integer.toString(i + 1);
                String line = index + Constants.DOT_SPACE + currentTask;
                messages.add(line);
            }
        }

        ui.printMessage(messages);

    }

}
