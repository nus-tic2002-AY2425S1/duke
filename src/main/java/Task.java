import java.util.ArrayList;

public class Task {
    protected String taskName;
    protected boolean isDone;
    // (Wk 3) Level-2 Include class level members to dynamically count total number of task
    private static int totalNumberOfTodoTask = 0;

    public Task(String taskName) {
        this.taskName = taskName;
        this.isDone = false;

        totalNumberOfTodoTask++;
    }
    public static int getTotalNumberOfTodoTask(){
        return totalNumberOfTodoTask;
    }
    public String getTaskName() {
        return taskName;
    }
    // (Wk 4) Level-3 Set the indexed task with "X" when mark as done and empty when mark as undone
    public String getStatusIcon(){
        return (isDone ? "X" : " ");
    }
    // (Wk 4) Level-3 Set value isDone = isDone (arg)
    public void setTaskMarkStatus(ArrayList<Task> todoTaskList, int index, boolean isDone) {
        Task markTask = todoTaskList.get(index);
        markTask.isDone = isDone;
        Message.printTaskStatusMessage(isDone, markTask);
    }
    // (Wk 3) Level-1 Echo item and print in format
    // (Wk 5) Level-4 Updated to account for inheritance
    public String printAddedTask() {
        // (Wk 3) Level-2 Add to list
        return "[" + getStatusIcon() + "] " + taskName;
    }
    // (Wk 3) Level-2 Print out task list
    public static void printTaskList(ArrayList<Task> todoTaskList) {
        Message.printHorizontalLines();
        System.out.println("Here are the tasks in your list:");
        // Using totalNumberOfTodoTask as the source of truth for the number of task objects created
        for (int counter = 0; counter < totalNumberOfTodoTask; counter++) {
            int listIndex = counter + 1;
            Task currentTask = todoTaskList.get(counter);
            // (Wk 5) Level-4 Updated to account for polymorphism
            System.out.println(listIndex + "." + currentTask.printAddedTask());
        }
        Message.printHorizontalLines();
    }
    // (WK 5) Level-4 enhancing addition of task to list
    public static void addTask(ArrayList<Task> todoTaskList, String todoTask, String keyword){
        switch (keyword){
            case "todo":
                KeywordHandling.processTodoTask(todoTask, todoTaskList);
                break;
            case "deadline":
                KeywordHandling.processDeadlineTask(todoTask, todoTaskList);
                break;
            case "event":
                KeywordHandling.processEventTask(todoTask, todoTaskList);
                break;
        }
    }
}
