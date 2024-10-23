package tasks;

import exception.DukeException;
import lombok.Getter;

import static parser.DateTimeParser.*;
import static parser.TaskParser.parseDeadline;

import java.time.LocalDateTime;

/**
 * Task with deadline
 */
@Getter
public class Deadline extends Task {
    private LocalDateTime by;

    /**
     * Constructor for Deadline
     *
     * @param description Description of the task
     * @param by The deadline of task in LocalDateTime.
     */
    public Deadline(String description, LocalDateTime by) {
        super(description);
        this.by = by;
    }

    /**
     * Converts the Deadline object to string.
     *
     * @return Deadline object description and LocalDateTime deadline.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + formatDateTime(by) + ")";
    }

    /**
     * Parse user input and creates a Deadline task.
     *
     * @param deadlineString The string input consisting description and deadline time
     * @return A new Deadline task
     * @throws DukeException If the input is improperly formatted or contains invalid date
     */
    @Override
    public Task createTask(String deadlineString) throws DukeException {
        String[] deadlineParts = parseDeadline(deadlineString);
        return new Deadline(deadlineParts[0], parseDateTime(deadlineParts[1]));
    }

    @Override
    public void update(String deadlineString) throws DukeException {
        String[] deadlineParts = parseDeadline(deadlineString);
        this.description = deadlineParts[0];
        this.by = parseDateTime(deadlineParts[1]);
    }

    /**
     * Converts the Deadline task to a format suitable for file saving.
     *
     * @return A string in the format used to save the task to a file.
     */
    @Override
    public String toFileFormat() {
        return "D|" + (isDone ? 1 : 0) + "|" + description + "|" + formatToFile(by);
    }
}
