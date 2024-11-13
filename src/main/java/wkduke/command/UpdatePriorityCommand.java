package wkduke.command;

import wkduke.common.Messages;
import wkduke.exception.CommandOperationException;
import wkduke.exception.StorageOperationException;
import wkduke.storage.Storage;
import wkduke.task.Task;
import wkduke.task.TaskList;
import wkduke.task.TaskPriority;
import wkduke.ui.Ui;

/**
 * Represents a command to update the priority of a specified task in the task list.
 */
public class UpdatePriorityCommand extends Command {
    public static final String COMMAND_WORD = "update-priority";
    public static final String MESSAGE_USAGE = COMMAND_WORD + " {taskNumber} {taskPriority[L,M,H]}";
    public static final String MESSAGE_SUCCESS = "OK! I've updated the priority of this task:";
    public static final String MESSAGE_FAILED = "This task already has the specified priority:";
    public static final String TASK_PLACEHOLDER = "  %s";
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
        try {
            int taskIndex = taskNumber - 1;
            Task task = taskList.getTask(taskIndex);
            boolean isUpdated = taskList.setTaskPriority(taskIndex, priority);

            if (isUpdated) {
                storage.save(taskList);
                ui.printMessages(
                        MESSAGE_SUCCESS,
                        String.format(TASK_PLACEHOLDER, task.toString())
                );
            } else {
                ui.printMessages(
                        MESSAGE_FAILED,
                        String.format(TASK_PLACEHOLDER, task.toString())
                );
            }
        } catch (IndexOutOfBoundsException e) {
            throw new CommandOperationException(
                    Messages.MESSAGE_INVALID_TASK_NUMBER,
                    String.format("Command='%s', TaskNumber='%s' TaskPriority=''%s", COMMAND_WORD, taskNumber, priority)
            );
        }
    }
}
