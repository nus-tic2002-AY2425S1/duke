package tasks;

import exception.DukeException;
import lombok.Getter;

/**
 * A to-do task
 */
@Getter
public class ToDo extends Task{
    /**
     * Constructor for to-do task
     * @param description The description of to-do task
     */
    public ToDo(String description){
        super(description);
    }

    /**
     * Convert To-Do object into string
     * @return A string representation of To-Do
     */
    @Override
    public String toString(){
        return "[T]" + super.toString();
    }
    /**
     * Creates a To-Do with input description
     * @param description The description of the task
     * @return The created To-Do object
     */
    @Override
    public Task createTask(String description){
        return new ToDo(description);
    }

    /**
     * Convert To-Do to a format for file saving
     * @return String in the format to save To-Do into a file
     */
    @Override
    public String toFileFormat(){
        return "T|" + (isDone ? 1:0) + "|" + description;
    }
    
}
