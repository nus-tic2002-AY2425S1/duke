package command;

import exception.DukeException;
import tasks.Task;

import java.util.List;

/**
 * Interface for handling user input commands
 * Solution below adapted from https://www.geeksforgeeks.org/factory-method-design-pattern-in-java/
 */
public interface CommandHandler {
    /**
     * Handle user input and perform neccessary actions and update to TaskList
     * @param userInput User input in String
     * @param tasks The list of tasks to have modification based on the handler
     * @throws DukeException DukeException if respective handling has error
     */
    void handle(String userInput, List<Task> tasks) throws DukeException;
}
