public class ToDo extends Task{
    public ToDo(String taskName) {
        super(taskName);
    }
    // (Wk 5) Level-4 Update return message for ToDos Task
    @Override
    public String printAddedTask() {
        return "\t[T]" + super.printAddedTask();
    }
}
