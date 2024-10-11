package tasks;

import exception.DukeException;

public class Deadline extends Task{
    public String time;

    public Deadline(String description, String time ){
        super(description);
        this.time = time;
    }

    @Override 
    public String toString(){
        return "[D]" + super.toString() + " (by: " + time + ")";
    }

    @Override
    public Task createTask(String deadlineString) throws DukeException {
        String [] deadlineParts = deadlineString.split(" /by ");
        if(deadlineParts.length != 2){
            throw new DukeException("OOPS!! The Deadline description is improperly formatted. Please try again!");
        }
        return new Deadline(deadlineParts[0],deadlineParts[1]);
    }

    @Override
    public String toFileFormat(){
        return "D|" + (isDone ? 1:0) + "|" + description + "|" + time;
    }
}
