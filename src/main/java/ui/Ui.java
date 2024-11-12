package ui;

import common.Constants;
import common.Messages;
import exception.CommandException;
import task.Task;
import task.TaskList;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Represents the user interface that is responsible for handling interactions with the user.
 * It handles reading the user input and displaying messages to the user.
 */
public class Ui {

    private static final String LINE = "____________________________________________________________";

    private final Scanner in;

    /**
     * Constructs a new Ui instance with a Scanner for reading user input.
     */
    public Ui() {
        this.in = new Scanner(System.in);
    }

    /**
     * Formats a string to create a specified number of leading spaces.
     *
     * @param numberOfSpace the number of spaces to format.
     * @return a string containing the specified number of leading spaces.
     */
    // Solution below adapted from https://stackoverflow.com/questions/1073787/print-spaces-with-string-format
    // https://stackoverflow.com/questions/69576641/why-would-you-use-a-stringbuilder-method-over-a-string-in-java
    public String formatSpace(int numberOfSpace) {
        // String space = String.format("%" + numberOfSpace + "s", "");
        String format = Constants.PERCENT + numberOfSpace + Constants.S;
        return String.format(format, Constants.EMPTY_STRING);
    }

    /**
     * Determines the appropriate number of spaces to insert before text or lines.
     * This is dependent on the context of the output, i.e. whether the space is for a horizontal line or for tasks.
     * It is used to align the output in the command-line.
     *
     * @param isLine indicates whether the space is to come before a horizontal line.
     * @param isTask indicates whether the space is to come before a a line of text representing a task.
     * @return a string containing the appropriate number of leading spaces.
     */
    public String getSpace(boolean isLine, boolean isTask) {
        // If space is to come before a horizontal line, use "    "
        if (isLine && !isTask) {
            return formatSpace(4);      // Space before horizontal line
        } else if (!isLine && isTask) {        // If space is to come before a line of text, use "     "
            // For printing task
            return formatSpace(7);      // Space before task text
        } else if (!isLine && !isTask) {
            return formatSpace(5);      // General space
        } else {
            return Constants.EMPTY_STRING;      // No space
        }
    }

    /**
     * Creates a horizontal line with the appropriate number of leading spaces before the horizontal line.
     *
     * @return a formatted string representing the horizontal line with leading spaces.
     */
    public String getLine() {
        String space = getSpace(true, false);
        return space + LINE;
    }

    /**
     * Displays a horizontal line in the command-line.
     */
    public void showLine() {
        System.out.println(getLine());
    }

    /**
     * Prints a horizontal line, followed by the message(s), and ends with another horizontal line.
     * There will be spaces before each line.
     *
     * @param <T>      represents the type of the messages.
     * @param messages represents the list of message(s) to print. It can be an array or ArrayList.
     */
    // Solution below inspired by https://stackoverflow.com/questions/2914695/how-can-you-write-a-function-that-accepts-multiple-types
    public <T> void printMessage(T messages) {
        // System.out.println("In printMessage");
        String line = getLine();        // includes space before line
        String space = getSpace(false, false);

        // StringBuilder stringBuilder = new StringBuilder();
        StringBuilder stringBuilder = new StringBuilder().append(line).append(Constants.NEW_LINE);

        // System.out.println(messageList.getClass());

        // Checks if the messageList is an array. Iterates and appends each item to the StringBuilder
        // https://stackoverflow.com/questions/40899820/arrays-check-if-object-is-an-array
        if (messages.getClass().isArray()) {
            for (int i = 0; i < Array.getLength(messages); i++) {
                stringBuilder.append(space).append(Array.get(messages, i)).append(Constants.NEW_LINE);
            }
        }

        // Checks if the messageList is an ArrayList. Iterates and appends each item to the StringBuilder
        // https://stackoverflow.com/questions/14674027/checking-if-object-is-instance-of-listobject
        else if (messages instanceof ArrayList<?>) {
            for (int i = 0; i < ((ArrayList<?>) messages).size(); i++) {
                stringBuilder.append(space).append(((ArrayList<?>) messages).get(i)).append(Constants.NEW_LINE);
            }
        }

        // Append the closing line after the message
        String text = stringBuilder.append(line).toString();

        // Print out the final formatted message to the command line
        System.out.println(text);
    }

    /**
     * Prints a greeting message to the command-line (when the application starts).
     */
    public void showWelcome() {
        String[] messages = {Messages.MESSAGE_WELCOME};
        printMessage(messages);
    }

    /**
     * Prints an exit message to the command-line to indicate that the chatbot is exiting.
     */
    public void showBye() {
        String[] messages = {Messages.MESSAGE_GOODBYE};
        printMessage(messages);
    }

    /**
     * Reads a line of input from the user and trims any leading or trailing whitespace.
     * It ensures that the input is not empty.
     * When the user provides an empty input, it will display an error message,
     * requesting for the user to enter a non-empty input.
     * It will continue to prompt the user until a valid input (non-empty string) is received.
     *
     * @return the trimmed user input (without any leading whitespaces).
     * @throws CommandException when the user inputs an invalid string.
     */
    // https://github.com/se-edu/addressbook-level2/blob/master/src/seedu/addressbook/ui/TextUi.java#L80
    public String readInput() throws CommandException {
        String userInput;
        // Keep asking for input until a non-empty string is entered
        do {
            userInput = in.nextLine().trim(); // Read and trim the input
            if (userInput.isEmpty()) {
                throw new CommandException(
                    Messages.ERROR_EMPTY_INPUT,
                    Messages.VALID_COMMANDS
                );
            }
        } while (userInput.isEmpty());          // Continue looping as long as the input is empty
        return userInput;       // Return the valid non-empty user input
    }

    /**
     * Displays an error message to the user.
     *
     * @param errorMessage represents a list of error messages to display.
     */
    public void showError(ArrayList<String> errorMessage) {
        printMessage(errorMessage);
    }

    // Print empty list message when given task list is empty
    public void printEmptyListMessage(TaskList taskList, String emptyListMessage) {
        if (taskList.isTaskListEmpty()) {
            String[] message = {emptyListMessage};
            printMessage(message);
        }
    }

    public ArrayList<String> getTaskMessages(String preMessage, TaskList taskList) {
        ArrayList<String> messages = new ArrayList<>(List.of(preMessage));
        for (int i = 0; i < taskList.getSize(); i++) {
            Task current = taskList.getTask(i);
            String index = Integer.toString(i + 1);
            String line = index + Constants.DOT_SPACE + current;
            messages.add(line);
        }
        return messages;
    }
}
