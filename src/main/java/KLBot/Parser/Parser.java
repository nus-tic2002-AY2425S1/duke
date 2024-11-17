package KLBot.Parser;

import KLBot.KLBotException;
import KLBot.TaskList.Deadline;
import KLBot.TaskList.Event;
import KLBot.TaskList.Task;
import KLBot.TaskList.ToDo;

/**
 * The Parser class is responsible for interpreting the user input,
 * identifying the type of task (ToDo, Deadline, Event), and creating
 * the corresponding task object.
 */
public class Parser {
    private static String errorTask;

    /**
     * Checks if the user input is a command to exit the program.
     *
     * @param userInput The user input string.
     * @return true if the input is "bye".
     */
    public boolean isExit(String userInput) {
        return "bye".equalsIgnoreCase(userInput);
    }

    /**
     * Checks if the user input is a command to show the task list.
     *
     * @param userInput The user input string.
     * @return true if the input is "list".
     */
    public boolean isShowList(String userInput) {
        return "list".equalsIgnoreCase(userInput);
    }

    /**
     * Parses the user input to create a task (ToDo, Deadline, or Event) based on the command.
     * It ensures that the description for each task type is valid.
     *
     * @param userInput The user input string containing the task command and description.
     * @return A Task object (ToDo, Deadline, or Event) based on the user input, or null if the input is invalid.
     * @throws KLBotException If there is an issue with the task description or format.
     */
    public Task createTask(String userInput) throws KLBotException {
        try {
            if (!hasDescription(userInput)) {
                throw new KLBotException(errorTask);
            }
            if (userInput.startsWith("todo")) {
                return new ToDo(userInput.replace("todo ", "").trim());
            } else if (userInput.startsWith("deadline")) {
                String[] parts = userInput.replace("deadline ", "").split("/by", 2);
                if (parts.length < 2) return null;
                return new Deadline(parts[0].trim(), parts[1].trim());
            } else if (userInput.startsWith("event")) {
                String[] parts = userInput.replace("event ", "").split("/", 3);
                if (parts.length < 3) return null;
                return new Event(parts[0].trim(), parts[1].replace("from ", "").trim(), parts[2].replace("to ", "").trim());
            }
        } catch (KLBotException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    /**
     * Validates if the user input has a description
     *
     * @param userInput The user input string containing the task command and description.
     * @return true if the description exists.
     */
    private static boolean hasDescription(String userInput) {
        if (userInput.toLowerCase().startsWith("todo")) {
            String[] todoDescription = userInput.split(" ");
            if (todoDescription.length <= 1) {
                errorTask = "Oops! It seems there’s a small issue with your To Do task description. Please make sure it follows the format: 'todo borrow book'.";
                return false;
            }
            return true;
        }
        if (userInput.toLowerCase().startsWith("deadline")) {
            String[] deadlineDescription = userInput.split(" /by ");
            if (deadlineDescription.length != 2) {
                errorTask = "Oh no! There’s a little hiccup with your Deadline task description. Please follow this format: 'deadline return book /by Sunday'.";
                return false;
            }
            return true;
        }
        if (userInput.toLowerCase().startsWith("event")) {
            String[] eventDescription = userInput.split(" /from | /to ");
            if (eventDescription.length != 3) {
                errorTask = "Oops! There’s a problem with your Event task description. Please follow this format: 'event project meeting /from Mon 2pm /to 4pm'.";
                return false;
            }
            return true;
        }
        return false;
    }
}

