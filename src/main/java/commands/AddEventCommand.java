package commands;

import root.*;
import tasks.Event;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class AddEventCommand extends Command {
    private String description;
    private String from;
    private String to;

    public AddEventCommand(String arguments) throws DukeException {
        String[] parts = arguments.split(" /from ");
        if (parts.length < 2 || parts[0].trim().isEmpty() || parts[1].split(" /to ").length < 2) {
            throw new DukeException("OOPS!!! The description, start time, and end time for an event cannot be empty.\n\tCorrect usage: event <description> /from <start time> /to <end time>");
        }
        this.description = parts[0].trim();
        String[] times = parts[1].split(" /to ");
        if (times.length < 2 || times[0].trim().isEmpty() || times[1].trim().isEmpty()) {
            throw new DukeException("OOPS!!! The start and end time for an event cannot be empty.\n\tCorrect usage: event <description> /from <start time> /to <end time>");
        }
        this.from = times[0].trim();
        this.to = times[1].trim();
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws IOException, DukeException {
        LocalDateTime eventStart = parseDate(from);
        LocalDateTime eventEnd = parseDate(to);

        // Ensure start is before end
        if (eventStart.isAfter(eventEnd)) {
            throw new DukeException("\tThe start time must be before the end time.");
        }

        // Check for clashes
        if (tasks.checkForClash(eventStart, eventEnd)) {
            throw new DukeException("\tThe event clashes with an existing task. Please choose another time.");
        }

        // Add the event if no clash is found
        Event event = new Event(description, eventStart, eventEnd, false);
        tasks.addTask(event);
        storage.saveTasks(tasks.getTasks());

        //ui.showLine();
        System.out.println("\tGot it. I've added this event:");
        System.out.println("\t" + event);
        System.out.println("\tNow you have " + tasks.size() + " tasks in the list.");
        ui.showLine();
    }

    private LocalDateTime parseDate(String dateString) throws DukeException {
        if (dateString.matches(".*\\d{4}$")) {
            try {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
                return LocalDateTime.parse(dateString, formatter);
            } catch (Exception e) {
                throw new DukeException("\tInvalid date and time format. Use yyyy-MM-dd HHmm (e.g., 2024-12-02 1800).");
            }
        } else {
            try {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                return LocalDate.parse(dateString, formatter).atStartOfDay();
            } catch (Exception e) {
                throw new DukeException("\tInvalid date format. Use yyyy-MM-dd (e.g., 2024-12-02).");
            }
        }
    }
}
