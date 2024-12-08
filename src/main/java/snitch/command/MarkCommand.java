package snitch.command;

import snitch.task.TaskList;
import snitch.Ui;
import snitch.Storage;
import snitch.SnitchException;
import snitch.task.Task;

/**
 * Command to mark a task as done.
 */
public class MarkCommand implements Command {
    private final int taskIndex;

    public MarkCommand(int taskIndex) {
        this.taskIndex = taskIndex - 1; // Convert user input to zero-based index
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws SnitchException {
        try {
            Task task = tasks.get(taskIndex);
            task.markAsDone();
            ui.showTaskMarked(task, true);
            storage.save(tasks.getAllTasks());
        } catch (IndexOutOfBoundsException e) {
            throw new SnitchException("Invalid task number.");
        }
    }
}