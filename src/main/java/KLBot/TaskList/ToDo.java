package KLBot.TaskList;

public class ToDo extends Task {

    public ToDo(String description) {
        super(description);
    }

    @Override
    public String getType() {
        return "T";
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    @Override
    public String toFileFormat() {
        return "T | " + (completed ? "1" : "0") + " | " + listDescription;
    }
}