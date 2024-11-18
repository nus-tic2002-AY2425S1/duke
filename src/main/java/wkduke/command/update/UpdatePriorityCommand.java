package wkduke.command.update;

import wkduke.command.Command;
import wkduke.common.Messages;
import wkduke.common.Utils;
import wkduke.exception.command.CommandOperationException;
import wkduke.exception.storage.StorageOperationException;
import wkduke.storage.Storage;
import wkduke.task.Task;
import wkduke.task.TaskList;
import wkduke.task.TaskPriority;
import wkduke.ui.Ui;
import wkduke.ui.UiTaskGroup;

import java.util.List;
import java.util.Set;

import static wkduke.ui.Ui.INDENT_HELP_MSG_NUM;

/**
 * Represents a command to update the priority of a specified task in the task list.
 */
public class UpdatePriorityCommand extends Command {
    public static final String COMMAND_WORD = "update-priority";
    public static final String MESSAGE_USAGE = COMMAND_WORD + " {task-number} {task-priority}\n"
            + "Description:".indent(INDENT_HELP_MSG_NUM)
            + "  - Updates the priority of a specific task in your task list.".indent(INDENT_HELP_MSG_NUM)
            + "Task Priority:".indent(INDENT_HELP_MSG_NUM)
            + "  L - Low priority.".indent(INDENT_HELP_MSG_NUM)
            + "  M - Medium priority.".indent(INDENT_HELP_MSG_NUM)
            + "  H - High priority.".indent(INDENT_HELP_MSG_NUM)
            + "Example:".indent(INDENT_HELP_MSG_NUM)
            + "  update-priority 3 H".indent(INDENT_HELP_MSG_NUM)
            + "  update-priority 1 L".indent(INDENT_HELP_MSG_NUM)
            + "Constraints:".indent(INDENT_HELP_MSG_NUM)
            + "  - Task number must be positive integer.".indent(INDENT_HELP_MSG_NUM)
            + "  - Task number must exist in the task list.".indent(INDENT_HELP_MSG_NUM)
            + "  - 'task-priority' must be one of: L, M, H.".indent(INDENT_HELP_MSG_NUM)
            + "  - The task's priority will be updated only if it's different.".indent(INDENT_HELP_MSG_NUM);

    private static final String MESSAGE_SUCCESS = "OK! I've updated the priority of this task:";
    private static final String MESSAGE_FAILED = "This task already has the specified priority:";
    private final int taskNumber;
    private final TaskPriority priority;

    /**
     * Constructs an UpdatePriorityCommand with the specified task number and priority.
     *
     * @param taskNumber The 1-based index of the task whose priority is to be updated.
     * @param priority   The new priority level for the task.
     */
    public UpdatePriorityCommand(int taskNumber, TaskPriority priority) {
        this.taskNumber = taskNumber;
        this.priority = priority;
    }

    /**
     * Checks if this UpdatePriorityCommand is equal to another object.
     * An UpdatePriorityCommand is considered equal if it is of the same type,
     * with the same task number and priority.
     *
     * @param obj The object to compare with this UpdatePriorityCommand.
     * @return {@code true} if the specified object is an UpdatePriorityCommand with equal task number and priority; otherwise, {@code false}.
     */
    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof UpdatePriorityCommand command)) {
            return false;
        }
        if (!priority.equals(command.priority)) {
            return false;
        }
        return taskNumber == command.taskNumber;
    }

    /**
     * Executes the update-priority command by setting the specified priority for the task,
     * saving the updated task list to storage, and displaying a success or failure message.
     *
     * @param taskList The task list containing the task to be updated.
     * @param ui       The user interface for displaying messages to the user.
     * @param storage  The storage where the updated task list will be saved.
     * @throws StorageOperationException if there is an error saving the task list to storage.
     * @throws CommandOperationException if the specified task number is invalid.
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws StorageOperationException, CommandOperationException {
        assert taskList != null : "Precondition failed: 'taskList' cannot be null";
        assert ui != null : "Precondition failed: 'ui' cannot be null";
        assert storage != null : "Precondition failed: 'storage' cannot be null";
        try {
            // Validate task numbers
            Utils.validateTaskNumbers(taskList, Set.of(taskNumber));

            // Update task priority
            int taskIndex = taskNumber - 1;
            Task task = taskList.getTask(taskIndex);
            if (priority.equals(task.getPriority())) {
                ui.printUiTaskGroup(taskList, new UiTaskGroup(String.format(MESSAGE_FAILED), "", List.of(task)));
                return;
            }
            task.setPriority(priority);

            // Save taskList to storage
            storage.save(taskList);

            // Display success messages
            ui.printUiTaskGroup(taskList, new UiTaskGroup(String.format(MESSAGE_SUCCESS), "", List.of(task)));
        } catch (IndexOutOfBoundsException e) {
            throw new CommandOperationException(
                    Messages.MESSAGE_INVALID_TASK_NUMBER,
                    String.format("Command='%s', TaskNumber='%s' TaskPriority=''%s", COMMAND_WORD, taskNumber, priority)
            );
        }
    }
}
