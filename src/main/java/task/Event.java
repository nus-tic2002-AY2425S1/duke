package task;

import common.Messages;
import parser.DateTimeParser;

import java.time.LocalDate;
import java.time.LocalDateTime;

// Events are tasks that start at a specific date/time and ends at a specific date/time
// e.g., (a) team project meeting 2/10/2019 2-4pm (b) orientation week 4/10/2019 to 11/10/2019

public class Event extends Task {

    protected LocalDateTime startDateTime;
    protected LocalDateTime endDateTime;

    public Event(String description, boolean isDone, LocalDateTime startDateTime, LocalDateTime endDateTime) {
        super(description, isDone);
        this.startDateTime = startDateTime;
        this.endDateTime = endDateTime;
    }

    public Event(String description, LocalDateTime startDateTime, LocalDateTime endDateTime) {
        this.description = description;
        this.startDateTime = startDateTime;
        this.endDateTime = endDateTime;
    }

    public LocalDateTime getStartDateTime() {
        return startDateTime;
    }

    public String getFormattedStartDateTime() {
        return DateTimeParser.formatDateTime(getStartDateTime());
    }

    public LocalDateTime getEndDateTime() {
        return endDateTime;
    }

    public String getFormattedEndDateTime() {
        return DateTimeParser.formatDateTime(getEndDateTime());
    }

    @Override
    public String toString() {
        final String SPACE = Messages.SPACE;
        // If description does not end with a space, add a space behind it
        if (!description.endsWith(SPACE)) {
            description += SPACE;
        } 
        // String formattedEndDateTime = DateTimeParser.formatDateTime(endDateTime);

        String formattedStartDateTime;
        String formattedEndDateTime;
        formattedStartDateTime = getFormattedStartDateTime();
        return "[" + TaskType.EVENT + "]" + super.toString() + "(from: " + getFormattedStartDateTime() + " to: " + getFormattedEndDateTime() + ")";
    }

    @Override
    public String encodeTask() {
        String separator = " | ";
        return TaskType.EVENT + super.encodeTask() + separator + getFormattedStartDateTime() + separator + getFormattedEndDateTime();
    }

    @Override
    public boolean isOnDate(LocalDate date) {
        // return getDue().isEqual(due);
        // return startDate.isEqual(getStartDateTime().toLocalDate()) && endDate.isEqual(getEndDateTime().toLocalDate());
        return getStartDateTime().toLocalDate().isEqual(date) || 
               getEndDateTime().toLocalDate().isEqual(date);
    }

}
