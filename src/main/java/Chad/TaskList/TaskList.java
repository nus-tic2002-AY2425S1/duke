package Chad.TaskList;

import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> tasks;

    public TaskList() {
        tasks = new ArrayList<>();
    }


    public void addTask(Task task) {
        tasks.add(task);
    }
    //its easier to delete by id
    public void deleteTask(int index) throws IndexOutOfBoundsException {
        if (index < 0 || index >= tasks.size()) {
            throw new IndexOutOfBoundsException("Invalid task index: " + (index + 1));
        }
        tasks.remove(index); // Remove the task from the tasks list
    }

    public void markTask(int index) {
        Task task = tasks.get(index);
        task.setTask();
    }
    public void unmarkTask(int index) {
        Task task = tasks.get(index);
        task.unSetTask();
    }
    public void printTasks() {
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println((i + 1) + ". " + tasks.get(i).toString());
        }
    }
    public Task getTaskById(Integer taskId)
    {
        return tasks.get(taskId);
    }

    public Integer getNoOfTask()
    {
        return tasks.size();
    }

    // convert a list to string for storage?
    @Override
    public String toString() {
        if (tasks.isEmpty()) {
            return "The list is empty.";
        }
    
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < tasks.size(); i++) {
            sb.append(i + 1).append(". ").append(tasks.get(i).toString()).append(System.lineSeparator());
        }
        return sb.toString().trim(); // Convert StringBuilder to String and trim the trailing newline
    }
}