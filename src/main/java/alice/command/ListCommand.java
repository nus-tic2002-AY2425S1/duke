package alice.command;

import alice.storage.Storage;
import alice.task.TaskList;
import alice.ui.Ui;

public class ListCommand extends Command {

    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        System.out.println("Here are the tasks in your list:");
        tasks.printTasks();
    }
}
