package wkduke.command.delete;

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

/**
 * Represents a command to delete a tasks from the task list.
 */
public class DeleteCommand extends Command {
    public static final String COMMAND_WORD = "delete";
    public static final String MESSAGE_USAGE = COMMAND_WORD + " {taskNumber1, taskNumber2...} (Note: duplicates will be ignored)";
    private static final String MESSAGE_SUCCESS_PRE = "Noted. I've removed these tasks:";
    private static final String MESSAGE_SUCCESS_POST = "Now you have %s tasks in the list.";
    private final Set<Integer> taskNumbers;

    /**
     * Constructs a DeleteCommand with the specified task numbers.
     *
     * @param taskNumbers A list of 1-based index representing the tasks to be marked as done (Duplicates will be ignored).
     */
    public DeleteCommand(List<Integer> taskNumbers) {
        this.taskNumbers = new HashSet<>(taskNumbers);
    }

    /**
     * Deletes tasks specified in the taskNumbers list from the taskList and adds them to the deletedTasks list.
     *
     * @param taskList     The task list from which tasks will be deleted.
     * @param deletedTasks A list to store the deleted tasks for reference.
     */
    private void deleteTasks(TaskList taskList, List<Task> deletedTasks) {
        for (Integer taskNumber : taskNumbers) {
            int taskIndex = taskNumber - 1;
            Task task = taskList.getTask(taskIndex);
            deletedTasks.add(task);
        }

        for (Task task : deletedTasks) {
            taskList.deleteTask(task);
        }
    }

    /**
     * Checks if this DeleteCommand is equal to another object.
     * A DeleteCommand is considered equal if it is of the same type and has the same task number.
     *
     * @param obj The object to compare with this DeleteCommand.
     * @return {@code true} if the specified object is a DeleteCommand and has an equal task number; otherwise, {@code false}.
     */
    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof DeleteCommand command)) {
            return false;
        }
        return taskNumbers.equals(command.taskNumbers);
    }

    /**
     * Executes the delete command by removing the specified task from the task list,
     * saving the updated list to storage, and displaying a success message.
     *
     * @param taskList The task list from which the task will be deleted.
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
            List<Task> deletedTasks = new ArrayList<>();
            deleteTasks(taskList, deletedTasks);

            // Save taskList to storage
            if (!deletedTasks.isEmpty()) {
                storage.save(taskList);
            }

            // Display success and failure messages
            ui.printUiTaskGroup(taskList, new UiTaskGroup(
                            MESSAGE_SUCCESS_PRE, String.format(MESSAGE_SUCCESS_POST, taskList.size()), deletedTasks
                    )
            );
        } catch (IndexOutOfBoundsException e) {
            throw new CommandOperationException(
                    Messages.MESSAGE_INVALID_TASK_NUMBER,
                    String.format("Command='unmark', TaskNumber='%s'", taskNumbers),
                    e.getMessage()
            );
        }
    }
}
