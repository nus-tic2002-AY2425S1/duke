package commands;

import root.*;
import tasks.Deadline;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class AddDeadlineCommand extends Command {
    private String description;
    private String by;

    public AddDeadlineCommand(String arguments) throws DukeException {
        String[] parts = arguments.split(" /by ");
        if (parts.length < 2 || parts[0].trim().isEmpty() || parts[1].trim().isEmpty()) {
            throw new DukeException("OOPS!!! The description and date/time for a deadline cannot be empty.\n\tCorrect usage: deadline <description> /by <yyyy-MM-dd HHmm>");
        }
        this.description = parts[0].trim();
        this.by = parts[1].trim();
    }
    
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws IOException, DukeException {
        LocalDateTime deadlineDateTime = parseDate(by);
    
        // Check for clashes with other tasks
        if (tasks.checkForClash(deadlineDateTime, deadlineDateTime)) {
            throw new DukeException("\tThe deadline clashes with an existing task. Please choose another time.");
        }
    
        // Add the deadline if no clash is found
        Deadline deadline = new Deadline(description, deadlineDateTime, false);
        tasks.addTask(deadline);
        storage.saveTasks(tasks.getTasks());
    
        //ui.showLine();
        System.out.println("\tGot it. I've added this task:");
        System.out.println("\t" + deadline);
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
