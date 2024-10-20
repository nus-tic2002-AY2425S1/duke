package command;

import java.util.HashMap;
import java.util.Map;

import exception.DukeException;
import tasks.*;

/**
 * Factory class to generate tasks based on user input commands.
 */
public class CommandFactory {
    public static final Map<String, Task> taskMap = new HashMap<>();
    static {
        taskMap.put("todo", new ToDo(null));
        taskMap.put("event", new Event(null,null,null));
        taskMap.put("deadline", new Deadline(null,null));
    }

    /**
     * Generate a Task object based on user input
     * @param userInput user input in string
     * @return The generate Task object.
     * @throws DukeException If the command is invalid or description is emoty.
     */
    public static Task generateTask(String userInput) throws DukeException {
        String [] commandParts = userInput.split(" ", 2);
        String command = commandParts[0];
        String commandString = commandParts.length == 2 ? commandParts[1] : null;

        Task taskTemplate = taskMap.get(command.toLowerCase());

        if (taskTemplate != null) {
            if (commandString == null || commandString.trim().isEmpty()) {
                throw new DukeException("OOPS!!! The description of a " + command + " cannot be empty");
            }
            return taskTemplate.createTask(commandString);
        } else {
            throw new DukeException("OOPS!!! I don't know what the command \"" + command + "\" means");
        }
    }
}
