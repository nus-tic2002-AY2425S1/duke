package wkduke.command.create;

import wkduke.task.Deadline;

import java.time.LocalDateTime;

/**
 * Represents a command to add a deadline task to the task list.
 */
public class AddDeadlineCommand extends AddCommand {
    public static final String MESSAGE_USAGE = COMMAND_WORD_DEADLINE + " {taskDescription} /by {deadLine}";

    /**
     * Constructs an AddDeadlineCommand with the specified task description and deadline.
     *
     * @param taskDescription The description of the deadline task.
     * @param by              The deadline date and time for the task.
     */
    public AddDeadlineCommand(String taskDescription, LocalDateTime by) {
        task = new Deadline(taskDescription, by);
    }
}
