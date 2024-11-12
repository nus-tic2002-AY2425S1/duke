import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Parser {

    /**
     * Parser which is the command that determine the type of task we would like to create. For example a normal task
     * or it is an event or deadline task.
     *
     * @param command which is the input command that user input to specific the type of the task with the details.
     * @return a Task object based on the command type either normal task or Event or Deadline task.
     * @throws Exception if the command does not match any know task type
     */
    public static Task parseCommand(String command) throws Exception {

        // Check for normal task command
        if (command.startsWith("add ")) {
            String taskDescription = command.substring(4).trim(); // Extract the task description
            return new Task(taskDescription); // Create a new normal task
        }

        // Check for deadline command
        if (command.startsWith("deadline ")) {
            String[] parts = command.split(" /by ", 2);
            String description = parts[0].substring(9).trim(); // Extract description
            String dateTime = parts[1].trim(); // Extract date string

            // Parse the date and time
            LocalDateTime dateTimeParsed = parseDateTime(dateTime);
            // Create a new Deadline task
            return new Deadline(description, dateTimeParsed);
        }

        // Check for event command
        if (command.startsWith("event ")) {
            String[] parts = command.split(" /from ", 2);
            String description = parts[0].substring(6).trim(); // Extract description
            String[] dateParts = parts[1].split(" /to ", 2);
            String fromDate = dateParts[0].trim(); // Extract start date string
            String toDate = dateParts[1].trim(); // Extract end date string

            // Parse the start and end dates
            LocalDateTime fromDateParsed = parseDateTime(fromDate);
            LocalDateTime toDateParsed = parseDateTime(toDate);

            return new Event(description, fromDateParsed, toDateParsed); // Create a new Event task
        }
        // Handle other commands
        throw new Exception("Invalid command.");
    }


    /**
     * switch date and time format from string to "dd/MM/yyyy HHmm" to LocalDateTime object
     *
     * @param dateTimeString the date and time string to parse
     * @return LocalDateTime object which represent the parsed date and timing
     * @throws DateTimeParseException if the string cannot be parser
     */
    // Method to check date and time from "d/M/yyyy HHmm" format
    public static LocalDateTime parseDateTime(String dateTimeString) throws Exception {
        try {
            // Define the formatter
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");
            return LocalDateTime.parse(dateTimeString, formatter);
        } catch (DateTimeParseException e) {
            throw new Exception("Invalid date/time format. Please use 'dd/MM/yyyy HHmm'.");
        }
    }

}
