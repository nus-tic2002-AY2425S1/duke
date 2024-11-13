package wkduke.command;

import wkduke.task.Todo;

/**
 * Represents a command to add a Todo task to the task list.
 */
public class AddTodoCommand extends AddCommand {
    public static final String MESSAGE_USAGE = COMMAND_WORD_TODO + " {taskDescription}";

    /**
     * Constructs an AddTodoCommand with the specified task description.
     *
     * @param taskDescription The description of the todo task.
     */
    public AddTodoCommand(String taskDescription) {
        task = new Todo(taskDescription);
    }
}
