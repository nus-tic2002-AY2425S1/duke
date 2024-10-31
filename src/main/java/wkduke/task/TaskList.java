package wkduke.task;

import java.time.LocalDateTime;
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

    public List<Task> getAllTaskOnDate(LocalDateTime targetDateTime) {
        List<Task> result = new ArrayList<>();

        for (Task task : taskList) {
            if (task.isOnDate(targetDateTime)) {
                result.add(task);
            }
        }
        return result;
    }

    public Task getTask(int index) {
        return taskList.get(index);
    }

    public int getTaskIndex(Task task) {
        return taskList.indexOf(task);
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
