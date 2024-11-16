package KLBot.Parser;

import KLBot.KLBotException;
import KLBot.TaskList.Deadline;
import KLBot.TaskList.Event;
import KLBot.TaskList.Task;
import KLBot.TaskList.ToDo;

public class Parser {
    private static String errorTask;

    public boolean isExit(String userInput) {
        return "bye".equalsIgnoreCase(userInput);
    }

    public boolean isShowList(String userInput) {
        return "list".equalsIgnoreCase(userInput);
    }

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

    private static boolean hasDescription(String userInput) {
        if (userInput.toLowerCase().startsWith("todo")) {
            String[] todoDescription = userInput.split(" ");
            if (todoDescription.length <= 1) {
                errorTask = "Oops! It seems there’s a small issue with your To Do task description. Please make sure it follows the format: 'todo borrow book'. 😊";
                return false;
            }
            return true;
        }
        if (userInput.toLowerCase().startsWith("deadline")) {
            String[] deadlineDescription = userInput.split(" /by ");
            if (deadlineDescription.length != 2) {
                errorTask = "Oh no! There’s a little hiccup with your Deadline task description. Please follow this format: 'deadline return book /by Sunday'. 📝";
                return false;
            }
            return true;
        }
        if (userInput.toLowerCase().startsWith("event")) {
            String[] eventDescription = userInput.split(" /from | /to ");
            if (eventDescription.length != 3) {
                errorTask = "Oops! There’s a problem with your Event task description. Please follow this format: 'event project meeting /from Mon 2pm /to 4pm'. 🎉";
                return false;
            }
            return true;
        }
        return false;
    }
}

