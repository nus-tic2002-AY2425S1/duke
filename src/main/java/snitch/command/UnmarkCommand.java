package snitch.command;

import snitch.task.TaskList;
import snitch.Ui;
import snitch.Storage;
import snitch.SnitchException;
import snitch.task.Task;

/**
 * Command to mark a task as not done.
 */
public class UnmarkCommand implements Command {
    private final int taskIndex;

    public UnmarkCommand(int taskIndex) {
        this.taskIndex = taskIndex - 1; // Convert user input to zero-based index
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws SnitchException {
        try {
            Task task = tasks.get(taskIndex);
            task.markAsNotDone();
            ui.showTaskMarked(task, false);
            storage.save(tasks.getAllTasks());
        } catch (IndexOutOfBoundsException e) {
            throw new SnitchException("Invalid task number.");
        }
    }
}