package Chad.Ui;


import Chad.TaskList.Task;
import Chad.TaskList.TaskList;

public class TextUi implements Ui {
    // Attributes for UI
    //private final String myline = "_________________________________________________________________";
    private final String logo = " Chad\n";
    //private final Scanner scanner;
    private String chadResponse;

    public TextUi() {
        //this.scanner = new Scanner(System.in);
        //chadResponse="Hi";
    }

    public String getChadResponse(){
        return chadResponse;
    }
    
    @Override
    public void showMsg(String myMsg) {
        chadResponse=myMsg;
    }

    @Override
    public String showWelcome() {
        //System.out.println("Hello from " + logo + System.lineSeparator() + "What can I do for you?\n");
        return  "Hello from " + logo + "\\n" + "What can I do for you?\n";
    }

    @Override
    public void showError(String errmsg) {
        //System.out.println(myline);
        //System.out.println(errmsg);
        chadResponse = errmsg;
    }

    @Override
    public void showBye() {
        //System.out.println("Bye. Hope to see you again soon!"); // Show goodbye message
        chadResponse = "Bye. "+System.lineSeparator()+"Hope to see you again soon!";
    }

    @Override
    public void showTaskList(TaskList tasks) {
       // System.out.println(myline);
       // System.out.println("Here are the tasks in your list:");
        chadResponse ="Here are the tasks in your list:"+System.lineSeparator();
        for (int i = 0; i < tasks.getNoOfTask(); i++) {
            //System.out.println((i + 1) + ". " + tasks.getTaskById(i).toString());
            chadResponse += ((i + 1)+ ". " + tasks.getTaskById(i).toString())+System.lineSeparator();
        }
        //System.out.println(myline);
    }

    @Override
    public void showHelp(String helpContentString) {
        //System.out.println(System.lineSeparator());
        //System.out.println(helpContentString);
        //System.out.println(System.lineSeparator());
        chadResponse=helpContentString;
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
        //System.out.println(myline);
        //System.out.println("Nice! I've marked this task as done:");
        //System.out.println(task.toString());  // Assuming Task has an overridden toString() method
        //System.out.println(myline);
        chadResponse = "Nice! I've marked this task as done:"+System.lineSeparator()+task.toString();
    }

    @Override
    public void showUnMarkTask(Task task) {
        //System.out.println(myline);
        //System.out.println("OK, I've marked this task as not done yet:");
        //System.out.println(task.toString());  // Assuming Task has an overridden toString() method
        //System.out.println(myline);
        chadResponse = "OK, I've marked this task as not done yet:"+System.lineSeparator()+task.toString();
    }

    // Helper method to handle the common output formatting
    private void displayTaskAction(String action, Task task, int noOfTask) {
        //System.out.println("Got it. I've " + action + " this task: " + task.toString() +
                //System.lineSeparator() +
                //"Now you have " + noOfTask + " tasks in the list.");
        chadResponse = "Got it. I've " + action + " this task: "+System.lineSeparator() + task.toString() +
                System.lineSeparator() +
                "Now you have " + noOfTask + " tasks in the list.";
    }
}