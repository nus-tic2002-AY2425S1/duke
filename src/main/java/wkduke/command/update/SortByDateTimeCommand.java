package wkduke.command.update;

import wkduke.exception.storage.StorageOperationException;
import wkduke.storage.Storage;
import wkduke.task.Task;
import wkduke.task.TaskList;
import wkduke.task.TimeAware;
import wkduke.ui.Ui;
import wkduke.ui.UiTaskGroup;

import java.util.List;

/**
 * A command to sort tasks in the task list by date and time.
 */
public class SortByDateTimeCommand extends SortCommand {
    /**
     * Constructs a {@code SortByDateTimeCommand} with the specified sort order.
     *
     * @param sortOrder The sort order (ascending or descending).
     */
    public SortByDateTimeCommand(SortOrder sortOrder) {
        super(sortOrder);
    }

    /**
     * Sorts tasks in the task list by datetime.
     * Non-time-aware tasks are placed at the bottom, and time-aware tasks are sorted based on their datetime.
     *
     * @param taskList The task list to sort.
     */
    // Solution below inspired by https://stackoverflow.com/questions/5927109/sort-objects-in-arraylist-by-date
    private void sortTaskByDateTime(TaskList taskList) {
        List<Task> tasks = taskList.getTasks();
        tasks.sort((t1, t2) -> {
            boolean t1TimeAware = t1 instanceof TimeAware;
            boolean t2TimeAware = t2 instanceof TimeAware;

            // Handle cases where one or both tasks are not time-aware
            if (!t1TimeAware && !t2TimeAware) {
                return 0; // Both are not time aware, keep order unchanged
            } else if (!t1TimeAware) {
                return 1; // t1 is non-time-aware, place it after t2
            } else if (!t2TimeAware) {
                return -1; // t2 is non-time-aware, place it after t1
            }

            // Both are time aware, compare their datetime
            TimeAware task1 = (TimeAware) t1;
            TimeAware task2 = (TimeAware) t2;
            int comparison = task1.getComparableDateTime().compareTo(task2.getComparableDateTime());
            return (sortOrder == SortOrder.ASCENDING) ? comparison : -comparison;
        });
    }

    /**
     * Checks if this {@code SortByDateTimeCommand} is equal to another object.
     * Two commands are considered equal if they have the same sorting order.
     *
     * @param obj The object to compare with this command.
     * @return {@code true} if the specified object is a {@code SortByDateTimeCommand} with the same sort order; {@code false} otherwise.
     */
    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof SortByDateTimeCommand command)) {
            return false;
        }
        return sortOrder.equals(command.sortOrder);
    }

    /**
     * Executes the sort command by sorting tasks in the task list by date time, saving the updated list,
     * and displaying the sorted tasks.
     *
     * @param taskList The task list to sort.
     * @param ui       The user interface to display the sorted tasks.
     * @param storage  The storage to save the sorted task list.
     * @throws StorageOperationException If there is an error saving the task list.
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws StorageOperationException {
        sortTaskByDateTime(taskList);
        storage.save(taskList);
        ui.printUiTaskGroup(taskList, new UiTaskGroup(
                String.format(MESSAGE_SUCCESS, "datetime", sortOrder), "", taskList.getTasks()));
    }
}
