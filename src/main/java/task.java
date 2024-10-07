public class task {
    private String taskName;

    public task(String taskName) {
        this.taskName = taskName;
    }
    public String getTaskName() {
        return taskName;
    }
    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }
    // (Wk 3) Level-1 echo item and print in format
    public static void printTask(String taskName) {
        message m = new message(taskName);
        message.printHorizontalLines();
        System.out.println(m.getTextMessage());
        message.printHorizontalLines();
    }
}
