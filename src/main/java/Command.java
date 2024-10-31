public abstract class Command {

    protected Command() {
    }

    public abstract void execute(TaskList taskList, Ui ui, Storage storage) throws CommandException, StorageOperationException;
    // public abstract void execute(TaskList taskList, Ui ui, Storage storage);

    public boolean isBye() {
        return false;
    }
}



/* 
// https://www.youtube.com/watch?v=wq9SJb8VeyM
// https://www.simplilearn.com/tutorials/java-tutorial/enum-in-java
// https://www.geeksforgeeks.org/java-program-to-convert-enum-to-string/
// https://stackoverflow.com/questions/23608885/how-to-define-static-constants-in-a-java-enum

public enum Command {

    BYE_COMMAND("bye"),
    LIST_COMMAND("list"),
    MARK_COMMAND("mark"),
    UNMARK_COMMAND("unmark"),
    TODO_COMMAND("todo"),
    DEADLINE_COMMAND("deadline"),
    EVENT_COMMAND("event"),
    DELETE_COMMAND("delete");

    // BYE("bye"),
    // LIST("list"),
    // MARK("mark"),
    // UNMARK("unmark"),
    // TODO("todo"),
    // DEADLINE("deadline"),
    // EVENT("event"),
    // DELETE("delete");

    private final String command;

    Command(String command) {
        this.command = command;
    }

    @Override
    public String toString() {
        return command;
    }

    public static Command fromString(String commandStr) {
        for (Command command : Command.values()) {
            if (command.command.equals(commandStr)) {
                return command;
            }
        }
        throw new IllegalArgumentException("Unknown command: " + commandStr);
    }

    // Static fields for easy access
    public static final String BYE = BYE_COMMAND.toString();
    public static final String LIST = LIST_COMMAND.toString();
    public static final String MARK = MARK_COMMAND.toString();
    public static final String UNMARK = UNMARK_COMMAND.toString();
    public static final String TODO = TODO_COMMAND.toString();
    public static final String DEADLINE = DEADLINE_COMMAND.toString();
    public static final String EVENT = EVENT_COMMAND.toString();
    public static final String DELETE = DELETE_COMMAND.toString();
}
*/