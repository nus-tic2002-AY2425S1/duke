import java.util.Scanner;


public class May {

    /*Store User Input*/
    private static Task[] taskList = new Task[100];
    private static int listNum = 0;

    public static void main(String[] args) {

        //Scanner
        Scanner scan = new Scanner(System.in);
        String input;

        /*Welcome Message*/
        System.out.println("Hello! I'm May");
        System.out.println("What can I do for you?");

        while(true){
            // Scan for user input
            input = scan.nextLine();

            // split the input into two put one for task status other part for description
            String[] command = input.split(" ",2);

            // Check for Exit command & output exit command message
            if(command[0].equalsIgnoreCase("bye")){
                /*Exit Message*/
                System.out.println("Bye. Hope to see you again soon!");
                break;
            }

            // Add a normal task (not used in the updated design, can be removed)
            else if (command[0].equalsIgnoreCase("add") && command.length > 1) {
                addTask(new Task(command[1]));
            }

            // Check for taskList Command & output item inside taskList
            else if (command[0].equalsIgnoreCase("taskList")){
                displayTaskList();
            }

            // Mark the task as done
            else if (command[0].equalsIgnoreCase("tick")){
                int taskIndex = Integer.parseInt(command[1])-1;
                // Check for taskIndex is within the task number
                // If it is within the range, then we mark as done
                if (taskIndex < listNum){
                   taskList[taskIndex].markAsDone();
                   System.out.println("Congratulations! You have completed the task! ");
                   System.out.println(" " + taskList[taskIndex]);
                }
            }

            // Mark the task as cross since it has not done
            else if (command[0].equalsIgnoreCase("cross")){
                int taskIndex = Integer.parseInt(command[1])-1;
                // Check for taskIndex is within the task number
                // If it is within the range, then we mark as done
                if (taskIndex < listNum){
                    taskList[taskIndex].unmarkAsDone();
                    System.out.println("Task have not done, Kindly complete the task. ");
                    System.out.println(" " + taskList[taskIndex]);
                }
            }

            //Create the todo Task and output number of task in the list
            else if (command[0].equalsIgnoreCase("todo") && command.length > 1) {
                String description = command[1];
                addOrUpdateTask(new ToDo(description));
            }

            // Mark the deadline of the task
            else if (command[0].equalsIgnoreCase("deadline") && command.length > 1) {
                String[] doneBy = command[1].split("/by ", 2);
                if (doneBy.length > 1) {
                    addOrUpdateTask(new Deadline(doneBy[0].trim(), doneBy[1].trim()));
                } else {
                    System.out.println("Invalid deadline.");
                }
            }

            // Create upcoming event task
            else if (command[0].equalsIgnoreCase("event") && command.length > 1) {
                String[] eventTime = command[1].split("/from | /to ",3);
                if (eventTime.length > 2) {
                    addOrUpdateTask(new Event(eventTime[0].trim(), eventTime[1].trim(), eventTime[2].trim()));
                }
                else{
                    System.out.println("Invalid event.");
                }
            }


            else {
                System.out.println("Unknown command");
            }
        }
        // Close Scan
        scan.close();
    }

    // add or update the task
    private static void addOrUpdateTask(Task newTask){
        for (int i = 0; i < listNum; i++){
            if (taskList[i].taskName.equals(newTask.taskName)){
                taskList[i] = newTask;
                System.out.println("Got it, I've updated this task:");
                System.out.println(" " + taskList[i]);
                System.out.println("Now you have " + listNum + " tasks in the list.");
                return;
            }
        }
        addTask(newTask);
    }

    // Add task to the tasklist
    private static void addTask(Task task){
        taskList[listNum] = task;
        listNum++;
        System.out.println("Got it, I've added this task:");
        System.out.println(" " + taskList[listNum - 1]);
        System.out.println("Now you have " + listNum + " tasks in the task list.");
    }

    //display the task in the task list
    private static void displayTaskList() {
        if (listNum == 0) {
            System.out.println("No tasks in the list.");
        } else {
            System.out.println("Here are the tasks in your list:");
            for (int i = 0; i < listNum; i++) {
                System.out.println((i + 1) + ". " + taskList[i]);
            }
        }
    }

}
