package ruan.command;

import ruan.task.*;
import ruan.ui.*;
import ruan.storage.*;
import ruan.exception.*;

public class ExitCommand extends Command {
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws RuanException {
        String[] message = {"Bye. Hope to see you again soon!"};
        ui.printMessage(message);
        storage.saveTasks(tasks.getTasks());
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
