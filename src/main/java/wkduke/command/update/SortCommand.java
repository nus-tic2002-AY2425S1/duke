package wkduke.command.update;

import wkduke.command.Command;

/**
 * Abstract base class for sorting commands.
 */
public abstract class SortCommand extends Command {
    public static final String COMMAND_WORD = "sort";
    static final String MESSAGE_SUCCESS = "Tasks have been sorted by '%s' in '%s' order.";
    private static final int INDENT_NUM = 12;
    public static final String MESSAGE_USAGE = COMMAND_WORD
            + " /by <field> /order <asc|desc>\n"
            + "Field:\n".indent(INDENT_NUM)
            + "  priority - Sort tasks by priority (e.g., high, medium, low)\n".indent(INDENT_NUM)
            + "  tasktype - Sort tasks by type (e.g., todo, deadline, event)\n".indent(INDENT_NUM)
            + "  datetime - Sort time aware tasks by date and time\n".indent(INDENT_NUM)
            + "Order:\n".indent(INDENT_NUM)
            + "  asc - Sort in ascending order\n".indent(INDENT_NUM)
            + "  desc - Sort in descending order\n".indent(INDENT_NUM)
            + "Example:\n".indent(INDENT_NUM)
            + "  sort /by priority /order asd".indent(INDENT_NUM);
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