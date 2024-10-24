public class AddToDoCommand extends AddCommand {
    public static final String MESSAGE_USAGE = COMMAND_WORD_TODO + " {taskDescription}";

    public AddToDoCommand(String taskDescription) {
        task = new ToDo(taskDescription);
    }
}
