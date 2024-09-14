package tasks;
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
    public Task createTask(String deadlineString) {
        String [] deadlineParts = deadlineString.split(" /by ");
        return new Deadline(deadlineParts[0],deadlineParts[1]);
    }
}
