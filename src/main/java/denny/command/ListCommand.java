package denny.command;

import denny.task.TaskList;
import denny.ui.Ui;
import denny.storage.Storage;

public class ListCommand extends Command {
    
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showTaskList(tasks.getAllTasks());
    }
}
