package commands.add;

import common.Constants;
import task.FixedDuration;
import task.Task;

/**
 * Represensts a command to add a task with fixed duration.
 * This command allows the user to specify a task description and its duration in hours.
 */
public class FixedDurationCommand extends AddTaskCommand {
    
    public static final String COMMAND_WORD = "fd";

    // make duration in terms of minutes
    // fd <task description> /duration <duration of task in hours>
    // fd <task description> /hours <number of hours needed> /minutes <number of minutes needed>

    public static final String MESSAGE_USAGE = COMMAND_WORD + SPACE + OPEN_ANGLE_BRACKET + 
        Constants.DESCRIPTION + CLOSE_ANGLE_BRACKET + SPACE + Constants.SLASH_DURATION + SPACE + OPEN_ANGLE_BRACKET + 
        Constants.DURATION + SPACE + "in" + SPACE + Constants.HOURS + CLOSE_ANGLE_BRACKET;

    private double duration;

    /**
     * Constructs a {@code FixedDurationCommand} with the specified task description and duration.
     * 
     * @param description represents the description of the task.
     * @param duration represents the duration required for the task, in hours.
     */
    public FixedDurationCommand(String description, double duration) {
        super(description);
        this.duration = duration;
    }

    /**
     * Gets the duration of the task, in hours.
     */
    public double getDuration() {
        return duration;
    }

    /**
     * Creates a {@code FixedDuration} task based on the received description and duration.
     * 
     * @return a new instance of {@code FixedDuration} task representing the task to be added.
     */
    @Override
    protected Task createTask() {
        String description = getDescription();
        double duration = getDuration();
        FixedDuration fixedDurationTask = new FixedDuration(description, duration);
        return fixedDurationTask;
    }

}
