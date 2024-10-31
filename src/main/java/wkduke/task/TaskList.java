package wkduke.task;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Manages a list of tasks, providing methods to add, delete, and query tasks.
 */
public class TaskList {
    public final List<Task> taskList;

    /**
     * Constructs an empty {@code TaskList}.
     */
    public TaskList() {
        taskList = new ArrayList<>();
    }

    /**
     * Constructs a {@code TaskList} with the given list of tasks.
     *
     * @param taskList The initial list of tasks.
     */
    public TaskList(List<Task> taskList) {
        this.taskList = taskList;
    }

    /**
     * Adds a task to the list.
     *
     * @param task The task to add.
     */
    public void addTask(Task task) {
        taskList.add(task);
    }

    /**
     * Deletes a task at the specified index from the list.
     *
     * @param index The index of the task to delete.
     */
    public void deleteTask(int index) {
        taskList.remove(index);
    }

    /**
     * Retrieves all tasks in the list.
     *
     * @return A {@code List<Task>} containing all tasks.
     */
    public List<Task> getAllTask() {
        return taskList;
    }

    /**
     * Retrieves all tasks that occur on the specified date.
     *
     * @param targetDateTime The date to check against.
     * @return A {@code List<Task>} containing tasks scheduled on the specified date.
     */
    public List<Task> getAllTaskOnDate(LocalDateTime targetDateTime) {
        List<Task> result = new ArrayList<>();

        for (Task task : taskList) {
            if (task.isOnDate(targetDateTime)) {
                result.add(task);
            }
        }
        return result;
    }

    /**
     * Retrieves the task at the specified index.
     *
     * @param index The index of the task to retrieve.
     * @return The task at the specified index.
     */
    public Task getTask(int index) {
        return taskList.get(index);
    }

    /**
     * Retrieves the index of a specified task.
     *
     * @param task The task to find.
     * @return The index of the task, or {@code -1} if it is not found.
     */
    public int getTaskIndex(Task task) {
        return taskList.indexOf(task);
    }

    /**
     * Checks if the task list is empty.
     *
     * @return {@code true} if the task list is empty; {@code false} otherwise.
     */
    public boolean isEmpty() {
        return taskList.isEmpty();
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
     * Retrieves the number of tasks in the list.
     *
     * @return The number of tasks in the list.
     */
    public int size() {
        return taskList.size();
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
}
