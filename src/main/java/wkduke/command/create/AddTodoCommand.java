package wkduke.command.create;

import wkduke.task.Todo;

import static wkduke.ui.Ui.INDENT_HELP_MSG_NUM;

/**
 * Represents a command to add a Todo task to the task list.
 */
public class AddTodoCommand extends AddCommand {
    public static final String MESSAGE_USAGE = COMMAND_WORD_TODO + " {task-description}\n"
            + "Description:".indent(INDENT_HELP_MSG_NUM)
            + "  - Adds a Todo task with the specified description.".indent(INDENT_HELP_MSG_NUM)
            + "Example:".indent(INDENT_HELP_MSG_NUM)
            + "  todo Read book".indent(INDENT_HELP_MSG_NUM)
            + "Constraints:".indent(INDENT_HELP_MSG_NUM)
            + "  - The task description cannot be empty.".indent(INDENT_HELP_MSG_NUM);

    /**
     * Constructs an AddTodoCommand with the specified task description.
     *
     * @param taskDescription The description of the todo task.
     */
    public AddTodoCommand(String taskDescription) {
        task = new Todo(taskDescription);
    }
}
