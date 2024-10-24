package wkduke.task;

import java.util.ArrayList;
import java.util.List;

public class TaskList {
    public final List<Task> taskList;

    public TaskList() {
        taskList = new ArrayList<>();
    }

    public TaskList(List<Task> taskList) {
        this.taskList = taskList;
    }

    public void addTask(Task task) {
        taskList.add(task);
    }

    public void deleteTask(int index) {
        taskList.remove(index);
    }

    public List<Task> getAllTask() {
        return taskList;
    }

    public Task getTask(int index) {
        return taskList.get(index);
    }

    public boolean isEmpty() {
        return taskList.isEmpty();
    }

    public boolean markTask(int index) {
        Task task = getTask(index);
        if (task.isDone()) {
            return false;
        }
        task.markAsDone();
        return true;
    }

    public int size() {
        return taskList.size();
    }

    public boolean unmarkTask(int index) {
        Task task = getTask(index);
        if (!task.isDone()) {
            return false;
        }
        task.markAsUndone();
        return true;
    }
}
