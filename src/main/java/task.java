import java.util.ArrayList;

public class task {
    protected String taskName;
    protected boolean isDone;
    // (Wk 3) Level-2 Include class level members to dynamically count total number of task
    private static int totalNumberOfTodoTask = 0;

    public task(String taskName) {
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
    public static void setTaskMarkStatus(ArrayList<task> taskList, int index, boolean isDone) {
        index --;
        task currentTask = taskList.get(index);
        currentTask.isDone = isDone;
        message.printTaskStatusMessage(isDone, currentTask);
    }
    // (Wk 3) Level-1 Echo item and print in format
    public static void printAddedTask(task instanceTask) {
        // (Wk 3) Level-2 Add to list
        message.printSingleMessage("added: " + instanceTask.getTaskName());
    }
    // (Wk 3) Level-2 Print out task list
    public static void printTaskList(ArrayList<task> taskList) {
        message.printHorizontalLines();
        // Using totalNumberOfTodoTask as the source of truth for the number of task objects created
        for (int counter = 0; counter < totalNumberOfTodoTask; counter++) {
            int listIndex = counter + 1;
            task currentTask = taskList.get(counter);
            System.out.println(listIndex + ".["  + currentTask.getStatusIcon() + "] " + currentTask.getTaskName());
        }
        message.printHorizontalLines();
    }
}
