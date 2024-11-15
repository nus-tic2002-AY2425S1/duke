package jibberJabber.tasks;

import jibberJabber.ui.Message;

import java.util.ArrayList;

public class TaskList {
    private final ArrayList<Task> todoTaskList;

    public TaskList() {
        this.todoTaskList = new ArrayList<>();
    }
    public ArrayList<Task> getTasks() {
        return todoTaskList;
    }
    public int getTotalTaskCount() {
        return todoTaskList.size();
    }
    public Task getLastTask() {
        return todoTaskList.get(todoTaskList.size() - 1);
    }
    public Task getTaskById(int id) {
        return todoTaskList.get(id);
    }
    public void addTask(Task task) {
        todoTaskList.add(task);
        Message.printAddedTaskMessage(todoTaskList.size(), task.printAddedTask());
    }
    public Task removeTask(int index) {
        Task removedTask = todoTaskList.remove(index);
        Message.printDeleteTaskMessage(todoTaskList.size(), removedTask.printAddedTask());
        return removedTask;
    }
    public void printTaskList() {
        Message.printHorizontalLines();
        System.out.println("Here are the tasks in your list:");
        // Using totalNumberOfTodoTask as the source of truth to determine the number of task objects created
        for (int counter = 0; counter < getTotalTaskCount(); counter++) {
            int listIndex = counter + 1;
            Task currentTask = getTaskById(counter);
            System.out.println(listIndex + "." + currentTask.printAddedTask());
        }
        Message.printHorizontalLines();
    }
}
