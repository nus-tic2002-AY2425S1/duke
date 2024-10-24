public abstract class Command {
    protected Command() {
    }

    public abstract void execute(TaskList taskList, Ui ui, Storage storage) throws StorageOperationException, CommandOperationException;

    public boolean isExit() {
        return false;
    }
}