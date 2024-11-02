package alice.command;

import alice.storage.Storage;
import alice.task.Task;
import alice.task.TaskList;
import alice.ui.Ui;

import java.io.IOException;

public class DeleteCommand extends Command {

    public DeleteCommand(int index){
        super(index);
    }
    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws IOException {
        Task task = tasks.get(getIndex());
        tasks.remove(getIndex());
        System.out.println("Noted. I've removed this task:");
        System.out.println(task.print());
        ui.showSize(tasks.size());
    }
}
