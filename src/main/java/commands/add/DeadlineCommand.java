package commands.add;

import common.Constants;
import task.Deadline;
import task.Task;

import java.time.LocalDateTime;


/**
 * Extends from {@code AddTaskCommand}.
 * It is a subclass of {@code AddTaskCommand} which provides the general structure of adding a task.
 * It takes the description and due date for the {@code Deadline} task, creates a {@code Deadline} task,
 * adds it to the {@code TaskList}, and saves the updated {@code TaskList} to {@code Storage}.
 */
public class DeadlineCommand extends AddTaskCommand {

    public static final String COMMAND_WORD = "deadline";

    // deadline <description> /by <due date>
    public static final String MESSAGE_USAGE = COMMAND_WORD + SPACE +
            DESCRIPTION_IN_ANGLE_BRACKETS + SPACE + Constants.SLASH_BY + SPACE +
            Constants.DUE_DATE_IN_ANGLE_BRACKETS;

    protected final LocalDateTime dueDate;

    /**
     * Constructs a {@code DeadlineCommand} with the specified description and due date.
     *
     * @param description represents the description of the {@code Deadline} task.
     * @param dueDate     represents the due date of the {@code Deadline} task.
     */
    public DeadlineCommand(String description, LocalDateTime dueDate) {
        super(description);
        this.dueDate = dueDate;
    }

    /**
     * Returns the due date and time of the {@code Deadline} task.
     */
    public LocalDateTime getDueDate() {
        return dueDate;
    }

    /**
     * Returns a new {@code Deadline} task with the provided description and due date.
     */
    @Override
    protected Task createTask() {
        String taskDescription = getDescription();
        LocalDateTime taskDueDate = getDueDate();
        return new Deadline(taskDescription, taskDueDate);
    }

}
