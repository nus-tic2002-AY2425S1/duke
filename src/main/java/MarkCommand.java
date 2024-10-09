import java.io.IOException;

public class MarkCommand extends Command {
    private final String arguments;

    public MarkCommand(String arguments) {
        this.arguments = arguments;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DennyException {
        try {
            int index = Integer.parseInt(arguments) - 1;
            Task task = tasks.getTask(index);
            task.markAsDone();
            ui.showTaskMarkedDone(task);
            storage.saveTasks(tasks.getAllTasks());
        } catch (NumberFormatException | IndexOutOfBoundsException e) {
            throw new DennyException("Invalid task number.");
        } catch (IOException e) {
            throw new DennyException("Error saving tasks: " + e.getMessage());
        }
    }
}
