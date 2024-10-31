package wkduke.command;

import wkduke.task.Event;

import java.time.LocalDateTime;

/**
 * Represents a command to add an event task to the task list.
 */
public class AddEventCommand extends AddCommand {
    public static final String MESSAGE_USAGE = COMMAND_WORD_EVENT + " {taskDescription} /from {start} /to {end}";

    /**
     * Constructs an AddEventCommand with the specified task description, start date, and end date.
     *
     * @param taskDescription The description of the event task.
     * @param from            The starting date and time for the event.
     * @param to              The ending date and time for the event.
     */
    public AddEventCommand(String taskDescription, LocalDateTime from, LocalDateTime to) {
        task = new Event(taskDescription, from, to);
    }
}
