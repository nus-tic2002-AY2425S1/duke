public class Todo extends Task {

    public Todo(String description) {
        super(description);
        this.isDone = false;
    }

    public Todo(String description, boolean isDone) {
        super(description,isDone);
    }


    public boolean isDone() {
        return isDone;
    }

    @Override
    public String print(){
        return "[T]" + (isDone ? "[X] " : "[ ] ") + "description: " + description;
    }
    @Override
    public String toSave() { return "T" + (isDone ? " 1 " : " 0 " ) + description; }
}