import java.lang.reflect.Array;

public class task {
    private String taskName;
    // (Wk 3) Level-2 Include class level members to dynamically count total number of task
    private static int totalNumberOfTodoTask = 0;

    public task(String taskName) {
        this.taskName = taskName;
        totalNumberOfTodoTask++;
    }
    public static int getTotalNumberOfTodoTask(){
        return totalNumberOfTodoTask;
    }
    public String getTaskName() {
        return taskName;
    }
    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }
    // (Wk 3) Level-1 Echo item and print in format
    public static void printAddedTask(String taskName) {
        message m = new message(taskName);
        message.printHorizontalLines();
        // (Wk 3) Level-2 Add to list
        System.out.println("added: " + m.getTextMessage());
        message.printHorizontalLines();
    }
    // (Wk 3) Level-2 Print out task list
    public static void printTaskList(String[] taskList) {
        message.printHorizontalLines();
        for (int counter = 0; counter < totalNumberOfTodoTask; counter++) {
            int listIndex = counter + 1;
            System.out.println(listIndex + ". " + taskList[counter]);
        }
        message.printHorizontalLines();
    }
}
