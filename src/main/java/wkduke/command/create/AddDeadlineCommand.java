package wkduke.command.create;

import wkduke.parser.TimeParser;
import wkduke.task.Deadline;

import java.time.LocalDateTime;

import static wkduke.ui.Ui.INDENT_HELP_MSG_NUM;

/**
 * Represents a command to add a deadline task to the task list.
 */
public class AddDeadlineCommand extends AddCommand {
    public static final String MESSAGE_USAGE = COMMAND_WORD_DEADLINE + " {task-description} /by {datetime}\n"
            + "Description:".indent(INDENT_HELP_MSG_NUM)
            + "  - Adds a Deadline task with a description and a due date to your task list.".indent(INDENT_HELP_MSG_NUM)
            + TimeParser.MESSAGE_USAGE
            + "Example:".indent(INDENT_HELP_MSG_NUM)
            + "  deadline Submit report /by 2024-11-05 23:59".indent(INDENT_HELP_MSG_NUM)
            + "  deadline Finish assignment /by 2024-11-05".indent(INDENT_HELP_MSG_NUM)
            + "Constraints:".indent(INDENT_HELP_MSG_NUM)
            + "  - The task description cannot be empty.".indent(INDENT_HELP_MSG_NUM);

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
