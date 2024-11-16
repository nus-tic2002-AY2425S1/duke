package wkduke.task;

import wkduke.command.update.SortOrder;
import wkduke.exception.command.CommandOperationException;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import static wkduke.common.Messages.MESSAGE_DUPLICATE_TASK;
import static wkduke.common.Messages.MESSAGE_DUPLICATE_TASK_HELP;

/**
 * Manages a list of tasks, providing methods to add, delete, and query tasks.
 */
public class TaskList {
    private final List<Task> tasks;

    /**
     * Constructs an empty {@code TaskList}.
     */
    public TaskList() {
        tasks = new ArrayList<>();
    }

    /**
     * Adds a task to the task list.
     *
     * @param task The task to be added. Must not be {@code null}.
     * @throws CommandOperationException if the task already exists in the list.
     */
    public void addTask(Task task) throws CommandOperationException {
        assert task != null : "Precondition failed: 'task' cannot be null";
        if (tasks.contains(task)) {
            throw new CommandOperationException(
                    MESSAGE_DUPLICATE_TASK,
                    String.format("Task='%s'", task),
                    MESSAGE_DUPLICATE_TASK_HELP
            );
        }
        tasks.add(task);
    }

    /**
     * Removes the specified task from the task list.
     *
     * @param task The task to be removed from the list. Must exist in the task list.
     */
    public void deleteTask(Task task) {
        tasks.remove(task);
    }

    /**
     * Finds tasks whose descriptions contain any of the specified keywords.
     *
     * @param keywords A list of keywords to search for in task descriptions.
     * @return A list of tasks containing at least one of the keywords in their descriptions.
     */
    public List<Task> findTasks(List<String> keywords) {
        List<Task> matchingTasks = new ArrayList<>();
        for (Task task : tasks) {
            for (String keyword : keywords) {
                if (task.getDescription().contains(keyword)) {
                    matchingTasks.add(task);
                }
            }
        }
        return matchingTasks;
    }

    /**
     * Retrieves all tasks in the list.
     *
     * @return A {@code List<Task>} containing all tasks.
     */
    public List<Task> getAllTask() {
        return tasks;
    }

    /**
     * Returns a list of tasks scheduled on the specified date.
     * Only tasks implementing {@code TimeAware} and occurring on the specified date are included.
     *
     * @param targetDateTime The date to check against.
     * @return A list of {@code Task} objects occurring on the specified date, or an empty list if none match.
     */
    public List<Task> getAllTaskOnDate(LocalDateTime targetDateTime) {
        assert targetDateTime != null : "Precondition failed: 'targetDateTime' cannot be null";
        List<Task> tasks = new ArrayList<>();
        for (Task task : this.tasks) {
            if (!(task instanceof TimeAware timeAwareTask)) {
                continue;
            }
            if (timeAwareTask.isOccursOnDate(targetDateTime)) {
                tasks.add(task);
            }
        }
        return tasks;
    }

    /**
     * Retrieves the task at the specified index.
     *
     * @param index The index of the task to retrieve.
     * @return The task at the specified index.
     */
    public Task getTask(int index) {
        return tasks.get(index);
    }

    /**
     * Retrieves the index of a specified task.
     *
     * @param task The task to find.
     * @return The index of the task, or {@code -1} if it is not found.
     */
    public int getTaskIndex(Task task) {
        return tasks.indexOf(task);
    }

    /**
     * Checks if the task list is empty.
     *
     * @return {@code true} if the task list is empty; {@code false} otherwise.
     */
    public boolean isEmpty() {
        return tasks.isEmpty();
    }

    /**
     * Marks the task at the specified index as done.
     *
     * @param index The index of the task to mark as done.
     * @return {@code true} if the task was successfully marked as done; {@code false} if it was already done.
     */
    public boolean markTask(int index) {
        Task task = getTask(index);
        if (task.isDone()) {
            return false;
        }
        task.markAsDone();
        return true;
    }

    /**
     * Sets the priority level of the task at the specified index in the task list.
     *
     * @param index    The index of the task whose priority is to be set.
     * @param priority The new {@code TaskPriority} to set for the task.
     * @return {@code true} if the priority was successfully updated; {@code false}
     * if the task already has the specified priority.
     */
    public boolean setTaskPriority(int index, TaskPriority priority) {
        Task task = getTask(index);
        if (priority.equals(task.getPriority())) {
            return false;
        }
        task.setPriority(priority);
        return true;
    }

    /**
     * Retrieves the number of tasks in the list.
     *
     * @return The number of tasks in the list.
     */
    public int size() {
        return tasks.size();
    }

    /**
     * Sorts tasks in the task list by datetime.
     * Non-time-aware tasks are placed at the bottom, and time-aware tasks are sorted based on their datetime.
     *
     * @param sortOrder The sorting order ({@code ASCENDING} or {@code DESCENDING}).
     */
    // Solution below inspired by https://stackoverflow.com/questions/5927109/sort-objects-in-arraylist-by-date
    public void sortTaskByDateTime(SortOrder sortOrder) {
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
     * Sorts tasks in the task list by priority.
     *
     * @param sortOrder The sorting order ({@code ASCENDING} or {@code DESCENDING}).
     */
    // Solution below inspired by https://medium.com/@mgautham1995/order-a-java-list-using-a-custom-comparison-order-for-enum-69565e741236
    public void sortTaskByPriority(SortOrder sortOrder) {
        tasks.sort((sortOrder == SortOrder.ASCENDING) ? Comparator.comparing(Task::getPriority)
                : Comparator.comparing(Task::getPriority).reversed());
    }

    /**
     * Sorts tasks in the task list by type.
     *
     * @param sortOrder The sorting order ({@code ASCENDING} or {@code DESCENDING}).
     */
    // Solution below inspired by https://medium.com/@mgautham1995/order-a-java-list-using-a-custom-comparison-order-for-enum-69565e741236
    public void sortTaskByType(SortOrder sortOrder) {
        tasks.sort((sortOrder == SortOrder.ASCENDING) ? Comparator.comparing(Task::getType)
                : Comparator.comparing(Task::getType).reversed());
    }

    /**
     * Marks the task at the specified index as not done.
     *
     * @param index The index of the task to mark as not done.
     * @return {@code true} if the task was successfully marked as not done; {@code false} if it was already not done.
     */
    public boolean unmarkTask(int index) {
        Task task = getTask(index);
        if (!task.isDone()) {
            return false;
        }
        task.markAsUndone();
        return true;
    }

    /**
     * Checks if this {@code TaskList} is equal to another object.
     * Two {@code TaskList} objects are considered equal if their task lists contain the same tasks in the same order.
     *
     * @param obj The object to compare with this {@code TaskList}.
     * @return {@code true} if the specified object is a {@code TaskList} and contains the same tasks;
     * {@code false} otherwise.
     */
    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof TaskList taskListObject)) {
            return false;
        }
        return tasks.equals(taskListObject.getAllTask());
    }
}
