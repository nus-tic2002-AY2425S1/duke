package wkduke.task;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Manages a list of tasks, providing methods to add, delete, and query tasks.
 */
public class TaskList {
    public final List<Task> tasks;

    /**
     * Constructs an empty {@code TaskList}.
     */
    public TaskList() {
        tasks = new ArrayList<>();
    }

    /**
     * Adds a task to the list.
     *
     * @param task The task to add.
     */
    public void addTask(Task task) {
        tasks.add(task);
    }

    /**
     * Deletes a task at the specified index from the list.
     *
     * @param index The index of the task to delete.
     */
    public void deleteTask(int index) {
        tasks.remove(index);
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

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof TaskList taskListObject)) {
            return false;
        }
        return tasks.equals(taskListObject.getAllTask());
    }
}
