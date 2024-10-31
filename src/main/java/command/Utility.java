package command;

import exception.DukeException;
import tasks.Task;

import java.util.List;

/**
 * Utility class for providing helper methods for command handling.
 */
public class Utility {

    /**
     * Parse task index from user input
     * @param userInput The user input command string
     * @return The parsed task index
     * @throws DukeException If the index is not a valid index in the task or is an invalid integer
     */
    public static int parseTaskIndex(String userInput) throws DukeException {
        assert userInput != null && !userInput.trim().isEmpty() : "User input cannot be null or empty";
        try {
            String[] parts = userInput.split(" ");
            if (parts.length < 2) {
                throw new DukeException("OOPS!! Please provide a task number!");
            }
            return Integer.parseInt(parts[1]) - 1;
        }  catch (NumberFormatException e) {
            throw new DukeException("OOPS!! The task number must be a valid integer!");
        }
    }

    /**
     * Checks if task index is valid in the TaskList
     * @param taskIndex The task index to be checked
     * @param tasks List of tasks
     * @return Boolean value if task index is valid or not
     */
    public static boolean isValidIndex(int taskIndex, List<Task> tasks){
        assert tasks != null : "Tasks list cannot be null";
        return taskIndex >= 0 && taskIndex < tasks.size();
    }


}
