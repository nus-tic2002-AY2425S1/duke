package wkduke.command.update;

import wkduke.command.Command;

import static wkduke.ui.Ui.INDENT_HELP_MSG_NUM;

/**
 * Abstract base class for sorting commands.
 */
public abstract class SortCommand extends Command {
    public static final String COMMAND_WORD = "sort";
    public static final String MESSAGE_USAGE = COMMAND_WORD + " /by <priority|tasktype|datetime> /order <asc|desc>\n"
            + "Description:".indent(INDENT_HELP_MSG_NUM)
            + "  - Sorts your tasks based on the specified field and order.".indent(INDENT_HELP_MSG_NUM)
            + "Field:\n".indent(INDENT_HELP_MSG_NUM)
            + "  priority - Sort tasks by priority (e.g., high, medium, low)\n".indent(INDENT_HELP_MSG_NUM)
            + "  tasktype - Sort tasks by type (e.g., todo, deadline, event)\n".indent(INDENT_HELP_MSG_NUM)
            + "  datetime - Sort time aware tasks by date time\n".indent(INDENT_HELP_MSG_NUM)
            + "Order:\n".indent(INDENT_HELP_MSG_NUM)
            + "  asc - Sort in ascending order\n".indent(INDENT_HELP_MSG_NUM)
            + "  desc - Sort in descending order\n".indent(INDENT_HELP_MSG_NUM)
            + "Example:\n".indent(INDENT_HELP_MSG_NUM)
            + "  sort /by priority /order asc".indent(INDENT_HELP_MSG_NUM)
            + "Constraints:".indent(INDENT_HELP_MSG_NUM)
            + "  - Both 'field' and 'order' must be specified.".indent(INDENT_HELP_MSG_NUM)
            + "  - 'field' must be one of: priority, tasktype, datetime.".indent(INDENT_HELP_MSG_NUM)
            + "  - 'order' must be one of: asc, desc.".indent(INDENT_HELP_MSG_NUM);
    static final String MESSAGE_SUCCESS = "Tasks have been sorted by '%s' in '%s' order.";
    final SortOrder sortOrder;

    /**
     * Constructs a {@code SortCommand} with the specified sort order.
     *
     * @param sortOrder The sort order (ascending or descending).
     */
    SortCommand(SortOrder sortOrder) {
        this.sortOrder = sortOrder;
    }
}