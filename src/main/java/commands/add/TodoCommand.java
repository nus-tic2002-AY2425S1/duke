package commands.add;

import task.Task;
import task.Todo;

/**
 * {@code TodoCommand} extends from the {@code AddTaskCommand}. It is a subclass of {@code AddTaskCommand}. 
 * The {@code TodoCommand} class provides the general structure for adding a {@code Task}.
 * It is used to add a {@code Todo} task to the {@code TaskList}.
 * This command takes the description, creates a {@code Todo} task, adds it to the {@code TaskList}, and saves the updated {@code TaskList} to {@code Storage}.
 */
public class TodoCommand extends AddTaskCommand {
    
    public static final String COMMAND_WORD = "todo";
    public static final String MESSAGE_USAGE = COMMAND_WORD + " <description>";
    
    /**
     * Constructs a {@code TodoCommand} with the specified description.
     * 
     * @param description represents the description of the {@code Todo} task to be added
     */
    public TodoCommand(String description) {
        super(description);
    }

    /**
     * Creates the {@code Todo} task with the given description.
     * 
     * @return the newly-created {@code Todo} task with the description
     */
    @Override
    protected Task createTask() {
        String description = getDescription();
        Todo task = new Todo(description);
        return task;
    }
}
