package ui;

import common.Constants;
import common.Messages;
import task.Task;
import task.TaskList;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents the user interface that is responsible for handling interactions with the user.
 * It handles reading the user input and displaying messages to the user.
 */
public class Ui {

    // Consolidate the strings to print into one variable - javaroResponse
    private String javaroResponse;

    public Ui() {

    }

    /**
     * Generates a response for the user's chat message.
     */
    public String getJavaroResponse() {
        return javaroResponse;
    }

    public void setJavaroResponse(String javaroResponse) {
        this.javaroResponse = javaroResponse;
    }

    /**
     * Formats a string to create a specified number of leading spaces.
     *
     * @param numberOfSpaces the number of spaces to format.
     * @return a string containing the specified number of leading spaces.
     */
    // Solution below adapted from https://stackoverflow.com/questions/1073787/print-spaces-with-string-format
    // https://stackoverflow.com/questions/69576641/why-would-you-use-a-stringbuilder-method-over-a-string-in-java
    public String formatSpace(int numberOfSpaces) {
        String format = Constants.PERCENT + numberOfSpaces + Constants.S;
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
            return formatSpace(Constants.FOUR);      // Space before horizontal line
        } else if (!isLine && isTask) {        // If space is to come before a line of text, use "     "
            // For printing task
            return formatSpace(Constants.TWO);      // Space before task text
        } else if (!isLine) {
            return formatSpace(Constants.FIVE);      // General space
        } else {
            return Constants.EMPTY_STRING;      // No space
        }
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
        StringBuilder stringBuilder = new StringBuilder();

        // Checks if the messageList is an array. Iterates and appends each item to the StringBuilder
        // https://stackoverflow.com/questions/40899820/arrays-check-if-object-is-an-array
        if (messages.getClass().isArray()) {
            for (int i = 0; i < Array.getLength(messages); i++) {
                stringBuilder.append(Array.get(messages, i)).append(Constants.NEW_LINE);
            }
        } else if (messages instanceof ArrayList<?>) {        // Checks if the messageList is an ArrayList. Iterates and appends each item to the StringBuilder. Reference: https://stackoverflow.com/questions/14674027/checking-if-object-is-instance-of-listobject
            for (int i = 0; i < ((ArrayList<?>) messages).size(); i++) {
                stringBuilder.append(((ArrayList<?>) messages).get(i)).append(Constants.NEW_LINE);
            }
        }

        // Append the closing line after the message
        String text = stringBuilder.toString();

        // Set the final formatted message as the response from Javaro
        setJavaroResponse(text);
    }

    public void showBye() {
        setJavaroResponse(Messages.MESSAGE_GOODBYE);
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
    public void printTaskListMessage(TaskList taskList, String emptyListMessage, String successMessage) {
        // Check if there are no tasks in the given task list. The given task list is already filtered for the required criteria
        if (taskList.isEmpty()) {
            String[] message = {emptyListMessage};
            printMessage(message);
        } else {
            // Prepare the message to display to the user
            // Iterate through the task list and add tasks scheduled on the specified date
            ArrayList<String> messages = getTaskMessages(successMessage, taskList);

            // Print the message list containing tasks scheduled on the specified date
            printMessage(messages);
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
