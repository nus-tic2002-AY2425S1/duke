package wkduke.command;

import wkduke.task.Deadline;

import java.time.LocalDateTime;

public class AddDeadlineCommand extends AddCommand {
    public static final String MESSAGE_USAGE = COMMAND_WORD_DEADLINE + " {taskDescription} /by {deadLine}";

    public AddDeadlineCommand(String taskDescription, LocalDateTime by) {
        task = new Deadline(taskDescription, by);
    }
}
