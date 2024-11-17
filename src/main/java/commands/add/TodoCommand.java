package commands.add;

import task.Task;
import task.Todo;

/**
 * Extends from the {@code AddTaskCommand}. It is a subclass of {@code AddTaskCommand}.
 * The {@code TodoCommand} class provides the general structure for adding a {@code Task}.
 * It is used to add a {@code Todo} task to the {@code TaskList}.
 * This command takes the description, creates a {@code Todo} task,
 * adds it to the {@code TaskList}, and saves the updated {@code TaskList} to {@code Storage}.
 */
public class TodoCommand extends AddTaskCommand {

    public static final String COMMAND_WORD = "todo";

    // todo <description>
    public static final String MESSAGE_USAGE = COMMAND_WORD + SPACE + DESCRIPTION_IN_ANGLE_BRACKETS;

    /**
     * Constructs a {@code TodoCommand} with the specified description.
     *
     * @param description represents the description of the {@code Todo} task to be added.
     */
    public TodoCommand(String description) {
        super(description);
    }

    /**
     * Creates the {@code Todo} task with the given description.
     *
     * @return the newly-created {@code Todo} task with the description.
     */
    @Override
    protected Task createTask() {
        // Assertion to ensure the description is valid at the time of task creation
//        assert getDescription() != null && !getDescription().trim().isEmpty() : "Todo description must not be null or empty";
        return new Todo(getDescription());
    }
}
