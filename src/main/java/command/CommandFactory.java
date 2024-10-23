package command;

import java.util.HashMap;
import java.util.Map;

import exception.DukeException;
import tasks.*;

import static parser.TaskParser.parseTask;

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
        String [] taskParts = parseTask(userInput);

        Task taskTemplate = taskMap.get(taskParts[0]);
        if (taskTemplate != null) {
            if (taskParts[1] == null || taskParts[1].isEmpty()) {
                throw new DukeException("OOPS!!! The description of a " + taskParts[0] + " cannot be empty");
            }
            return taskTemplate.createTask(taskParts[1]);
        } else {
            throw new DukeException("OOPS!!! I don't know what the command \"" + taskParts[0] + "\" means");
        }
    }
}
