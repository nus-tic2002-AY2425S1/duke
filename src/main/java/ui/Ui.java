package ui;
import java.util.Scanner;

import common.Messages;

import java.lang.reflect.Array;
import java.util.ArrayList;

// deals with interactions with the user
public class Ui {

    // Use constant variables to store the keyword commands
    // https://www.javatpoint.com/java-constant
    private static final String LINE = "____________________________________________________________";
    private static final String NEW_LINE = "\n";

    private final Scanner in;

    public Ui() {
        this.in = new Scanner(System.in);
    }

    // Solution below adapted from https://stackoverflow.com/questions/1073787/print-spaces-with-string-format
    // https://stackoverflow.com/questions/69576641/why-would-you-use-a-stringbuilder-method-over-a-string-in-java
    public String formatSpace(int numberOfSpace) {
        // String space = String.format("%" + numberOfSpace + "s", "");
        String format = "%" + numberOfSpace + "s";
        String space = String.format(format, "");
        return space;
    }

    public String getSpace(boolean isLine, boolean isTask) {
        // If space is to come before a horizontal line, use "    "
        if (isLine == true && isTask == false) {
            return formatSpace(4);
        } else if (isLine == false && isTask == true) {        // If space is to come before a line of text, use "     "
            // For printing task
            return formatSpace(7);
        } else if (isLine == false && isTask == false) {
            return formatSpace(5);
        } else {
            return "";
        }
    }

    public String getLine() {
        String space = getSpace(true, false);
        return space + LINE;
    }

    public void showLine() {
        System.out.println(getLine());
    }

    // Prints a horizontal line, followed by the message (may be one or multiple lines), then another horizontal line
    // https://stackoverflow.com/questions/2914695/how-can-you-write-a-function-that-accepts-multiple-types
    public <T> void printMessage(T messageList) {
        // System.out.println("In printMessage");
        String line = getLine();        // includes space before line
        String space = getSpace(false, false);
        
        // StringBuilder stringBuilder = new StringBuilder();
        StringBuilder stringBuilder = new StringBuilder().append(line).append(NEW_LINE);
        
        // System.out.println(messageList.getClass());

        // https://stackoverflow.com/questions/40899820/arrays-check-if-object-is-an-array
        if (messageList.getClass().isArray()) {
            for (int i = 0; i < Array.getLength(messageList); i++) {
                stringBuilder.append(space)
                             .append(Array.get(messageList, i))
                             .append(NEW_LINE);
            }
        } 
        
        // https://stackoverflow.com/questions/14674027/checking-if-object-is-instance-of-listobject
        else if (messageList instanceof ArrayList<?>) {
            for (int i = 0; i < ((ArrayList<?>) messageList).size(); i++) {
                stringBuilder.append(space).append(
                                                ((ArrayList<?>) messageList)
                                                .get(i)
                                            )
                                           .append(NEW_LINE);
            }
        }

        // String text = stringBuilder.toString();
        String text = stringBuilder.append(line).toString();
        
        System.out.println(text);
    }

    // Print greeting message
    public void showWelcome() {
        String[] messageList = {Messages.MESSAGE_WELCOME};
        printMessage(messageList);
    }

    // Chatbot exits
    public void showBye() {
        String[] messageList = {Messages.MESSAGE_GOODBYE};
        printMessage(messageList);
    }

    // https://github.com/se-edu/addressbook-level2/blob/master/src/seedu/addressbook/ui/TextUi.java#L80
    public String readInput() {
        String userInput = in.nextLine().trim();
        while (userInput.isEmpty()) {
            userInput = in.nextLine().trim();
        }
        return userInput;
    }

    // TODO: Add code for displaying error message
    public void showError(ArrayList<String> e) {
        printMessage(e);
    }

}
