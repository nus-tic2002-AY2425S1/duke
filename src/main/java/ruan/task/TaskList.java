package ruan.task;

import java.util.ArrayList;
import ruan.exception.ErrorType;
import ruan.exception.RuanException;

/**
 * Represents a list of tasks and provides various operations to manipulate the tasks
 * It allows adding, deleting, and marking tasks as done or not done
 */

public class TaskList {
    private ArrayList<Task> tasks;    
    
    /**
     * Constructs an empty TaskList
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Constructs a TaskList with an existing list of tasks
     * @param tasks The list of tasks to initialize the TaskList
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }
    
    /**
     * Adds a task to the TaskList
     * @param task The task to add to the list
     */
    public void addTask(Task task) {
        tasks.add(task);
    }

    /**
     * Deletes a task from the TaskList at the specified index
     * @param index Index of the task to be deleted
     * @throws RuanException If the index is invalid (i.e., out of bounds)
     */
    public void deleteTask(int index) throws RuanException {
        if (index < 0 || index >= tasks.size()) {
            throw new RuanException(ErrorType.INVALID_TASK_NUMBER);
        }

        tasks.remove(index);
    }

     /**
     * Sets the completion status of a task in the TaskList
     * @param index Index of the task to update
     * @param isDone True if the task is being marked as done
     * @throws RuanException If the index is invalid (i.e., out of bounds)
     */
    public void setDone(int index, boolean isDone) throws RuanException {
        if (index < 0 || index >= tasks.size()) {
            throw new RuanException(ErrorType.INVALID_TASK_NUMBER);
        }
        tasks.get(index).setDone(isDone);
    }

    /**
     * Gets the list of tasks.
     * @return The ArrayList containing all tasks in the TaskList
     */
    public ArrayList<Task> getTasks() {
        return tasks;
    }

    /**
     * Gets the description of a task at a specified index
     * @param index Index of the task
     * @return String representation of the task
     */
    public String getTaskDescription(int index) {
        return tasks.get(index).toString();
    }

    /**
     * Gets the number of tasks in TaskList
     * @return Size of the TaskList
     */
    public int size() {
        return tasks.size();
    }

    /**
     * Check if there is a duplicate task in TaskList
     * @return True if there is duplicated task
     */
    public boolean isDuplicate(Task task) {
        for (Task t : tasks) {
            if (t.equals(task)) {
                return true;
            }
        }
        return false;
    }

}
