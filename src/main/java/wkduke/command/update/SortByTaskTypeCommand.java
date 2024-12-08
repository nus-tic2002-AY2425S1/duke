package wkduke.command.update;

import wkduke.exception.storage.StorageOperationException;
import wkduke.storage.Storage;
import wkduke.task.Task;
import wkduke.task.TaskList;
import wkduke.ui.Ui;
import wkduke.ui.UiTaskGroup;

import java.util.Comparator;
import java.util.List;

/**
 * A command to sort tasks in the task list by task type.
 */
public class SortByTaskTypeCommand extends SortCommand {
    /**
     * Constructs a {@code SortByTaskTypeCommand} with the specified sort order.
     *
     * @param sortOrder The sort order (ascending or descending).
     */
    public SortByTaskTypeCommand(SortOrder sortOrder) {
        super(sortOrder);
    }

    /**
     * Sorts tasks in the task list by type.
     *
     * @param taskList The task list to sort.
     */
    // Solution below inspired by https://medium.com/@mgautham1995/order-a-java-list-using-a-custom-comparison-order-for-enum-69565e741236
    private void sortTaskByType(TaskList taskList) {
        List<Task> tasks = taskList.getTasks();
        tasks.sort((sortOrder == SortOrder.ASCENDING) ? Comparator.comparing(Task::getType)
                : Comparator.comparing(Task::getType).reversed());
    }

    /**
     * Checks if this {@code SortByTaskTypeCommand} is equal to another object.
     * Two commands are considered equal if they have the same sorting order.
     *
     * @param obj The object to compare with this command.
     * @return {@code true} if the specified object is a {@code SortByTaskTypeCommand} with the same sort order; {@code false} otherwise.
     */
    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof SortByTaskTypeCommand command)) {
            return false;
        }
        return sortOrder.equals(command.sortOrder);
    }

    /**
     * Executes the sort command by sorting tasks in the task list by task type, saving the updated list,
     * and displaying the sorted tasks.
     *
     * @param taskList The task list to sort.
     * @param ui       The user interface to display the sorted tasks.
     * @param storage  The storage to save the sorted task list.
     * @throws StorageOperationException If there is an error saving the task list.
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws StorageOperationException {
        sortTaskByType(taskList);
        storage.save(taskList);
        ui.printUiTaskGroup(taskList, new UiTaskGroup(
                String.format(MESSAGE_SUCCESS, "tasktype", sortOrder), "", taskList.getTasks()));
    }
}
