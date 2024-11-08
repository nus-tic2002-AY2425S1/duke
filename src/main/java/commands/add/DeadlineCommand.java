package commands.add;

import java.time.LocalDateTime;

import task.Task;
import task.Deadline;


/**
 * {@code DeadlineCommand} extends from {@code AddTaskCommand}. 
 * It is a subclass of {@code AddTaskCommand} which provides the general structure of adding a task.
 * It takes the description and due date for the {@code Deadline} task, creates a {@code Deadline} task,
 * adds it to the {@code TaskList}, and saves the updated {@code TaskList} to {@code Storage}.
 */
public class DeadlineCommand extends AddTaskCommand {

    public static final String COMMAND_WORD = "deadline";
    public static final String MESSAGE_USAGE = COMMAND_WORD + " <description> /by <due date>";
    
    protected final LocalDateTime by;

    /**
     * Constructs a {@code DeadlineCommand} with the specified description and due date.
     * 
     * @param description represents the description of the {@code Deadline} task
     * @param by represents the due date of the {@code Deadline} task
     */
    public DeadlineCommand(String description, LocalDateTime by) {
        super(description);
        this.by = by;
    }

    /**
     * Returns the due date and time of the {@code Deadline} task.
     * 
     * @return due date and time of {@code Deadline} task
     */
    public LocalDateTime getBy() {
        return by;
    }

    /**
     * Creates a new {@code Deadline} task with the provided description and due date.
     * 
     * @return the newly created {@code Deadline} task
     */
    @Override
    protected Task createTask() {
        String description = getDescription();
        LocalDateTime by = getBy();
        Deadline task = new Deadline(description, by);
        return task;
    }

}
