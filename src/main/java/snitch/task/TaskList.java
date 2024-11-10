package snitch.task;

import java.util.ArrayList;

/**
 * Represents a list of tasks.
 * Provides methods to add, retrieve, and remove tasks.
 */
public class TaskList {
    private final ArrayList<Task> tasks;

    /**
     * Constructs an empty TaskList.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Constructs a TaskList initialized with an existing list of tasks.
     *
     * @param tasks An ArrayList of tasks to initialize the TaskList with.
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Adds a task to the TaskList.
     *
     * @param task The task to be added.
     */
    public void add(Task task) {
        tasks.add(task);
    }

    /**
     * Retrieves a task from the TaskList by its index.
     *
     * @param index The index of the task to retrieve.
     * @return The task at the specified index.
     * @throws IndexOutOfBoundsException If the index is out of range.
     */
    public Task get(int index) {
        return tasks.get(index);
    }

    /**
     * Removes a task from the TaskList by its index.
     *
     * @param index The index of the task to remove.
     * @return The removed task.
     * @throws IndexOutOfBoundsException If the index is out of range.
     */
    public Task remove(int index) {
        return tasks.remove(index);
    }

    /**
     * Returns the number of tasks in the TaskList.
     *
     * @return The size of the TaskList.
     */
    public int size() {
        return tasks.size();
    }

    /**
     * Checks if the TaskList is empty.
     *
     * @return True if the TaskList is empty, false otherwise.
     */
    public boolean isEmpty() {
        return tasks.isEmpty();
    }

    /**
     * Returns the underlying list of tasks.
     *
     * @return An ArrayList containing all tasks.
     */
    public ArrayList<Task> getAllTasks() {
        return tasks;
    }
}