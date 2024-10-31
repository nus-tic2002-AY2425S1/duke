package wkduke.task;

import wkduke.parser.TimeParser;

import java.time.LocalDateTime;

public class Deadline extends Task {
    protected LocalDateTime by;

    public Deadline(String description, LocalDateTime by) {
        super(description);
        this.by = by;
    }

    @Override
    public String encode() {
        return String.format("D | %s | %s | %s",
                isDone ? "1" : "0",
                description,
                by.format(TimeParser.ENCODING_FORMATTER)
        );
    }

    @Override
    public boolean isOnDate(LocalDateTime targetDateTime) {
        return targetDateTime.isEqual(by);
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by:" + by.format(TimeParser.CLI_FORMATTER) + ")";
    }

    public LocalDateTime getBy() {
        return by;
    }

    public void setBy(LocalDateTime by) {
        this.by = by;
    }
}
