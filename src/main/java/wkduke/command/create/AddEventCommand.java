package wkduke.command.create;

import wkduke.parser.TimeParser;
import wkduke.task.Event;

import java.time.LocalDateTime;

import static wkduke.ui.Ui.INDENT_HELP_MSG_NUM;

/**
 * Represents a command to add an event task to the task list.
 */
public class AddEventCommand extends AddCommand {
    public static final String MESSAGE_USAGE = COMMAND_WORD_EVENT + " {task-description} /from {start-datetime} /to {end-datetime}\n"
            + "Description:".indent(INDENT_HELP_MSG_NUM)
            + "  - Adds an Event task with a description and a specified time range to your task list.".indent(INDENT_HELP_MSG_NUM)
            + TimeParser.MESSAGE_USAGE
            + "Example:".indent(INDENT_HELP_MSG_NUM)
            + "  event Workshop /from 2024-11-05 09:00 /to 2024-11-05 17:00".indent(INDENT_HELP_MSG_NUM)
            + "  event Meeting /from 2024/11/06 /to 2024/11/07".indent(INDENT_HELP_MSG_NUM)
            + "Constraints:".indent(INDENT_HELP_MSG_NUM)
            + "  - The task description cannot be empty.".indent(INDENT_HELP_MSG_NUM)
            + "  - The start datetime must be before the end datetime.".indent(INDENT_HELP_MSG_NUM)
            + "  - Both start and end datetime must be valid and complete.".indent(INDENT_HELP_MSG_NUM);

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
