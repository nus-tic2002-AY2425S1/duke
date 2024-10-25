package alice.task;

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