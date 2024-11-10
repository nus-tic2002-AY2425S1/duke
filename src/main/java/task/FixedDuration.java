package task;

import java.time.LocalDate;

import common.Constants;

public class FixedDuration extends Task {

    private double duration;
    private static final String SPACE = Constants.SPACE;

    public FixedDuration(String description, boolean isDone, double duration) {
        super(description, isDone);
        this.duration = duration;
    }

    public FixedDuration(String description, double duration) {
        super(description);
        this.duration = duration;
    }

    public double getDuration() {
        return duration;
    }

    @Override
    public String toString() {
        return Constants.OPEN_SQUARE_BRACKET + TaskType.FIXED_DURATION + Constants.CLOSE_SQUARE_BRACKET + 
                super.toString() + SPACE + Constants.OPEN_ROUND_BRACKET + Constants.DURATION + Constants.COLON + 
                SPACE + getDuration() + SPACE + Constants.HOURS + Constants.CLOSE_ROUND_BRACKET;
    }

    @Override
    public String encodeTask() {
        return TaskType.FIXED_DURATION + super.encodeTask() + Constants.ENCODE_TASK_SEPARATOR + 
                getDuration() + SPACE + Constants.HOURS;
    }


    @Override
    public boolean isOnDate(LocalDate date) {
        return false;
    }

}