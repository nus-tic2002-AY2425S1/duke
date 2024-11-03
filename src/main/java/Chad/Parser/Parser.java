package Chad.Parser;

import Chad.Command.*;
import Chad.Exception.ChadException;
import Chad.TaskList.*;

public class Parser {

    // Method to parse the user input and return the corresponding Command object
    public static Command parse(String fullCommand) throws ChadException {
        String[] parts = fullCommand.split(" ", 2);
        String commandWord = parts[0]; // The first part is the command

        switch (commandWord) {
            case "todo":
                return createTodo(parts);

            case "deadline":
                return createDeadline(parts);

            case "event":
                return createEvent(parts);

            case "delete":
                return createDelete(parts);

            case "mark":
                return createMark(parts);

            case "unmark":
                return createUnmark(parts);

            case "bye":
                return new ExitCommand(); // Exit command

            case "list":
                return new ListCommand(); // List command

            default:
                return createGeneralTask(fullCommand); // Add normal task
        }
    }

    private static Command createTodo(String[] parts) throws ChadException {
        validateTaskDescription(parts);
        String todoDescription = parts[1];
        return new AddCommand(new Todo(todoDescription));
    }

    private static Command createDeadline(String[] parts) throws ChadException {
        if (parts.length < 2 || !parts[1].contains("/by")) {
            throw new ChadException("Invalid deadline format. Expected: deadline task /by time.");
        }
        
        String[] deadlineParts = parts[1].split("/by", 2);
        if (deadlineParts.length < 2) {
            throw new ChadException("Deadline must have a '/by' time.");
        }
        
        String deadlineDescription = deadlineParts[0].trim();
        String deadlineTime = deadlineParts[1].trim();
        
        return new AddCommand(new Deadline(deadlineDescription, deadlineTime));
    }

    private static Command createEvent(String[] parts) throws ChadException {
        if (parts.length < 2 || !parts[1].contains("/from") || !parts[1].contains("/to")) {
            throw new ChadException("Invalid event format. Expected: event task /from time /to time.");
        }
        
        String[] fromToParts = parts[1].split("/to", 2);
        String eventDescription = fromToParts[0].split("/from", 2)[0].trim();
        String eventTimeFrom = fromToParts[0].split("/from", 2)[1].trim();
        String eventTimeTo = fromToParts[1].trim();
        
        return new AddCommand(new Event(eventDescription, eventTimeFrom, eventTimeTo));
    }

    private static Command createDelete(String[] parts) throws ChadException {
        validateTaskIndex(parts);
        int index = Integer.parseInt(parts[1]) - 1;  // Convert to zero-based index
        return new DeleteCommand(index);
    }

    private static Command createMark(String[] parts) throws ChadException {
        validateTaskIndex(parts);
        int index = Integer.parseInt(parts[1]) - 1;  // Convert to zero-based index
        return new MarkTask(index);
    }

    private static Command createUnmark(String[] parts) throws ChadException {
        validateTaskIndex(parts);
        int index = Integer.parseInt(parts[1]) - 1;  // Convert to zero-based index
        return new UnmarkTask(index);
    }

    private static Command createGeneralTask(String inpuString) throws ChadException {
        //parts=inpuString
        //validateTaskDescription(parts);
        //String generalTaskDescription = parts[1];
        return new AddCommand(new Task(inpuString));
    }

    // Helper method to validate if the task description is provided
    private static void validateTaskDescription(String[] parts) throws ChadException {
        if (parts.length < 2) {
            throw new ChadException("Task description cannot be empty.");
        }
    }

    // Helper method to validate task index
    private static void validateTaskIndex(String[] parts) throws ChadException {
        if (parts.length < 2) {
            throw new ChadException("Please provide a task index.");
        }
    }
}
