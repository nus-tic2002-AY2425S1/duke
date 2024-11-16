package josbot.task;

import josbot.parser.DateTimeParser;

import java.time.LocalDateTime;

/**
 * Represent Deadline Task
 */

public class Deadline extends Task {
    protected LocalDateTime by;
    protected boolean time;

    /**
     * Creates a deadline task with its description and date & time
     *
     * @param description description of the deadline task
     * @param by          date&time of the deadline
     * @param time
     */

    public Deadline(String description, LocalDateTime by, boolean time) {
        super(description);
        this.by = by;
        this.time = time;
    }

    /**
     * Creates a deadline task with its description and date
     *
     * @param description description of the deadline task
     * @param by          date of the deadline
     */

    public Deadline(String description, LocalDateTime by) {
        super(description);
        this.by = by;
        this.time = false;
    }

    /**
     * Return deadline date & time
     *
     * @return deadline date & time
     */

    @Override
    public LocalDateTime getDateTime() {
        return by;
    }

    /**
     * Return String of Deadline type
     *
     * @return Deadline type
     */
    @Override
    public String getType() {
        return "D";
    }

    /**
     * Return String of the deadline details
     *
     * @return
     */

    @Override
    public String toString() {
        DateTimeParser dtParser = new DateTimeParser();
        return "[D]" + super.toString() + " (by: " + dtParser.convertToString(getDateTime(), "view") + ")";
    }
}
