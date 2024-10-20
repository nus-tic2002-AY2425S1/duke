public class Parser {
    public static Task parseCommand(String command) throws Exception {

        // Check for deadline command
        if (command.startsWith("deadline ")) {
            String[] parts = command.split(" /by ", 2);
            String description = parts[0].substring(9).trim(); // Extract description
            String dateTime = parts[1].trim(); // Extract date string
            return new Deadline(description, dateTime); // Create a new Deadline task
        }

        // Check for event command
        if (command.startsWith("event ")) {
            String[] parts = command.split(" /from ", 2);
            String description = parts[0].substring(6).trim(); // Extract description
            String[] dateParts = parts[1].split(" /to ", 2);
            String fromDate = dateParts[0].trim(); // Extract start date string
            String toDate = dateParts[1].trim(); // Extract end date string
            return new Event(description, fromDate, toDate); // Create a new Event task
        }
        // Handle other commands
        throw new Exception("Invalid command.");
    }
}
