package wkduke.command;

import wkduke.task.ToDo;

/**
 * Represents a command to add a ToDo task to the task list.
 */
public class AddToDoCommand extends AddCommand {
    public static final String MESSAGE_USAGE = COMMAND_WORD_TODO + " {taskDescription}";

    /**
     * Constructs an AddToDoCommand with the specified task description.
     *
     * @param taskDescription The description of the todo task.
     */
    public AddToDoCommand(String taskDescription) {
        task = new ToDo(taskDescription);
    }
}
