package wkduke.command.update;

import wkduke.command.Command;
import wkduke.common.Messages;
import wkduke.common.Utils;
import wkduke.exception.command.CommandOperationException;
import wkduke.exception.storage.StorageOperationException;
import wkduke.storage.Storage;
import wkduke.task.Task;
import wkduke.task.TaskList;
import wkduke.ui.Ui;
import wkduke.ui.UiTaskGroup;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static wkduke.ui.Ui.INDENT_HELP_MSG_NUM;

/**
 * Represents a command to unmark one or more tasks as not done in the task list.
 */
public class UnmarkCommand extends Command {
    public static final String COMMAND_WORD = "unmark";
    public static final String MESSAGE_USAGE = COMMAND_WORD + " {task-numbers...}\n"
            + "Description:".indent(INDENT_HELP_MSG_NUM)
            + "  - Marks one or more tasks as 'not done' in your task list.".indent(INDENT_HELP_MSG_NUM)
            + "  - You can provide multiple task numbers separated by commas.".indent(INDENT_HELP_MSG_NUM)
            + "Example:".indent(INDENT_HELP_MSG_NUM)
            + "  unmark 1".indent(INDENT_HELP_MSG_NUM)
            + "  unmark 1, 3, 5".indent(INDENT_HELP_MSG_NUM)
            + "Constraints:".indent(INDENT_HELP_MSG_NUM)
            + "  - Task numbers must be positive integers.".indent(INDENT_HELP_MSG_NUM)
            + "  - Task numbers must exist in the task list.".indent(INDENT_HELP_MSG_NUM)
            + "  - Duplicate task numbers will be ignored.".indent(INDENT_HELP_MSG_NUM)
            + "  - Tasks not marked as done yet will be skipped with a message.".indent(INDENT_HELP_MSG_NUM);
    private static final String MESSAGE_SUCCESS = "OK, I've marked these tasks as not done yet:";
    private static final String MESSAGE_FAILED = "These tasks is not yet marked as done:";
    private final Set<Integer> taskNumbers;

    /**
     * Constructs an UnmarkCommand with the specified task numbers.
     *
     * @param taskNumbers A list of 1-based index representing the tasks to be marked as done (Duplicates will be ignored).
     */
    public UnmarkCommand(List<Integer> taskNumbers) {
        this.taskNumbers = new HashSet<>(taskNumbers);
    }

    /**
     * Updates the status of tasks as not done, categorising them into successfully updated tasks and tasks
     * that were already in the "not done" status.
     *
     * @param taskList            The task list containing the tasks.
     * @param updatedTasks        A list to store tasks successfully marked as not done.
     * @param alreadyNotDoneTasks A list to store tasks that were already in the "not done" status.
     */
    private void updateTaskStatus(TaskList taskList, List<Task> updatedTasks, List<Task> alreadyNotDoneTasks) {
        for (Integer taskNumber : taskNumbers) {
            int taskIndex = taskNumber - 1;
            Task task = taskList.getTask(taskIndex);

            boolean isUpdated = taskList.unmarkTask(taskIndex);
            if (isUpdated) {
                updatedTasks.add(task);
            } else {
                alreadyNotDoneTasks.add(task);
            }
        }
    }

    /**
     * Checks if this UnmarkCommand is equal to another object.
     * An UnmarkCommand is considered equal if it is of the same type and has the same task number.
     *
     * @param obj The object to compare with this UnmarkCommand.
     * @return {@code true} if the specified object is an UnmarkCommand with an equal task number; otherwise, {@code false}.
     */
    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof UnmarkCommand command)) {
            return false;
        }
        return taskNumbers.equals(command.taskNumbers);
    }

    /**
     * Executes the unmark command by marking the specified tasks as not done, saving the updated
     * task list to storage, and displaying a success or failure message.
     *
     * @param taskList The task list containing the task to be marked as not done.
     * @param ui       The user interface for displaying messages to the user.
     * @param storage  The storage where the updated task list will be saved.
     * @throws StorageOperationException if there is an error with saving the task list to storage.
     * @throws CommandOperationException if the specified task number is invalid.
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws StorageOperationException, CommandOperationException {
        assert taskList != null : "Precondition failed: 'taskList' cannot be null";
        assert ui != null : "Precondition failed: 'ui' cannot be null";
        assert storage != null : "Precondition failed: 'storage' cannot be null";
        try {
            // Validate task numbers
            Utils.validateTaskNumbers(taskList, taskNumbers);

            // Update task statuses
            List<Task> updatedTasks = new ArrayList<>();
            List<Task> alreadyNotDoneTasks = new ArrayList<>();
            updateTaskStatus(taskList, updatedTasks, alreadyNotDoneTasks);

            // Save taskList to storage
            if (!updatedTasks.isEmpty()) {
                storage.save(taskList);
            }

            // Display success and failure messages
            ui.printUiTaskGroups(taskList, List.of(
                    new UiTaskGroup(MESSAGE_SUCCESS, "", updatedTasks),
                    new UiTaskGroup(MESSAGE_FAILED, "", alreadyNotDoneTasks)
            ));
        } catch (IndexOutOfBoundsException e) {
            throw new CommandOperationException(
                    Messages.MESSAGE_INVALID_TASK_NUMBER,
                    String.format("Command='unmark', TaskNumber='%s'", taskNumbers),
                    e.getMessage()
            );
        }
    }
}
