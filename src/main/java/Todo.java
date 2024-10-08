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
    public String toString(){
        return "[T]" + (isDone ? "[X] " : "[ ] ") + super.toString();
    }
    @Override
    public String toSave() { return "T" + super.toSave(); }
}