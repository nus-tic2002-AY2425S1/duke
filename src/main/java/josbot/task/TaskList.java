package josbot.task;

import java.util.ArrayList;

/**
 * Represent a TaskList where a list of Task are stored
 */

public class TaskList {
    protected ArrayList<Task> tasks;

    /**
     * Creates an empty TaskList
     */
    public TaskList() {
        this.tasks = new ArrayList<Task>();
    }

    /**
     * Creates a TaskList using the data in the parameter
     *
     * @param load Consist of list of Task
     */
    public TaskList(ArrayList<Task> load) {
        this.tasks = load;
    }

    /**
     * Return ArrayList<Task> consisting of tasks
     *
     * @return list of tasks
     */
    public ArrayList<Task> getTasks() {
        return tasks;
    }

    /**
     * Return an integer of the current task size/count
     *
     * @return an integer of the current task size/count
     */
    public int getTaskCount() {
        return tasks.size();
    }

    /**
     * Add a task to the task list
     *
     * @param task that's going to be added to the task list
     */
    public void addTask(Task task) {
        tasks.add(task);
    }

    /**
     * Remove task from the task list
     *
     * @param task_number that's going to be removed from the task list
     */
    public void removeTask(int task_number) {
        tasks.remove(task_number);
    }


}
