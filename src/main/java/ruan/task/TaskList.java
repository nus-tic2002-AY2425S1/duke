package ruan.task;

import java.util.ArrayList;
import ruan.exception.ErrorType;
import ruan.exception.RuanException;

public class TaskList {
    private ArrayList<Task> tasks;    
    
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }
   
    public void addTask(Task task) {
        tasks.add(task);
    }  
    
    public void deleteTask(int index) throws RuanException {
        if (index < 0 || index >= tasks.size()) {
            throw new RuanException(ErrorType.INVALID_TASK_NUMBER);
        }

        tasks.remove(index);
    }

    public void setDone(int index, boolean isDone) throws RuanException {
        if (index < 0 || index >= tasks.size()) {
            throw new RuanException(ErrorType.INVALID_TASK_NUMBER);
        }
        tasks.get(index).setDone(isDone);
    }

    public ArrayList<Task> getTasks() {
        return tasks;
    }

    public String getTaskDescription(int index) {
        return tasks.get(index).toString();
    }

    public int size() {
        return tasks.size();
    }

}
