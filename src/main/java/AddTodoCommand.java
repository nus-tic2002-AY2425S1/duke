import java.io.IOException;

public class AddTodoCommand extends Command {
    private final String description;

    public AddTodoCommand(String description) {
        this.description = description;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DennyException {
        if (description.isEmpty()) {
            throw new DennyException("The description of a todo cannot be empty.");
        }
        Task newTask = new ToDo(description);
        tasks.addTask(newTask);
        ui.showTaskAdded(newTask, tasks.size());
        try {
            storage.saveTasks(tasks.getAllTasks());
        } catch (IOException e) {
            throw new DennyException("Error saving tasks: " + e.getMessage());
        }
    }
}
