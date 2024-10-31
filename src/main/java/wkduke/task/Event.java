package wkduke.task;

import wkduke.parser.TimeParser;

import java.time.LocalDateTime;

public class Event extends Task {
    protected LocalDateTime from;
    protected LocalDateTime to;

    public Event(String description, LocalDateTime from, LocalDateTime to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    @Override
    public String encode() {
        return String.format("E | %s | %s | %s | %s",
                isDone ? "1" : "0",
                description,
                from.format(TimeParser.ENCODING_FORMATTER),
                to.format(TimeParser.ENCODING_FORMATTER)
        );
    }

    @Override
    public boolean isOnDate(LocalDateTime targetDateTime) {
        return targetDateTime.isAfter(from) && targetDateTime.isBefore(to);
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from:" + from.format(TimeParser.CLI_FORMATTER) + " to:" + to.format(TimeParser.CLI_FORMATTER) + ")";
    }

    public LocalDateTime getFrom() {
        return from;
    }

    public void setFrom(LocalDateTime from) {
        this.from = from;
    }

    public LocalDateTime getTo() {
        return to;
    }

    public void setTo(LocalDateTime to) {
        this.to = to;
    }
}
