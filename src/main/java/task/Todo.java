package task;
// Todos are tasks without any date/time attached to it e.g., visit new theme park

import java.time.LocalDate;

public class Todo extends Task {

    public Todo(String description) {
        super();
        this.description = description;
    }

    public Todo(String description, boolean isDone) {
        super(description, isDone);
    }

    @Override
    public String toString() {
        return "[" + TaskType.TODO + "]" + super.toString();
    }

    @Override
    public String encodeTask() {
        return TaskType.TODO + super.encodeTask();
    }

    @Override
    public boolean isOnDate(LocalDate date) {
        return false;
    }

}
