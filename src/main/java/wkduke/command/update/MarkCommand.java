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
 * Represents a command to mark one or more tasks as done in the task list.
 */
public class MarkCommand extends Command {
    public static final String COMMAND_WORD = "mark";
    public static final String MESSAGE_USAGE = COMMAND_WORD + " {task-numbers...}\n"
            + "Description:".indent(INDENT_HELP_MSG_NUM)
            + "  - Marks one or more tasks as done in your task list.".indent(INDENT_HELP_MSG_NUM)
            + "  - You can provide multiple task numbers separated by commas.".indent(INDENT_HELP_MSG_NUM)
            + "Example:".indent(INDENT_HELP_MSG_NUM)
            + "  mark 1".indent(INDENT_HELP_MSG_NUM)
            + "  mark 1, 3, 5".indent(INDENT_HELP_MSG_NUM)
            + "Constraints:".indent(INDENT_HELP_MSG_NUM)
            + "  - Task numbers must be positive integers.".indent(INDENT_HELP_MSG_NUM)
            + "  - Task numbers must exist in the task list.".indent(INDENT_HELP_MSG_NUM)
            + "  - Duplicate task numbers will be ignored.".indent(INDENT_HELP_MSG_NUM)
            + "  - Tasks already marked as done will be skipped with a message.".indent(INDENT_HELP_MSG_NUM);
    private static final String MESSAGE_SUCCESS = "Nice! I've marked these tasks as done:";
    private static final String MESSAGE_FAILED = "These tasks is already marked as done:";
    private final Set<Integer> taskNumbers;

    /**
     * Constructs a MarkCommand with the specified task numbers.
     *
     * @param taskNumbers A list of 1-based index representing the tasks to be marked as done (Duplicates will be ignored).
     */
    public MarkCommand(List<Integer> taskNumbers) {
        this.taskNumbers = new HashSet<>(taskNumbers);
    }

    /**
     * Updates the status of tasks as done, categorising them into successfully updated tasks and tasks
     * that were already marked as done.
     *
     * @param taskList           The task list containing the tasks.
     * @param updatedTasks       A list to store tasks successfully marked as done.
     * @param alreadyMarkedTasks A list to store tasks that were already marked as done.
     */
    private void updateTaskStatus(TaskList taskList, List<Task> updatedTasks, List<Task> alreadyMarkedTasks) {
        for (Integer taskNumber : taskNumbers) {
            int taskIndex = taskNumber - 1;
            Task task = taskList.getTask(taskIndex);

            boolean isUpdated = taskList.markTask(taskIndex);
            if (isUpdated) {
                updatedTasks.add(task);
            } else {
                alreadyMarkedTasks.add(task);
            }
        }
    }

    /**
     * Checks if this MarkCommand is equal to another object.
     * A MarkCommand is considered equal if it is of the same type and has the same task number.
     *
     * @param obj The object to compare with this MarkCommand.
     * @return {@code true} if the specified object is a MarkCommand with an equal task number; otherwise, {@code false}.
     */
    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof MarkCommand command)) {
            return false;
        }
        return taskNumbers.equals(command.taskNumbers);
    }

    /**
     * Executes the mark command by marking the specified tasks as done, saving the updated
     * task list to storage, and displaying a success or failure message.
     *
     * @param taskList The task list containing the task to be marked as done.
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
            List<Task> alreadyMarkedTasks = new ArrayList<>();
            updateTaskStatus(taskList, updatedTasks, alreadyMarkedTasks);

            // Save taskList to storage
            if (!updatedTasks.isEmpty()) {
                storage.save(taskList);
            }

            // Display success and failure messages
            ui.printUiTaskGroups(taskList, List.of(
                    new UiTaskGroup(MESSAGE_SUCCESS, "", updatedTasks),
                    new UiTaskGroup(MESSAGE_FAILED, "", alreadyMarkedTasks)
            ));
        } catch (IndexOutOfBoundsException e) {
            throw new CommandOperationException(
                    Messages.MESSAGE_INVALID_TASK_NUMBER,
                    String.format("Command='mark', TaskNumber='%s'", taskNumbers),
                    e.getMessage()
            );
        }
    }
}
