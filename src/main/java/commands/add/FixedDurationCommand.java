package commands.add;

import common.Constants;
import task.FixedDuration;
import task.Task;

/**
 * Represents a command to add a task with fixed duration.
 * This command allows the user to specify a task description and its duration in hours.
 */
public class FixedDurationCommand extends AddTaskCommand {

    public static final String COMMAND_WORD = "fd";

    // make duration in terms of minutes
    // fd <task description> /duration <duration of task in hours>
    // fd <task description> /hours <number of hours needed> /minutes <number of minutes needed>

    public static final String MESSAGE_USAGE = COMMAND_WORD + SPACE +
            DESCRIPTION_IN_ANGLE_BRACKETS + SPACE + Constants.SLASH_DURATION + SPACE +
            Constants.DURATION_IN_HOURS_IN_ANGLE_BRACKETS;

    private final double duration;

    /**
     * Constructs a {@code FixedDurationCommand} with the specified task description and duration.
     *
     * @param description represents the description of the task.
     * @param duration    represents the duration required for the task, in hours.
     */
    public FixedDurationCommand(String description, double duration) {
        super(description);
        this.duration = duration;
    }

    /**
     * Gets the duration of the task, in hours.
     */
    protected double getDuration() {
        return duration;
    }

    /**
     * Creates a {@code FixedDuration} task based on the received description and duration.
     *
     * @return a new instance of {@code FixedDuration} task representing the task to be added.
     */
    @Override
    protected Task createTask() {
        String taskDescription = getDescription();
        double taskDuration = getDuration();
        return new FixedDuration(taskDescription, taskDuration);
    }

}
