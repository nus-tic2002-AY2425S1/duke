package starkchatbot.taskmanager;

/**
 * Represents a todo tasks, which is a type of task with a description.
 * This class extends task class and adds a specific identifier for "To-Do" tasks.
 */
public class Todo extends Task{

    /**
     * Constructs a Todo task with the specified description.
     *
     * @param description The description of the To-Do task.
     */
    public Todo(String description){
        super(description);
    }

    /**
     * Returns a string representation of the Todo task, prefixed with "[T]".
     *
     * @return A formatted string representing the Todo task in the format "[T][status] description".
     */
    @Override
    public String printTask(){
        return "[T] " + super.printTask();
    }
}
