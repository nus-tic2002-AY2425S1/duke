package jibberJabber.tasks;

import jibberJabber.tasks.taskType.Deadline;
import jibberJabber.tasks.taskType.Event;
import jibberJabber.ui.Message;

import java.time.LocalDate;
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
        for (int counter = 0; counter < getTotalTaskCount(); counter++) {
            int listIndex = counter + 1;
            Task currentTask = getTaskById(counter);
            System.out.println(listIndex + "." + currentTask.printAddedTask());
        }
        Message.printHorizontalLines();
    }
    /**
     * Retrieve tasks within a given period
     *
     * @param startDate the index of the task to remove.
     * @param endDate the index of the task to remove.
     * @return task list of tasks within the date range
     */
    public TaskList getTasksWithinPeriod(LocalDate startDate, LocalDate endDate) {
        LocalDate taskStartDate;
        LocalDate taskEndDate;
        TaskList todoTaskList = new TaskList();
        for (Task task : getTasks()) {
            if (!task.isDone){
                if (task instanceof Event) {
                    taskStartDate = ((Event) task).from;
                    taskEndDate = ((Event) task).to;
                    if ((taskStartDate.isEqual(startDate) || taskStartDate.isAfter(startDate)) &&
                        (taskEndDate.isEqual(endDate) || taskEndDate.isBefore(endDate))) {
                            todoTaskList.addTask(task);
                    }
                } else if (task instanceof Deadline) {
                    taskEndDate = ((Deadline) task).by;
                    if ((taskEndDate.isEqual(startDate) || taskEndDate.isAfter(startDate)) &&
                       (taskEndDate.isEqual(endDate) || taskEndDate.isBefore(endDate))) {
                            todoTaskList.addTask(task);
                    }
                }

            }
        }
        return todoTaskList;
    }
    /**
     * Retrieve tasks with the specified keyword
     *
     * @param searchKeyword the keyword to search within each task's name.
     * @param taskList the task list to sieve through the task.
     * @return task list of tasks with the keyword present
     */
    public TaskList getTasksWithMatchingKeyword(String searchKeyword, TaskList taskList){
        TaskList matchingStrings = new TaskList();
        searchKeyword = searchKeyword.toLowerCase();
        for (Task task : taskList.getTasks()) {
            if (task.printAddedTask().toLowerCase().contains(searchKeyword)){
                matchingStrings.addTask(task);
            }
        }
        return matchingStrings;
    }
}
