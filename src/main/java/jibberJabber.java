import java.util.Arrays;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class jibberJabber {
    public static void main(String[] args) {
        // Welcome message
        message.printWelcomeMessage("Jibber Jabber");

        // (Wk 3) Level-1 capture task to complete from user input
        Scanner scanner = new Scanner(System.in);
        String todoTask = "";
        // Regex code below adopted from https://www.w3schools.com/java/java_regex.asp
        Pattern stringPattern = Pattern.compile("bye", Pattern.CASE_INSENSITIVE);
        boolean matchFound = false;
        while(true) {
            todoTask = scanner.nextLine();
            // Check if the input value is "bye"
            Matcher textToCheck = stringPattern.matcher(todoTask);
            matchFound = textToCheck.find();
            if (matchFound){
                message.printEndingMessage();
                break;
            }
            task.printTask(todoTask);
        }
    }
}
