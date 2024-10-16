package alice.task;

import java.util.ArrayList;

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

    public Task get(int i) {
        return tasks.get(i);
    }

    public void remove(int i) {
        tasks.remove(i);;
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
