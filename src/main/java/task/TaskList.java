package task;
import java.util.List;
import java.util.ArrayList;
import java.time.LocalDate;

/**
 * TaskList represents a list of tasks.
 * It provides methods to manage, manipulate, and do operations on the tasks list
 * Functionalities include adding, deleting, marking, unmarking and retrieving tasks in the list.
 * It can also check tasks on specific dates and retrieve the size of the {@code TaskList}
 */
public class TaskList {
    // https://stackoverflow.com/questions/2279030/type-list-vs-type-arraylist-in-java
    private List<Task> taskList;

    /**
     * Constructor to initialize the {@code TaskList} as an empty list.
     */
    public TaskList() {
        this.taskList = new ArrayList<>();
    }

    /**
     * Returns the list of tasks.
     * @return the list of tasks in the {@code TaskList}
     */
    public List<Task> getTaskList() {
        return taskList;
    }

    /**
     * Retrieves the size of the {@code TaskList}.
     * @return the total number of tasks in the {@code TaskList}
     */
    public int getSize() {
        return getTaskList().size();
    }

    /**
     * Checks if the {@code TaskList} is empty.
     * @return true if the {@code TaskList} is empty; false otherwise
     */
    public boolean isEmpty() {
        return getTaskList().isEmpty();
    }

    /**
     * Retrieves the task at a specific index in the {@code TaskList}.
     * @param index represents the index of the task to retrieve
     * @return the task at the specified index
     */
    public Task getTask(int index) {
        return getTaskList().get(index);
    }

    /**
     * Adds a new task to the {@code TaskList}
     * @param task represents the task to be added. If it is null, the task will not be added
     */
    // Adds a new task
    public void addTask(Task task) {
        if (task != null) {
            getTaskList().add(task);
        }
    }

    /**
     * Removes a task from the list 
     * @param task represents the task to be removed from the {@code TaskList}
     * @return true if the task is removed successfully; false otherwise
     */
    public boolean removeTask(Task task) {
        return getTaskList().remove(task);
    } 

    /**
     * Makes a task at a specified index as done. 
     * Returns false if the task has already been marked done before this method is called.
     * @param taskIndex represents the index of the task to mark
     * @return true if the task is marked successfully; false otherwise
     */
    public boolean markTask(int taskIndex) {
        Task taskToMark = getTask(taskIndex);
        if (taskToMark.getIsDone() == true) {       // Task has already been marked as done
            return false;
        } else {
            taskToMark.setDone(true);
            return true;
        }
    }

    /**
     * Marks a task at a specified index as not done.
     * Returns false if the task has already been marked as not done before this method is called.
     * @param indexToUnmark represents the index of the task to unmark
     * @return true if the task is unmarked successfully; false otherwise
     */
    public boolean unmarkTask(int indexToUnmark) {
        Task taskToUnmark = getTask(indexToUnmark);
        if (taskToUnmark.getIsDone() == false) {        // Task has already been marked as not done
            return false;
        } else {
            taskToUnmark.setDone(false);
            return true;
        }
    }

    /**
     * Returns the appropriate word for "task" based on the number of tasks in the {@code TaskList}
     * @return "task" if there is only one task in the {@code TaskList}; "tasks" if there are multiple
     */
    public String getTaskWord() {
        StringBuilder taskWordStringBuilder = new StringBuilder(" task");
        if (getSize() > 1) {
            taskWordStringBuilder.append("s");
        }
        String taskWord = taskWordStringBuilder.toString();
        return taskWord;
    }

    /**
     * Retrieves all tasks that occur on a specific date
     * @param date represents the date to check against
     * @return a list of tasks that occur on a specified date
     */
    public List<Task> getAllTasksOnDate(LocalDate date) {
        List<Task> tasksOnDate = new ArrayList<>();
        for (Task task : getTaskList()) {
            if (task.isOnDate(date)) {
                tasksOnDate.add(task);
            }
        }
        return tasksOnDate;
    }
}
