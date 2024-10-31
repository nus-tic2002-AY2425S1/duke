package tasks;

import exception.DukeException;
import lombok.Getter;

import static parser.TaskParser.parseFixedDuration;

@Getter
public class FixedDuration extends Task{
    private Integer duration;

    /**
     * Constructor for FixedDuration.
     * @param description Description string of FixedDuration.
     * @param duration The duration in hour in integer.
     */
    public FixedDuration (String description, Integer duration){
        super(description);
        this.duration = duration;
    }

    /**
     * Convert FixedDuration object to String
     * @return FixedDuration object as a formatted string
     */
    @Override
    public String toString(){
        return "[FD]" + super.toString() + " (needs: " + duration + " hours)";
    }

    /**
     * Parse user input and create FixedDuration object.
     * @param fixedDurationString User input
     * @return FixedDuration object
     * @throws DukeException If FixedDuration user input is improperly formatted
     */
    @Override
    public Task createTask(String fixedDurationString) throws DukeException {
        String [] fixedDurationParts = parseFixedDuration(fixedDurationString);
        return new FixedDuration(fixedDurationParts[0], Integer.parseInt(fixedDurationParts[1]));
    }

    @Override
    public void update(String fixedDurationString) throws DukeException {
        String [] fixedDurationParts = parseFixedDuration(fixedDurationString);
        this.description = fixedDurationParts[0];
        this.duration = Integer.parseInt(fixedDurationParts[1]);
    }
    /**
     * Converts the FixedDuration task to a format suitable for file saving.
     *
     * @return A string in the format used to save the task to a file.
     */
    @Override
    public String toFileFormat(){
        return "FD|" + (isDone ? 1:0) + "|" + description + "|" + duration;
    }

}
