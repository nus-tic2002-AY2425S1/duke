package tasks;

import exception.DukeException;
import lombok.Getter;

import static parser.DateTimeParser.*;
import java.time.LocalDateTime;

/**
 * Task with deadline
 */
@Getter
public class Deadline extends Task {
    private final LocalDateTime by;

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
        String[] deadlineParts = deadlineString.split(" /by ");
        if (deadlineParts.length != 2) {
            throw new DukeException("OOPS!! The Deadline description is improperly formatted. Please try again!");
        }

        String description = deadlineParts[0];
        LocalDateTime deadlineTime = parseDateTime(deadlineParts[1]);

        return new Deadline(description.trim(), deadlineTime);
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
