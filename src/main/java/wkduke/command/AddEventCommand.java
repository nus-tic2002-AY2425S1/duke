package wkduke.command;

import wkduke.task.Event;

public class AddEventCommand extends AddCommand {
    public static final String MESSAGE_USAGE = COMMAND_WORD_EVENT + " {taskDescription} /from {start} /to {end}";

    public AddEventCommand(String taskDescription, String from, String to) {
        task = new Event(taskDescription, from, to);
    }
}
