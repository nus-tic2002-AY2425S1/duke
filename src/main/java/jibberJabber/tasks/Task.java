package jibberJabber.tasks;

import jibberJabber.commands.KeywordHandling;
import jibberJabber.ui.Message;

public class Task {
    protected String taskName;
    public boolean isDone;
    private static int totalNumberOfTodoTask = 0;

    public Task(String taskName) {
        this.taskName = taskName;
        this.isDone = false;

        totalNumberOfTodoTask++;
    }
    public static int decreaseTotalNumberOfTodoTask(){
        if (totalNumberOfTodoTask > 0){
            totalNumberOfTodoTask --;
        }
        return totalNumberOfTodoTask;
    }
    public String getTaskName() {
        return taskName;
    }
    public String getStatusIcon(){
        return (isDone ? "X" : " ");
    }
    public void setTaskMarkStatus(TaskList todoTaskList, int index, boolean isDone, boolean isFromFile) {
        Task markTask = todoTaskList.getTaskById(index);
        markTask.isDone = isDone;
        if (!isFromFile) {
            Message.printTaskStatusMessage(isDone, markTask);
        }
    }
    public String printAddedTask(){
        return "[" + getStatusIcon() + "] " + taskName;
    }
    public static void addTask(TaskList todoTaskList, String todoTask, String keyword, KeywordHandling keywordHandling , boolean isFromFile){
        switch (keyword){
            case "todo":
                keywordHandling.processTodoTask(todoTask, todoTaskList, isFromFile);
                break;
            case "deadline":
                keywordHandling.processDeadlineTask(todoTask, todoTaskList, isFromFile);
                break;
            case "event":
                keywordHandling.processEventTask(todoTask, todoTaskList, isFromFile);
                break;
        }
    }
}
