package josbot.commands;

import josbot.storage.FileStorage;
import josbot.task.TaskList;
import josbot.ui.UI;

public class MarkCommand extends Command {

    @Override
    public void execute(TaskList tasks, UI ui, FileStorage file)
    {
        Integer task_number = Integer.valueOf(description) - 1;
        boolean marked = false;
        if(commandType.equals("mark"))
        {
            tasks.getTasks().get(task_number).markAsDone();
            marked = true;
        }
        else if(commandType.equals("unmark"))
        {
            tasks.getTasks().get(task_number).markAsNotDone();
        }

        ui.showMarkMessage(tasks.getTasks().get(task_number), marked);
        file.saveToFile(tasks);
    }
}
