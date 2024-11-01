package Chad.Command;

import Chad.Exception.ChadException;
import Chad.Storage.Storage;
import Chad.TaskList.TaskList;
import Chad.Ui.Ui;

public class MarkTask extends Command {
    private int taskIndex; // The index of the task to mark

    public MarkTask(int taskIndex) {
        this.taskIndex = taskIndex; // Capture the index from the input
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws ChadException {
        if (taskIndex < 0 || taskIndex >= tasks.getNoOfTask()) {
            throw new ChadException("Invalid task index: " + (taskIndex + 1));
        }
        tasks.markTask(taskIndex); // Mark the specified task
        ui.showAddTask(tasks.getTaskById(taskIndex),tasks.getNoOfTask()); // Adjust this as necessary for how you want to show the result
        storage.save(tasks.toString()); // Optionally save changes to the storage if required
    }

}