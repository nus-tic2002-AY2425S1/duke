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
            try {
                // Scan for user input
                input = scan.nextLine();

                // split the input into two put one for task status other part for description
                String[] command = input.split(" ", 2);

                // Check for Exit command & output exit command message
                if (command[0].equalsIgnoreCase("bye")) {
                    /*Exit Message*/
                    System.out.println("Bye. Hope to see you again soon!");
                    break;
                }

                // Add a normal task (not used in the updated design, can be removed)
                else if (command[0].equalsIgnoreCase("add") && command.length > 1) {
                    addTask(new Task(command[1]));
                }

                // Check for taskList Command & output item inside taskList
                else if (command[0].equalsIgnoreCase("taskList")) {
                    displayTaskList();
                }

                // Mark the task as done
                else if (command[0].equalsIgnoreCase("tick")) {
                    int taskIndex = Integer.parseInt(command[1]) - 1;
                    // Check for taskIndex is within the task number
                    // If it is within the range, then we mark as done
                    if (taskIndex < listNum) {
                        taskList[taskIndex].markAsDone();
                        System.out.println("Congratulations! You have completed the task! ");
                        System.out.println(" " + taskList[taskIndex]);
                    }
                    else {
                        // add in error handle
                        throw new ErrorException("Invalid task index. Please enter a valid task index.");
                    }
                }

                // Mark the task as cross since it has not done
                else if (command[0].equalsIgnoreCase("cross")) {
                    int taskIndex = Integer.parseInt(command[1]) - 1;
                    // Check for taskIndex is within the task number
                    // If it is within the range, then we mark as done
                    if (taskIndex < listNum) {
                        taskList[taskIndex].unmarkAsDone();
                        System.out.println("Task have not done, Kindly complete the task. ");
                        System.out.println(" " + taskList[taskIndex]);
                    }
                    else {
                        // add in error handle
                        throw new ErrorException("Invalid task index. Please enter a valid task index.");
                    }
                }

                // Create "todo-task"
                else if (command[0].equalsIgnoreCase("todo")) {
                    if (command.length < 2 || command[1].trim().isEmpty()) {
                        throw new ErrorException(" 'todo' command cannot be empty.");
                    }
                    addOrUpdateTask(new ToDo(command[1]));
                }

                // Mark the deadline of the task
                else if (command[0].equalsIgnoreCase("deadline")) {
                    if (command.length < 2) {
                        throw new ErrorException("'deadline' command cannot be empty.");
                    }
                    String[] doneBy = command[1].split("/by ", 2);
                    if (doneBy.length < 2) {
                        throw new ErrorException("Invalid deadline format.");
                    }
                    addOrUpdateTask(new Deadline(doneBy[0].trim(), doneBy[1].trim()));
                }

                // Create upcoming event task
                else if (command[0].equalsIgnoreCase("event")) {
                    if (command.length < 2) {
                        throw new ErrorException("'event' command cannot be empty.");
                    }
                    String[] eventTime = command[1].split("/from | /to ", 3);
                    if (eventTime.length < 3) {
                        throw new ErrorException("Invalid event format.");
                    }
                    addOrUpdateTask(new Event(eventTime[0].trim(), eventTime[1].trim(), eventTime[2].trim()));
                }

                // Unknown command
                else {
                    throw new Error("I'm Sorry, I don't recognize that command. ");
                }
            }

            // Handle custom error
            catch (ErrorException e){
                System.out.println("OOPS!!! " + e.getMessage());
            }
            //Handle tick and cross index error
            catch (NumberFormatException e){
                System.out.println("Please enter a valid number.");
            }
            //Handle other unexpected error
            catch (Exception e){
                System.out.println("Something went wrong. Please try again.");
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

    // Add task to the task-list
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
