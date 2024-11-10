package josbot.commands;

import josbot.storage.FileStorage;
import josbot.task.TaskList;
import josbot.ui.UI;

public class ListCommand extends Command {

    @Override
    public void execute(TaskList tasks, UI ui, FileStorage file)
    {
        ui.showListMessage();
        for (int i = 1; i < tasks.getTaskCount() + 1; i++) {
            System.out.println(i + ". " + tasks.getTasks().get(i - 1).toString());
        }
    }
}
