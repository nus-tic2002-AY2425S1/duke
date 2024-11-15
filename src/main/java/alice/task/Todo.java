package alice.task;

/**
 * <h1>Todo</h1>
 * The Todo class details the implementation
 * of todo task object which only consists of
 * the description.
 * <p>
 *
 * @author  Jarrel Bin
 * @version 1.0
 * @since   2024-11-02
 */
public class Todo extends Task {

    public Todo(String description) {
        super(description);
        this.isDone = false;
    }

    public Todo(String description, boolean isDone) {
        super(description,isDone);
    }

    @Override
    public String print(){
        return "[T]" + (isDone ? "[X] " : "[ ] ") + "description: " + description;
    }
    @Override
    public String toSave() { return "T" + (isDone ? " true " : " false " ) + description; }
}