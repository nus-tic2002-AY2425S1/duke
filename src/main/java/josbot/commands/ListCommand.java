package josbot.commands;

import josbot.storage.FileStorage;
import josbot.task.TaskList;
import josbot.ui.UI;

public class ListCommand extends Command {

    @Override
    public void execute(TaskList tasks, UI ui, FileStorage file)
    {
        ui.showListMessage();
        ui.showTaskLists(tasks);

    }
}
