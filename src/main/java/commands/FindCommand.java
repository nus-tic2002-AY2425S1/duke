package commands;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import common.Constants;
import exception.CommandException;
import exception.StorageOperationException;
import storage.Storage;
import task.Task;
import task.TaskList;
import ui.Ui;

/**
 * Represents a command to find tasks in a task list based on a specified description.
 * The command searches for tasks that matches the given description and displays the list to the user.
 */
public class FindCommand extends Command {

    public static final String COMMAND_WORD = "find";
    public static final String MESSAGE_USAGE = COMMAND_WORD + SPACE + OPEN_ANGLE_BRACKET + 
        Constants.DESCRIPTION + CLOSE_ANGLE_BRACKET;
    public static final String MESSAGE_EMPTY_LIST = "There are no matching tasks in your list.";
    public static final String MESSAGE_SHOW_SUCCESS_PRE = "Here are the matching tasks in your list:";

    private String description;

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
     * @return the description that this command will search for in the task list.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Executes the find command and searches for tasks in the task list that matches the given description.
     * 
     * @param taskList represents the task list to search for matching tasks descriptions.
     * @param ui represents the user interface to print messages to the user.
     * @param storage represents the storage to handle any storage operations (not used in this command).
     * @throws CommandException if an error occurs while executing the command.
     * @throws StorageOperationException if an error occurs during storage operations.
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws CommandException, StorageOperationException {
        String description = getDescription();

        List<Task> tasksWithMatchingDescription = taskList.getAllTasksWithMatchingDescription(description);

        // Check if there are no tasks with the specified description
        if (tasksWithMatchingDescription.isEmpty()) {
            String[] messages = {MESSAGE_EMPTY_LIST};
            ui.printMessage(messages);
            return;
        }

        // Prepare the message to display to the user
        ArrayList<String> messages = new ArrayList<>(Arrays.asList(MESSAGE_SHOW_SUCCESS_PRE));

        for (int i = 0; i < tasksWithMatchingDescription.size(); i++) {
            Task task = taskList.getTask(i);         // taskList.get(i) contains the checkbox
            String taskDescription = task.getDescription();
            if (taskDescription.contains(description)) {
                String index = Integer.toString(i + 1);
                String line = index + Constants.DOT_SPACE + task;
                messages.add(line);
            }
        }

        ui.printMessage(messages);

    }
    
}
