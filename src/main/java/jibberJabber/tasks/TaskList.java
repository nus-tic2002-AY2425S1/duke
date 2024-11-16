package jibberJabber.tasks;

import jibberJabber.ui.Message;

import java.util.ArrayList;
/**
 * The task list class represents a list of tasks in the task management system.
 * Provides methods to manage tasks such as adding, removing, and retrieving tasks.
 */
public class TaskList {
    private final ArrayList<Task> todoTaskList;
    /**
     * Constructs an empty TaskList.
     */
    public TaskList() {
        this.todoTaskList = new ArrayList<>();
    }
    /**
     * Retrieves the list of all tasks within the array list
     *
     * @return the ArrayList containing all tasks in the TaskList.
     */
    public ArrayList<Task> getTasks() {
        return todoTaskList;
    }
    /**
     * Returns the total number of tasks in the list.
     *
     * @return the total number of tasks.
     */
    public int getTotalTaskCount() {
        return todoTaskList.size();
    }
    /**
     * Retrieves the last task added to the list.
     *
     * @return the last task in the list, or null if the list is empty.
     */
    public Task getLastTask() {
        if (todoTaskList.isEmpty()){
            return null;
        }
        return todoTaskList.get(todoTaskList.size() - 1);
    }
    /**
     * Retrieves a task by index.
     *
     * @param id the index of the task to retrieve.
     * @return the task at the specified index.
     */
    public Task getTaskById(int id) {
        return todoTaskList.get(id);
    }
    /**
     * Adds a new task to the list.
     *
     * @param task the task to add.
     */
    public void addTask(Task task) {
        todoTaskList.add(task);
    }
    /**
     * Removes a task from the list by index.
     *
     * @param index the index of the task to remove.
     * @return the removed task.
     */
    public Task removeTask(int index) {
        return todoTaskList.remove(index);
    }
    /**
     * Prints all tasks in the list in the defaulted formatted manner.
     */
    public void printTaskList() {
        Message.printHorizontalLines();
        System.out.println("Here are the tasks in your list:");
        // Using totalNumberOfTodoTask as the source of truth to determine the number of task objects created
        for (int counter = 0; counter < getTotalTaskCount(); counter++) {
            int listIndex = counter + 1;
            Task currentTask = getTaskById(counter);
            System.out.println(listIndex + "." + currentTask.printAddedTask());
        }
        Message.printHorizontalLines();
    }
}
