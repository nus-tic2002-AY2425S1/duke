public class Todo extends Task {

    public Todo(String description) {
        super(description);
        this.isDone = false;
    }

    public boolean isDone() {
        return isDone;
    }


    @Override
    public String toString(){
        return "[T]" + (isDone ? "[X] " : "[ ] ") + super.toString();
    }
}