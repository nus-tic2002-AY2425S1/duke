package task;
import java.time.LocalDate;
import java.time.LocalDateTime;

import parser.DateTimeParser;

// Deadlines are tasks that need to be done before a specific date/time e.g., submit report by 11/10/2019 5pm

public class Deadline extends Task {

    protected LocalDateTime due;

    public Deadline(String description, boolean isDone, LocalDateTime due) {
        super(description, isDone);
        this.due = due;
    }

    public Deadline(String description, LocalDateTime due) {
        this.description = description;
        this.due = due;
    }

    public LocalDateTime getDue() {
        return due;
    }

    public String getFormattedDue() {
        return DateTimeParser.formatDateTime(due);
    }

    @Override
    public String toString() {
        // If description does not end with a space, add a space behind it
        if (!description.endsWith(" ")) {
            description += " ";
        } 
        // String formattedDue = DateTimeParser.formatDateTime(due);
        return "[" + TaskType.DEADLINE + "]" + super.toString() + "(by: " + getFormattedDue() + ")";
        // return "[D]" + super.toString() + "(by: " + getDue() + ")";
    }

    @Override
    public String encodeTask() {
        String separator = " | ";
        // Construct the final encoded task without leading or trailing spaces
        return TaskType.DEADLINE + super.encodeTask() + separator + getFormattedDue();
    }

    @Override
    // https://www.geeksforgeeks.org/compare-dates-in-java/
    public boolean isOnDate(LocalDate date) {
        return getDue().toLocalDate().isEqual(date);
    }

}
