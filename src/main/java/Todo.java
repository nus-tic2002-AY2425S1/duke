// Todos are tasks without any date/time attached to it e.g., visit new theme park

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
        // System.out.println("TaskType.TODO " + TaskType.TODO.getClass().getName() + " is " + TaskType.TODO);
        return "[" + TaskType.TODO + "]" + super.toString();
        // return "[" + TaskType.TODO + "]" + super.toString();
        // return "[T]" + super.toString();
    }

    @Override
    public String encodeTask() {
        return TaskType.TODO + super.encodeTask();
    }

    // @Override
    // public TaskType getTaskType() {
    //     return TaskType.TODO;
    // }

    // @Override
    // public void writeToFile() {
    //     super.writeToFile(null);
    // }

}
