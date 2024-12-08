package Chad.Ui;


import Chad.TaskList.Task;
import Chad.TaskList.TaskList;

public class TextUi implements Ui {
    // Attributes for UI

    private final String logo = " Chad\n";
    private String chadResponse;



    public String getChadResponse() {
        return chadResponse;
    }

    @Override
    public void showMsg(String myMsg) {
        chadResponse = myMsg;
    }

    @Override
    public String showWelcome() {
        return "Hello from " + logo + "\\n" + "What can I do for you?\n";
    }

    @Override
    public void showError(String errmsg) {
        chadResponse = errmsg;
    }

    @Override
    public void showBye() {
        chadResponse = "Bye. " + System.lineSeparator() + "Hope to see you again soon!";
    }

    @Override
    public void showTaskList(TaskList tasks) {
        String responseString;
        if(tasks.getNoOfTask()==0){
            responseString="Opps! The list is empty!";
        }
        else{
            responseString = "Here are the tasks in your list:" + System.lineSeparator();
        }
        
        for (int i = 0; i < tasks.getNoOfTask(); i++) {
            responseString += ((i + 1) + ". " + tasks.getTaskById(i).toString()) + System.lineSeparator();
        }
        chadResponse = responseString;
    }

    @Override
    public void showHelp(String helpContentString) {

        chadResponse = helpContentString;
    }

    @Override
    public void showDeleteTask(Task task, int noOfTask) {
        displayTaskAction("removed", task, noOfTask);
    }

    @Override
    public void showAddTask(Task task, int noOfTask) {
        displayTaskAction("added", task, noOfTask);
    }

    @Override
    public void showMarkTask(Task task) {
        chadResponse = "Nice! I've marked this task as done:" + System.lineSeparator() + task.toString();
    }

    @Override
    public void showUnMarkTask(Task task) {
        chadResponse = "OK, I've marked this task as not done yet:" + System.lineSeparator() + task.toString();
    }

    // Helper method to handle the common output formatting
    private void displayTaskAction(String action, Task task, int noOfTask) {

        chadResponse = "Got it. I've " + action + " this task: " + System.lineSeparator() + task.toString() +
                System.lineSeparator() +
                "Now you have " + noOfTask + " tasks in the list.";
    }
}