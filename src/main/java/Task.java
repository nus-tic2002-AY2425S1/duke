import java.util.ArrayList;

public class Task {
    protected String taskName;
    protected boolean isDone;
    private static int totalNumberOfTodoTask = 0;

    public Task(String taskName) {
        this.taskName = taskName;
        this.isDone = false;

        totalNumberOfTodoTask++;
    }
    public static int getTotalNumberOfTodoTask(){
        return totalNumberOfTodoTask;
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
    public void setTaskMarkStatus(ArrayList<Task> todoTaskList, int index, boolean isDone) {
        Task markTask = todoTaskList.get(index);
        markTask.isDone = isDone;
        Message.printTaskStatusMessage(isDone, markTask);
    }
    public String printAddedTask() {
        return "[" + getStatusIcon() + "] " + taskName;
    }
    public static void printTaskList(ArrayList<Task> todoTaskList) {
        Message.printHorizontalLines();
        System.out.println("Here are the tasks in your list:");
        // Using totalNumberOfTodoTask as the source of truth to determine the number of task objects created
        for (int counter = 0; counter < totalNumberOfTodoTask; counter++) {
            int listIndex = counter + 1;
            Task currentTask = todoTaskList.get(counter);
            System.out.println(listIndex + "." + currentTask.printAddedTask());
        }
        Message.printHorizontalLines();
    }
    public static void addTask(ArrayList<Task> todoTaskList, String todoTask, String keyword, KeywordHandling keywordHandling){
        todoTask = todoTask.toLowerCase();
        switch (keyword){
            case "todo":
                keywordHandling.processTodoTask(todoTask, todoTaskList);
                break;
            case "deadline":
                keywordHandling.processDeadlineTask(todoTask, todoTaskList);
                break;
            case "event":
                keywordHandling.processEventTask(todoTask, todoTaskList);
                break;
        }
    }
}
