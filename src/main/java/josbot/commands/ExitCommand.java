package josbot.commands;

import josbot.JosBotException;
import josbot.storage.FileStorage;
import josbot.task.TaskList;
import josbot.ui.UI;

public class ExitCommand extends Command {
    public ExitCommand(String commandType, String description) {
        super(commandType, description);
        this.exit = true;
    }

    public void execute(TaskList tasks, UI ui, FileStorage file) throws JosBotException
    {
        ui.showGreeting("end");
    }
}