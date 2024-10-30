package wkduke.command;

import wkduke.task.Event;

import java.time.LocalDateTime;

public class AddEventCommand extends AddCommand {
    public static final String MESSAGE_USAGE = COMMAND_WORD_EVENT + " {taskDescription} /from {start} /to {end}";

    public AddEventCommand(String taskDescription, LocalDateTime from, LocalDateTime to) {
        task = new Event(taskDescription, from, to);
    }
}
