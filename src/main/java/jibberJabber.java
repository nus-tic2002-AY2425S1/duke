import java.util.Arrays;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class jibberJabber {
    public static void main(String[] args) {
        // Welcome message
        message.printWelcomeMessage("Jibber Jabber");

        // Initializationgit 
        int sizeOfArray = 100;
        String[] todoArray = new String[sizeOfArray];

        // (Wk 3) Level-1 capture task to complete from user input
        Scanner scanner = new Scanner(System.in);
        String todoTask = "";
        // Regex code below adopted from https://www.w3schools.com/java/java_regex.asp
        Pattern byePattern = Pattern.compile("bye", Pattern.CASE_INSENSITIVE);
        Pattern listPattern = Pattern.compile("list", Pattern.CASE_INSENSITIVE);

        for (int counter = 0; counter < sizeOfArray; counter++){
            todoTask = scanner.nextLine();
            // Check if the input value is "bye" or "list"
            Matcher byeText = byePattern.matcher(todoTask);
            Matcher listText = listPattern.matcher(todoTask);

            if (byeText.find()){
                message.printEndingMessage();
                break;
            } else if (listText.find()){
                // (Wk 3) Level-2 Check for "list" key word, then print task list
                task.printTaskList(todoArray);
                continue;
            }

            task inputTask = new task(todoTask);
            todoArray[counter] = inputTask.getTaskName();
            task.printAddedTask(todoArray[counter]);
        }
    }
}
