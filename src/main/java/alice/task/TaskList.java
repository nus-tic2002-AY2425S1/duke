package alice.task;

import java.time.LocalDate;
import java.util.ArrayList;

import static alice.command.AddCommand.buildFormatter;

/**
 * <h1>Tasklist</h1>
 * The Tasklist class details the implementation
 * of an arrayList task object which keeps track
 * of the tasks currently in memory.
 * <p>
 *
 * @author  Jarrel Bin
 * @version 1.0
 * @since   2024-11-02
 */
public class TaskList {
    protected ArrayList<Task> tasks;

    public TaskList(ArrayList<Task> tasks){
        this.tasks = tasks;
    }
    public TaskList(){
        this(new ArrayList<Task>());
    }

    public void printTasks(){
        for (int i = 0; i < tasks.size(); i++){
            System.out.println((i+1) + "." + tasks.get(i).print());
        }
    }

    public void printTasks(String searchWord){
        for (int i = 0; i < tasks.size(); i++){
            if (tasks.get(i).print().contains(searchWord)) {
                System.out.println((i + 1) + "." + tasks.get(i).print());
            }
        }
    }

    //Solution below adapted from https://stackoverflow.com/questions/11466441/call-a-child-class-method-from-a-parent-class-object
    public void printTasks(LocalDate dateQuery){
        for (int i = 0; i < tasks.size(); i++){
            if (tasks.get(i) instanceof Deadline){
                String by = ((Deadline)tasks.get(i)).getBy();
                if (LocalDate.parse(by, buildFormatter()).equals(dateQuery))
                    System.out.println((i+1) + "." + tasks.get(i).print());
            }else if (tasks.get(i) instanceof Event){
                String from = ((Event)tasks.get(i)).getFrom();
                String to = ((Event)tasks.get(i)).getTo();
                if (LocalDate.parse(from, buildFormatter()).isBefore(dateQuery) && LocalDate.parse(to, buildFormatter()).isAfter(dateQuery))
                    System.out.println((i+1) + "." + tasks.get(i).print());
            }
        }
    }

    public Task get(int i) {
        return tasks.get(i);
    }

    public void remove(int i) {
        tasks.remove(i);;
    }

    public void remove(String removeWord) {
        ArrayList<Task> tasksToRemove = new ArrayList<>();
        for (Task task : tasks) {
            if (task.print().contains(removeWord)) {
                tasksToRemove.add(task);
                System.out.println(task.print());
            }
        }
        tasks.removeAll(tasksToRemove);

    }

    public int size() {
        return tasks.size();
    }

    public void add(Task task) {
        tasks.add(task);
    }

    public Task getLast() {
        return tasks.get(tasks.size()-1);
    }
    public String toString(){
        ArrayList<String> out = new ArrayList<>();
        for (Task task : tasks) {
            out.add(task.toSave());
        }
        return String.join("\n", out);

    }
}
