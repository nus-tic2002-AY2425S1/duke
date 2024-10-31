import java.util.Scanner;
import java.util.ArrayList;
import java.time.LocalDateTime;


public class May {

    // Using ArrayList
    private static ArrayList<Task> taskList = new ArrayList<>();
    private static ManageTask taskManager;

    public static void main(String[] args) {

        // Load tasks from file
        taskList = SaveAndLoadTask.loadTasks();
        taskManager = new ManageTask(taskList);

        //Scanner
        Scanner scan = new Scanner(System.in);
        String input;

        /*Welcome Message*/
        WelcomeMessage.printWelcomeMessage();

        while(true){
            try {
                // Scan for user input
                input = scan.nextLine();

                // split the input into two put one for task status other part for description
                String[] command = input.split(" ", 2);

                assert command.length > 0 : "Command should not be empty";

                // Check for Exit command & output exit command message
                if (command[0].equalsIgnoreCase("bye")) {
                    /*Exit Message*/
                    System.out.println("Bye. Hope to see you again soon!");
                    break;
                }

                // Add a normal task (not used in the updated design, can be removed)
                else if (command[0].equalsIgnoreCase("add") && command.length > 1) {
                    taskManager.addTask(new Task(command[1]));
                }

                // Check for taskList Command & output item inside taskList
                else if (command[0].equalsIgnoreCase("taskList")) {
                    taskManager.displayTaskList();
                }

                // Mark the task as done
                else if (command[0].equalsIgnoreCase("done")) {
                    assert command.length > 1 : "Task index required for tick command";
                    int taskIndex = Integer.parseInt(command[1]) - 1;
                    assert taskIndex >= 0 && taskIndex < taskList.size() : "Task index out of bounds";
                    taskList.get(taskIndex).markAsDone();
                    System.out.println("Congratulations! You have completed the task! ");
                    System.out.println(" " + taskList.get(taskIndex));
                    SaveAndLoadTask.saveTasks(taskList);
                }

                // Mark the task as cross since it has not done
                else if (command[0].equalsIgnoreCase("undone")) {
                    assert command.length > 1 : "Task index required for cross command";
                    int taskIndex = Integer.parseInt(command[1]) - 1;
                    assert taskIndex >= 0 && taskIndex < taskList.size() : "Task index out of bounds";
                    taskList.get(taskIndex).unmarkAsDone();
                    System.out.println("Task have not done, Kindly complete the task. ");
                    System.out.println(" " + taskList.get(taskIndex));
                    SaveAndLoadTask.saveTasks(taskList);  // Save after unmarking as done
                }

                // Create todo-task
                else if (command[0].equalsIgnoreCase("todo")) {
                    if (command.length < 2 || command[1].trim().isEmpty()) {
                        throw new ErrorException(" 'todo' command cannot be empty.");
                    }
                    taskManager.addOrUpdateTask(new ToDo(command[1]));
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
                    LocalDateTime deadlineTime = Parser.parseDateTime(doneBy[1].trim());
                    taskManager.addOrUpdateTask(new Deadline(doneBy[0].trim(), deadlineTime));
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
                    LocalDateTime fromTime = Parser.parseDateTime(eventTime[1].trim());
                    LocalDateTime toTime = Parser.parseDateTime(eventTime[2].trim());
                    taskManager.addOrUpdateTask(new Event(eventTime[0].trim(), fromTime, toTime));
                }

                // Delete task from the list
                else if (command[0].equalsIgnoreCase("delete")) {
                    int taskIndex = Integer.parseInt(command[1]) - 1;
                    if (taskIndex < taskList.size()) {
                        Task removedTask = taskList.remove(taskIndex);
                        System.out.println("Noted. I've removed this task:");
                        System.out.println(" " + removedTask);
                        System.out.println("Now you have " + taskList.size() + " tasks in the list.");
                        SaveAndLoadTask.saveTasks(taskList);
                    } else {
                        throw new ErrorException("Invalid task index. Please enter a valid task index.");
                    }
                }

                // Find task with keyword
                else if (command[0].equalsIgnoreCase("find")){
                    if (command.length < 2 || command[1].trim().isEmpty()){
                        throw new ErrorException("'find' command cannot be empty.");
                    }
                    String keyword = command[1].trim();
                    taskManager.findTask(keyword);
                }

                // Check for task that had completed in the past week
                else if (command[0].equalsIgnoreCase("completed")) {
                    ArrayList<Task> completedTasks = taskManager.getTasksCompletedInPastWeek();
                    if (completedTasks.isEmpty()) {
                        System.out.println("You have not completed any tasks in the past week.");
                    } else {
                        System.out.println("Tasks completed in the past week:");
                        for (Task task : completedTasks) {
                            System.out.println(task);
                        }
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
}
