package snitch.command;


import snitch.task.TaskList;
import snitch.Ui;
import snitch.Storage;


public class ByeCommand implements Command {

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showGoodbye();
    }
}