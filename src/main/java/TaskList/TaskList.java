package TaskList;
import General.Deadline;
import General.Event;
import General.Task;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import Ui.Ui;
import General.Todo;
import Storage.Storage;
public class TaskList {
    private static ArrayList<Task> tasks;
    private static Storage storage;
    public TaskList(Storage storage) {
        tasks = new ArrayList<>();
        this.storage = storage;
    }
    public int getSize(){
        return tasks.size();
    }

    public int getTaskIndex(String description) {
        for (int i = 0; i < tasks.size(); i++) {
            if (tasks.get(i).getDescription().equalsIgnoreCase(description)) {
                return i; // return the index if found
            }
        }
        return -1; // return -1 if not found
    }
    public void markAsDone(int index) {
        try {
            tasks.get(index).markAsDone();
        }catch (IndexOutOfBoundsException e){
            System.out.println(Ui.start+ "\n\tItem is out of range. You currently only have " + tasks.size() + " items."+Ui.end);
        }
    }

    public void markAsUnDone(int index) {
        try {
            tasks.get(index).markAsUnDone();
        }catch (IndexOutOfBoundsException e){
            System.out.println(Ui.start+ "\n\tItem is out of range. You currently only have " + tasks.size() + " items."+Ui.end);
        }
    }

    public void addTask(Task task,boolean fromFile) throws IOException {
        //System.out.println(Ui.start+ "\n\tAdding task " + task.getDescription()+Ui.end);
        tasks.add(task);
        if (!fromFile) {
            Storage.appendToFile(task);
        }
    }
    public void removeTask(int index){
        try {
            tasks.remove(index);
        } catch (IndexOutOfBoundsException e) {
            System.out.println(Ui.start+ "\n\tItem is out of range. You currently only have " + (tasks.size()) + " items."+Ui.end);
        }
    }
    public ArrayList<Task> getTasks() {
        return tasks;
    }

}
