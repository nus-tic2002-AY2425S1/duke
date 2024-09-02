import java.util.Scanner;


public class May {

    /*Store User Input*/
    private static String[] list = new String[100];
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

            // Check for Exit command
            if(input.equalsIgnoreCase("bye")){
                /*Exit Message*/
                System.out.println("Bye. Hope to see you again soon!");
                break;
            }

            // Check for List Command & output item inside List
            else if (input.equalsIgnoreCase("list")){
                if (listNum == 0){
                    System.out.println("No Item in current list, you may add your list item.");
                }
                else{
                    for (int i = 0; i < listNum; i++){
                        System.out.println((i+1) + ". " + list[i]);
                    }
                }
            }

            // Add item in the list
            else{
                list[listNum] = input;
                listNum++;

                //Echo item add in the list
                System.out.println("Added: " + input);
            }
        }

        // Close Scan
        scan.close();
    }
}
