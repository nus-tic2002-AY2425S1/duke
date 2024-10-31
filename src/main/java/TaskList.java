import java.util.List;
import java.util.ArrayList;

// contains the task list e.g., it has operations to add/delete tasks in the list
public class TaskList {
    // https://stackoverflow.com/questions/2279030/type-list-vs-type-arraylist-in-java
    private List<Task> taskList;

    public TaskList() {
        this.taskList = new ArrayList<>();
    }

    // Gets the total number of tasks in the list
    public int getSize() {
        return taskList.size();
    }

    public boolean isEmpty() {
        return taskList.isEmpty();
    }

    // Gets a task at a specific index
    public Task getTask(int index) {
        return taskList.get(index);
    }

    // Adds a new task
    public void addTask(Task task) {
        if (task != null) {
            taskList.add(task);
        }
    }

    // Removes a task from the list
    public boolean removeTask(Task task) {
        return taskList.remove(task);
    } 

    public List<String> prepareTaskListToPrint() throws TaskException {
        List<String> taskListForPrint = new ArrayList<>();
        
        if (isEmpty()) {
            throw new TaskException(Messages.MESSAGE_TASKLIST_EMPTY);
        }
        
        for (int i = 0; i < getSize(); i++) {
            Task current = getTask(i);         // taskList.get(i) contains the checkbox
            String index = Integer.toString(i + 1);
            String line = index + ". " + current;
            taskListForPrint.add(line);
        }

        return taskListForPrint;
    }

    // @Override
    // public void toString() {
    //     for (Task task : taskList) {

    //     }
    // }
}
