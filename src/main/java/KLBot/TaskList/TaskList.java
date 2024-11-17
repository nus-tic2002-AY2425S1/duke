package KLBot.TaskList;

import java.util.ArrayList;
import java.util.List;

/**
 * The TaskList class manages a collection of tasks. It allows tasks to be added, removed,
 * and retrieved. It also provides methods to check the size of the list and whether the list is empty.
 */
public class TaskList {
    private ArrayList<Task> tasks;

    /**
     * Constructs a new TaskList.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Adds a single task to the TaskList.
     *
     * @param task The task to be added to the list.
     */
    public void addTask(Task task) {
        tasks.add(task);
    }

    /**
     * Adds multiple tasks to the TaskList.
     *
     * @param tasks The list of tasks to be added to the TaskList.
     */
    public void addTasks(ArrayList<Task> tasks) {
        this.tasks.addAll(tasks);
    }

    /**
     * Removes the task at the specified index from the TaskList.
     *
     * @param index The index of the task to be removed.
     */
    public void removeTask(int index) {
        tasks.remove(index);
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
     * Returns the number of tasks in the TaskList.
     *
     * @return The number of tasks in the list.
     */
    public int size() {
        return tasks.size();
    }

    /**
     * Checks whether the TaskList is empty.
     *
     * @return true if the TaskList contains no tasks.
     */
    public boolean isEmpty() {
        return tasks.isEmpty();
    }

    /**
     * Searches for tasks whose descriptions contain the given keyword.
     *
     * @param keyword The keyword to search for in task descriptions.
     * @return A list of tasks whose descriptions contain the keyword.
     */
    public List<Task> searchTasks(String keyword) {
        //Reference: Example 2 in https://www.tutorialspoint.com/how-to-find-an-element-in-a-list-with-java
        List<Task> matchingTasks = new ArrayList<>();
        for (Task task : tasks) {
            if (task.getDescription().toLowerCase().contains(keyword.toLowerCase())) {
                matchingTasks.add(task);
            }
        }
        return matchingTasks;
    }
}
