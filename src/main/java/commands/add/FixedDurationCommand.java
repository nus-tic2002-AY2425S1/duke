package commands.add;

import common.Constants;
import task.FixedDuration;
import task.Task;

public class FixedDurationCommand extends AddTaskCommand {
    
    public static final String COMMAND_WORD = "fd";

    // make duration in terms of minutes
    // fd <task description> /duration <duration of task in hours>
    // fd <task description> /hours <number of hours needed> /minutes <number of minutes needed>
    public static final String SPACE = Constants.SPACE;
    public static final String OPEN_ANGLE_BRACKET = Constants.OPEN_ANGLE_BRACKET;
    public static final String CLOSE_ANGLE_BRACKET = Constants.CLOSE_ANGLE_BRACKET;

    public static final String MESSAGE_USAGE = COMMAND_WORD + SPACE + OPEN_ANGLE_BRACKET + 
                                                Constants.DESCRIPTION + CLOSE_ANGLE_BRACKET + SPACE + 
                                                Constants.SLASH_DURATION + SPACE + OPEN_ANGLE_BRACKET + 
                                                Constants.DURATION + SPACE + "in" + SPACE + 
                                                Constants.HOURS + CLOSE_ANGLE_BRACKET;

    private double duration;

    public FixedDurationCommand(String description, double duration) {
        super(description);
        this.duration = duration;
    }

    public double getDuration() {
        return duration;
    }

    @Override
    protected Task createTask() {
        String description = getDescription();
        double duration = getDuration();
        FixedDuration fixedDurationTask = new FixedDuration(description, duration);
        return fixedDurationTask;
    }

}
