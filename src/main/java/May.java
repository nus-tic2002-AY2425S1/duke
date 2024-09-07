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


            // Check for Exit command & output exit command message
            if(input.equalsIgnoreCase("bye")){
                /*Exit Message*/
                System.out.println("Bye. Hope to see you again soon!");
                break;
            }

            // Check for taskList Command & output item inside taskList
            else if (input.equalsIgnoreCase("taskList")){
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

            // Add item in the taskList
            else{
                taskList[listNum] = input;
                listNum++;

                //Echo item add in the taskList
                System.out.println("Added: " + input);
            }
        }

        // Close Scan
        scan.close();
    }
}
