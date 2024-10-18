package alice.task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TodoTest extends Task {

    public TodoTest(String description) {
        super(description);
        this.isDone = false;
    }

    public TodoTest(String description, boolean isDone) {
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
    public String toSave() { return "T" + (isDone ? " true " : " false " ) + description; }
}