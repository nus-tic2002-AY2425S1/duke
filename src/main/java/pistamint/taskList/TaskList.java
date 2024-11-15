package pistamint.taskList;
import java.io.IOException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import pistamint.general.*;
import pistamint.*;

import pistamint.ui.Ui;
import pistamint.storage.Storage;
public class TaskList {
    public static ArrayList<Task> tasks;
    private static Storage storage;
    public TaskList(Storage storage) {
        tasks = new ArrayList<>();
        this.storage = storage;
    }

    /**
     * This method returns the number of item in the task list.
     * @return the size of the task list
     */
    public int getSize(){
        return tasks.size();
    }

    /**
     * This method marks the task from the task list as completed.
     * @param index refers to the position of the list item that is to be marked as completed
     * @throws IndexOutOfBoundsException when user input the index of item is out of range.
     */
    public void markAsDone(int index) {
        tasks.get(index).markAsDone();
    }

    /**
     * This method marks the task from the task list as not completed.
     * @param index refers to the position of the list item that is to be unmarked.
     * @throws IndexOutOfBoundsException when user input the index of item is out of range.
     */
    public void markAsUnDone(int index) {
        tasks.get(index).markAsUnDone();

    }

    /**
     * This method adds task into the task list. If item that is added is not on first load form the file storage
     * then it has to be appended into the file storage
     * @param task refers to the task that is to be added in the task list / into the file storage.
     * @param fromFile is a boolean to indicate if this task that is currently processed is it from file storage.
     * @throws IOException when there is IO issues when processing the entry.
     */
    public void addTask(Task task,boolean fromFile) {
        try {
            tasks.add(task);
            if (!fromFile) {
                Storage.appendToFile(task);
            }
        }catch (IOException e){
            Ui.runTimeException(e.getMessage());
        }

    }

    /**
     * This method is to remove the task from the task list based on the item index.
     * @param index refers to the position of the list item that is to be removed.
     * @throws IndexOutOfBoundsException when user input the index of item is out of range.
     */
    public void removeTask(int index){
        tasks.remove(index);
    }

    /**
     * This method get the list of task of in the task list
     * @return an ArrayList of tasks that is currently stored in the tasklist.
     */
    public ArrayList<Task> getTasks() {
        return tasks;
    }

    public ArrayList<Task> findTask(String keyword){
        ArrayList<Task> matchedTask = new ArrayList<>();
        for (Task task : tasks) {
            Pattern pattern = Pattern.compile(keyword, Pattern.CASE_INSENSITIVE);
            Matcher matcher = pattern.matcher(task.getDescription());
            if (matcher.find()) {
                matchedTask.add(task);
            }
        }
        return matchedTask;
    }

}
