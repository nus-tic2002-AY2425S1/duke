import java.util.Arrays;
import java.util.Scanner;

public class Javaro {
    
    public static void space(boolean isLine) {
        String space = "    ";

        // If space is to come before a horizontal line, use "    "
        if (isLine) {
            System.out.print(space);
        
        // If space is to come before a line of text, use "     "
        } else {
            System.out.print(space + " ");
        }
    }

    // This function prints a horizontal line
    public static void printLine() {
        space(true);
        String line = "____________________________________________________________";
        System.out.println(line);
    }

    // Chatbot greets the user
    public static void greet() {
        printLine();
        String chatbotName = "Javaro";
        
        space(false);
        System.out.println("Hello, I'm " + chatbotName + ".");
        
        space(false);
        System.out.println("What can I do for you?");
        printLine();
    }

    // Chatbot exits
    public static void exit() {
        printLine();
        
        space(false);
        System.out.println("Bye. Hope to see you again soon!");
        printLine();
    }

    // int length = list.length;
    // String[] result = new String[length + 1];
    // for (int i = 0; i < length; i++) {
    //     result[i] = list[i];
    // }
    // result[length] = email;
    // return result;


    // double[] newOverseas = Arrays.copyOf(overseas, overseas.length + 1);
    // // System.out.println("this is new overseas ");
    // // System.out.println(Arrays.toString(newOverseas));
    
    // newOverseas[overseas.length] = expense;
    // // System.out.println("this is new overseas after adding expense ");
    // // System.out.println(Arrays.toString(newOverseas));
    
    // return newOverseas;


    // 1st element has index 0. Length 1
    public static String[] addToList(String[] list, String item) {
        int listLength = list.length;
        String[] newList = Arrays.copyOf(list, listLength + 1);
        newList[listLength] = item;
        return newList;
    }

    public static void printAddMessage(String item) {
        printLine();
        space(false);
        System.out.println("added: " + item);
        printLine();
    }

    public static void printList(String[] list) {
        // System.out.println(Arrays.toString(list));

        printLine();
        for (int i = 0; i < list.length; i++) {
            String index = Integer.toString(i + 1);
            String line = index + ". " + list[i];
            space(false);
            System.out.println(line);
        }
        printLine();
    }

    // This function echos commands entered by the user, and exits when the user types the command bye.
    public static void echo() {
        Scanner in = new Scanner(System.in);
        String input = in.nextLine();

        // Assume there will be no more than 100 tasks.
        String[] list = new String[0];

        while (input.equals("bye") == false) {
            
            if (input.equals("list")) {
                printList(list);

            } else {
                list = addToList(list, input);
                printAddMessage(input);

            }

            // Ask the user for the next input
            input = in.nextLine();
        }
    }

    public static void main(String[] args) {
        greet();
        echo();
        exit();
    }
}
