package Pistamint.TaskList;
import Pistamint.General.Task;
import java.io.IOException;
import java.util.ArrayList;
import Pistamint.Ui.Ui;
import Pistamint.Storage.Storage;
public class TaskList {
    private static ArrayList<Task> tasks;
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
        try {
            tasks.get(index).markAsDone();
        }catch (IndexOutOfBoundsException e){
            System.out.println(Ui.start+ "\n\tItem is out of range. You currently only have " + tasks.size() + " items."+Ui.end);
        }
    }

    /**
     * This method marks the task from the task list as not completed.
     * @param index refers to the position of the list item that is to be unmarked.
     * @throws IndexOutOfBoundsException when user input the index of item is out of range.
     */
    public void markAsUnDone(int index) {
        try {
            tasks.get(index).markAsUnDone();
        }catch (IndexOutOfBoundsException e){
            System.out.println(Ui.start+ "\n\tItem is out of range. You currently only have " + tasks.size() + " items."+Ui.end);
        }
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
        try {
            tasks.remove(index);

        } catch (IndexOutOfBoundsException e) {
            System.out.println(Ui.start+ "\n\tItem is out of range. You currently only have " + (tasks.size()) + " items."+Ui.end);
        }
    }

    /**
     * This method get the list of task of in the task list
     * @return an ArrayList of tasks that is currently stored in the tasklist.
     * @throws IndexOutOfBoundsException when user input the index of item is out of range.
     */
    public ArrayList<Task> getTasks() {
        return tasks;
    }

}
