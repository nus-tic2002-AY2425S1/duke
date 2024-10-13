import java.util.Scanner;
import java.util.ArrayList;


public class May {

    /*Store User Input*/
    //private static Task[] taskList = new Task[100];
    //private static int listNum = 0;

    // Using ArrayList
    private static ArrayList<Task> taskList = new ArrayList<>();

    public static void main(String[] args) {

        // Load tasks from file
        taskList = SaveTask.loadTasks();

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
                    if (taskIndex < taskList.size()) {
                        taskList.get(taskIndex).markAsDone();
                        System.out.println("Congratulations! You have completed the task! ");
                        System.out.println(" " + taskList.get(taskIndex));
                        SaveTask.saveTasks(taskList);
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
                    if (taskIndex < taskList.size()) {
                        taskList.get(taskIndex).unmarkAsDone();
                        System.out.println("Task have not done, Kindly complete the task. ");
                        System.out.println(" " + taskList.get(taskIndex));
                        SaveTask.saveTasks(taskList);  // Save after unmarking as done
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

                // Delete task from the list
                else if (command[0].equalsIgnoreCase("delete")) {
                    int taskIndex = Integer.parseInt(command[1]) - 1;
                    if (taskIndex < taskList.size()) {
                        Task removedTask = taskList.remove(taskIndex);
                        System.out.println("Noted. I've removed this task:");
                        System.out.println(" " + removedTask);
                        System.out.println("Now you have " + taskList.size() + " tasks in the list.");
                        SaveTask.saveTasks(taskList);
                    } else {
                        throw new ErrorException("Invalid task index. Please enter a valid task index.");
                    }
                }

                // Unknown command
                else {
                    throw new ErrorException("I'm sorry, I don't recognize that command.");
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
        for (int i = 0; i < taskList.size(); i++){
            if (taskList.get(i).taskName.equals(newTask.taskName)){
                taskList.set(i, newTask);
                System.out.println("Got it, I've updated this task:");
                System.out.println(" " + taskList.get(i));
                System.out.println("Now you have " + taskList.size() + " tasks in the list.");
                SaveTask.saveTasks(taskList);
                return;
            }
        }
        addTask(newTask);
    }

    // Add task to the task-list
    private static void addTask(Task task){
        taskList.add(task);
        System.out.println("Got it, I've added this task:");
        System.out.println(" " + taskList.get(taskList.size() - 1));
        System.out.println("Now you have " + taskList.size()  + " tasks in the task list.");
        SaveTask.saveTasks(taskList);
    }

    //display the task in the task list
    private static void displayTaskList() {
        if (taskList.isEmpty()) {
            System.out.println("No tasks in the list.");
        } else {
            System.out.println("Here are the tasks in your list:");
            for (int i = 0; i < taskList.size(); i++) {
                System.out.println((i + 1) + ". " + taskList.get(i));
            }
        }
    }
}
