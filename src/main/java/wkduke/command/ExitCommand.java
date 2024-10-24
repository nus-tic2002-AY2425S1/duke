package wkduke.command;

import wkduke.storage.Storage;
import wkduke.task.TaskList;
import wkduke.ui.Ui;

public class ExitCommand extends Command {
    public static final String COMMAND_WORD = "bye";

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        ui.showGoodbyeMessage();
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
