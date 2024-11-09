package snitch.command;


import snitch.task.TaskList;
import snitch.Ui;
import snitch.Storage;

public class ListCommand implements Command {

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        if (tasks.isEmpty()) {
            ui.showError("Your task list is empty.");
        } else {
            ui.showTasks(tasks);
        }
    }
}