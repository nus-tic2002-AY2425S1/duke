import java.util.*;
public class Ruan {
    public static void main(String[] args) {
        // String logo = " ____        _        \n"
        //         + "|  _ \\ _   _| | _____ \n"
        //         + "| | | | | | | |/ / _ \\\n"
        //         + "| |_| | |_| |   <  __/\n"
        //         + "|____/ \\__,_|_|\\_\\___|\n";
        // System.out.println("Hello from\n" + logo);

        String line = "";
        try(Scanner userInput = new Scanner(System.in)){

            System.out.println("  -----------------------------------");
            System.out.println("  Hello, I'm Ruan\n  What can I do for you?");
            System.out.println("  -----------------------------------");

            while(!line.trim().toLowerCase().equals("bye")){
                line = userInput.nextLine();
                String lineOut = line;
                if(line.trim().toLowerCase().equals("bye")){
                    lineOut = "Bye. Hope to see you again soon!";
                }

                System.out.println("  -----------------------------------");
                System.out.println("  "+lineOut);
                System.out.println("  -----------------------------------");
                
            }
        }
    }
}
