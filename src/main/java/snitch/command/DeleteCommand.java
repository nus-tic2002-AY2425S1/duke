package snitch.command;


import snitch.task.TaskList;
import snitch.Ui;
import snitch.Storage;
import snitch.SnitchException;
import snitch.task.Task;

public class DeleteCommand implements Command {
    private final int taskIndex;

    public DeleteCommand(int taskIndex) {
        this.taskIndex = taskIndex - 1; // Convert 1-based index to 0-based
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws SnitchException {
        if (taskIndex < 0 || taskIndex >= tasks.size()) {
            throw new SnitchException("Invalid task number.");
        }
        Task removedTask = tasks.remove(taskIndex);
        ui.showTaskRemoved(removedTask, tasks.size());

        // Save tasks after deletion
        storage.save(tasks);
    }
}