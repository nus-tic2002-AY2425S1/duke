package snitch.task;

import snitch.command.FuzzySearch;

import java.util.ArrayList;
import java.util.stream.Collectors;

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
        assert this.tasks != null : "Task list should be initialized as non-null";
    }

    /**
     * Constructs a TaskList initialized with an existing list of tasks.
     *
     * @param tasks An ArrayList of tasks to initialize the TaskList with.
     */
    public TaskList(ArrayList<Task> tasks) {
        assert tasks != null : "Provided task list should not be null";
        this.tasks = tasks;
    }

    /**
     * Adds a task to the TaskList.
     *
     * @param task The task to be added.
     */
    public void add(Task task) {
        assert task != null : "Task to add should not be null";
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
        assert index >= 0 && index < tasks.size() : "Index out of bounds for get method";
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
        assert index >= 0 && index < tasks.size() : "Index out of bounds for remove method";
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
        assert tasks != null : "Task list should not be null when fetching all tasks";
        return tasks;
    }

    /**
     * Finds tasks whose descriptions approximately match the specified keyword.
     *
     * @param keyword The keyword to search for.
     * @return A TaskList containing tasks that partially match the keyword.
     */
    public TaskList findTasksContaining(String keyword) {
        assert keyword != null && !keyword.isEmpty() : "Search keyword should not be null or empty";

        final int MAX_DISTANCE = 2;

        ArrayList<Task> matchingTasks = tasks.stream()
                .filter(task -> {
                    String description = task.getDescription().toLowerCase();
                    return description.contains(keyword.toLowerCase()) ||
                            FuzzySearch.levenshteinRecursive(description, keyword.toLowerCase(), description.length(), keyword.length()) <= MAX_DISTANCE;
                })
                .collect(Collectors.toCollection(ArrayList::new));
        return new TaskList(matchingTasks);
    }
}