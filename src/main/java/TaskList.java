import java.util.List;
import java.util.ArrayList;

// contains the task list e.g., it has operations to add/delete tasks in the list
public class TaskList {
    // https://stackoverflow.com/questions/2279030/type-list-vs-type-arraylist-in-java
    private List<Task> taskList;

    public TaskList() {
        this.taskList = new ArrayList<>();
    }

    public List<Task> getTaskList() {
        return taskList;
    }

    // Gets the total number of tasks in the list
    public int getSize() {
        return getTaskList().size();
    }

    public boolean isEmpty() {
        return getTaskList().isEmpty();
    }

    // Gets a task at a specific index
    public Task getTask(int index) {
        return getTaskList().get(index);
    }

    // Adds a new task
    public void addTask(Task task) {
        if (task != null) {
            getTaskList().add(task);
        }
    }

    // Removes a task from the list
    public boolean removeTask(Task task) {
        return getTaskList().remove(task);
    } 

    public boolean markTask(int taskIndex) {
        Task taskToMark = getTask(taskIndex);
        if (taskToMark.getIsDone() == true) {
            return false;
        } else {
            taskToMark.setDone(true);
            return true;
        }
    }

    public boolean unmarkTask(int indexToUnmark) {
        Task taskToUnmark = getTask(indexToUnmark);
        if (taskToUnmark.getIsDone() == false) {
            return false;
        } else {
            taskToUnmark.setDone(false);
            return true;
        }
    }

}
