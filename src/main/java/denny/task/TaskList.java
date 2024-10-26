package denny.task;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Manages a collection of tasks and provides operations to manipulate them.
 */
public class TaskList {
    private List<Task> tasks;

    /**
     * Creates a new TaskList with the provided list of tasks.
     * @param tasks Initial list of tasks
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public TaskList(List<Task> tasks) {
        this.tasks = new ArrayList<>(tasks);
    }

    /**
     * Adds a new task to the list.
     * @param task Task to be added
     */
    public void addTask(Task task) {
        tasks.add(task);
    }

    /**
     * Deletes a task at the specified index.
     * @param index Index of the task to delete
     */
    public void deleteTask(int index) {
        tasks.remove(index);
    }

    /**
     * Gets a task at the specified index.
     * @param index Index of the task to retrieve
     * @return The task at the specified index
     */
    public Task getTask(int index) {
        return tasks.get(index);
    }

    /**
     * Gets the total number of tasks in the list.
     * @return Number of tasks
     */
    public int size() {
        return tasks.size();
    }

    /**
     * Gets a copy of all tasks in the list.
     * @return List containing all tasks
     */
    public List<Task> getAllTasks() {
        return new ArrayList<>(tasks);
    }

    // New methods for date-based operations
    /**
     * Retrieves tasks scheduled for a specific date.
     * @param date The date to check for tasks
     * @return List of tasks scheduled for the specified date
     */
    public List<Task> getTasksOnDate(LocalDateTime date) {
        return tasks.stream()
                .filter(task -> {
                    if (task instanceof Deadline) {
                        LocalDateTime deadlineDate = ((Deadline) task).getBy();
                        return isSameDate(deadlineDate, date);
                    } else if (task instanceof Event) {
                        LocalDateTime eventStart = ((Event) task).getFrom();
                        LocalDateTime eventEnd = ((Event) task).getTo();
                        return isSameDate(eventStart, date) || isSameDate(eventEnd, date);
                    }
                    return false;
                })
                .collect(Collectors.toList());
    }

    private boolean isSameDate(LocalDateTime date1, LocalDateTime date2) {
        return date1.toLocalDate().equals(date2.toLocalDate());
    }
}