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

            // Check for taskList Command & output item inside taskList
            else if (command[0].equalsIgnoreCase("taskList")){
                if (listNum == 0){
                    System.out.println("No Item in current taskList, you may add your taskList item.");
                }
                else{
                    System.out.println("Here are the task in you list:");
                    for (int i = 0; i < listNum; i++){
                        System.out.println((i+1) + ". " + taskList[i]);
                    }
                }
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

            // Add item in the taskList
            else{
                // Task List is less than 100
                if(listNum < 100){
                   taskList[listNum] = new Task(input);
                   listNum++;
                    //Echo item add in the taskList
                    System.out.println("Added: " + input);
                }
                else{
                    System.out.println("Sorry, but there are more than 100 tasks in this list.");
                }
            }
        }

        // Close Scan
        scan.close();
    }
}
