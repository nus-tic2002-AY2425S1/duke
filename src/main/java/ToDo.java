public class ToDo extends Task{
    public ToDo(String taskName) {
        super(taskName);
    }
    @Override
    public String printAddedTask() {
        return "\t[T]" + super.printAddedTask();
    }
}
