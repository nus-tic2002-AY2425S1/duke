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
        tasks.add(task);
        if (!fromFile) {
            storage.appendToFile(task);
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

    /*public  void processTask(String input, String task) {
        String action, timeline, from, to = "";
        try {
            if (task.equalsIgnoreCase("todo") ) {
                action = input.substring(input.indexOf(" "));
                Todo todo = new Todo(action.trim(), 'T');
                addTask(todo,false);
                Ui.showTaskAdded(todo,input);
            } else {
                String description;
                if (task.equalsIgnoreCase("deadline")) {
                    description= input.substring(input.indexOf(" "), input.indexOf("/by"));
                    timeline = input.substring(input.indexOf("/by") + 3);
                    Deadline deadline = new Deadline(description.trim(), 'D', timeline);
                    addTask(deadline,false);
                    Ui.showTaskAdded(deadline,input);
                } else if (task.equalsIgnoreCase("event")) {
                    description= input.substring(input.indexOf(" "), input.indexOf("/from"));
                    from = input.substring(input.indexOf("/from") + 5, input.indexOf("/to"));
                    to = input.substring(input.indexOf("/to") + 3);
                    Event event = new Event(description.trim(), 'E', from, to);
                    addTask(event,false);
                    Ui.showTaskAdded(event,input);
                }
            }

        } catch (StringIndexOutOfBoundsException | IOException ex) {
            System.out.println(Ui.start+ "\n\tOOPS!!! The description of " + task + " is incomplete." +Ui.end);
        }
    }*/


}
