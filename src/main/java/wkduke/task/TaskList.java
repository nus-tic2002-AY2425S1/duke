package wkduke.task;

import java.util.ArrayList;
import java.util.List;

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

    public TaskList(List<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Adds a task to the task list.
     *
     * @param task The task to be added. Must not be {@code null}.
     */
    public void addTask(Task task) {
        assert task != null : "Precondition failed: 'task' cannot be null";
        tasks.add(task);
    }

    /**
     * Removes the specified task from the task list.
     *
     * @param task The task to be removed from the list. Must exist in the task list.
     */
    public void deleteTask(Task task) {
        assert task != null : "Precondition failed: 'task' cannot be null";
        tasks.remove(task);
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
     * Retrieves all tasks in the list.
     *
     * @return A {@code List<Task>} containing all tasks.
     */
    public List<Task> getTasks() {
        return tasks;
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
     * Retrieves the number of tasks in the list.
     *
     * @return The number of tasks in the list.
     */
    public int size() {
        return tasks.size();
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
        return tasks.equals(taskListObject.getTasks());
    }
}
