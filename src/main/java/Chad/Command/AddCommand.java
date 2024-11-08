package Chad.Command;

import Chad.Exception.ChadException;
import Chad.Storage.Storage;
import Chad.TaskList.Task;
import Chad.TaskList.TaskList;
import Chad.Ui.Ui;

public class AddCommand extends Command {
    private final Task taskToAdd;

    public AddCommand(Task taskToAdd) {
        this.taskToAdd = taskToAdd;
    }

    private String extractTaskName(String taskString, String command) throws ChadException {
        String taskName = taskString.replaceFirst(command + " ", "").trim();
        if (taskName.isEmpty()) {
            throw new ChadException("Opps! Please enter a name for the " + command + " task.");
        }
        return taskName;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws ChadException {
        //Task task = new Task(taskString);
        tasks.addTask(taskToAdd);
        ui.showAddTask(taskToAdd, tasks.getNoOfTask());
        storage.save(tasks.toString());
    }
}

