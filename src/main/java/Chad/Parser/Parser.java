package Chad.Parser;

import Chad.Command.AddCommand;
import Chad.Command.Command;
import Chad.Command.DeleteCommand;
import Chad.Command.ExitCommand;
import Chad.Command.FindCommand;
import Chad.Command.HelpCommand;
import Chad.Command.ListByDateCommand;
import Chad.Command.ListCommand;
import Chad.Command.MarkTaskCommand;
import Chad.Command.StatisticsCommand;
import Chad.Command.UnmarkTaskCommand;
import Chad.Exception.ChadException;
import Chad.TaskList.Deadline;
import Chad.TaskList.Event;
import Chad.TaskList.Task;
import Chad.TaskList.Todo;

public class Parser {

    // Method to parse the user input and return the corresponding Command object
    public static Command parse(String fullCommand) throws ChadException {
        String[] parts = fullCommand.split(" ", 2);
        String commandWord = parts[0]; // The first part is the command
        commandWord=commandWord.toLowerCase();
        // No Fallthrough indicated and no break, because each case is a return will terminate the case
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
            return new ExitCommand(); 
        case "list":
            return createList(fullCommand);
        case "find":
            return createFind(fullCommand);
        case "summary":
            return createStaticsCommand(fullCommand);
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
        String parsedDeadlineTime = ChadDate.parseDate(deadlineTime);

        return new AddCommand(new Deadline(deadlineDescription, parsedDeadlineTime));
    }

    private static Command createEvent(String[] parts) throws ChadException {
        if (parts.length < 2 || !parts[1].contains("/from") || !parts[1].contains("/to")) {
            throw new ChadException("Invalid event format. Expected: event task /from time /to time.");
        }
        
        String[] fromToParts = parts[1].split("/to", 2);
        String eventDescription = fromToParts[0].split("/from", 2)[0].trim();
        String eventTimeFrom = fromToParts[0].split("/from", 2)[1].trim();
        String parsedTimefrom = ChadDate.parseDate(eventTimeFrom);
        String eventTimeTo = fromToParts[1].trim();
        String parsedTimeto = ChadDate.parseDate(eventTimeTo);
        
        return new AddCommand(new Event(eventDescription, parsedTimefrom, parsedTimeto));
    }

    private static Command createDelete(String[] parts) throws ChadException {
        validateTaskIndex(parts);
        int index = Integer.parseInt(parts[1]) - 1;  // Convert to zero-based index
        return new DeleteCommand(index);
    }

    private static Command createList(String fullcommand) throws ChadException {
    
    // Split the command into parts
    String[] parts = fullcommand.split(" ", 2); // Split into at most 2 parts

    // Check if there is a substring beyond "list"
    if (parts.length > 1) {
        String parameter = parts[1].trim(); // Get and trim the "something" part

        if (ChadDate.isDate(parameter)) {
            // If "something" is a date, create a ListByDateCommand
            return new ListByDateCommand(parameter);
        } else {
            return new ListCommand();
        }
    }
    // If there's no "something" part, return a normal ListCommand
    return new ListCommand();
    }
    private static Command createFind(String fullcommand) throws ChadException {
    
        // Split the command into parts
        String[] parts = fullcommand.split(" ", 2); // Split into at most 2 parts
    
        // Check if there is a substring beyond "find"
        if (parts.length > 1) {
            String parameter = parts[1].trim(); // Get and trim the "something" part
            return new FindCommand(parameter);
        }
        // If there's no "something" part, return a normal ListCommand
        return new HelpCommand("FIND");
        }
    private static Command createStaticsCommand(String fullcommand) throws ChadException {
    
            // Split the command into parts
            String[] parts = fullcommand.split(" ", 2); // Split into at most 2 parts
        
            // Check if there is a substring beyond "find"
            if (parts.length > 1) {
                String parameter = parts[1]; // Get and trim the "something" part
                //Ui.showMsg(parameter);
                return new StatisticsCommand(parameter);
                
            }
            // If there's no "something" part, return a normal ListCommand
            return new HelpCommand("SUMMARY");
            }

    private static Command createMark(String[] parts) throws ChadException {
        validateTaskIndex(parts);
        int index = Integer.parseInt(parts[1]) - 1;  // Convert to zero-based index
        return new MarkTaskCommand(index);
    }

    private static Command createUnmark(String[] parts) throws ChadException {
        validateTaskIndex(parts);
        int index = Integer.parseInt(parts[1]) - 1;  // Convert to zero-based index
        return new UnmarkTaskCommand(index);
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
