/**
 * Class to handle all task actions from instructions/commands
 */
public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        //set all item to not done by default
        this.isDone = false;
    }

    public String getStatusIcon() {
        //return task status
        return (isDone ? "X" : " ");
    }

    
    public void markDone() {
        //mark item as done
        isDone = true; 
    }

    
    public void unmarkDone() {
        //unmark item, set not done
        isDone = false; 
    }

    @Override
    public String toString() {
        //return formatted string
        return "[" + getStatusIcon() + "] " + description; 
    }
}
