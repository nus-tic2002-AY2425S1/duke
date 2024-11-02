package alice.command;

import alice.storage.Storage;
import alice.task.*;
import alice.ui.Ui;

import java.io.IOException;

public class DeleteCommand extends Command {

    public DeleteCommand(int index){
        super(index);
    }

    public DeleteCommand(String action, String instruction){
        super(action, instruction);
    }

    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws IOException {
        if (getIndex() == -1){
            System.out.println("Noted. I've removed these tasks:");
            tasks.remove(getInstruction());

        } else{
            Task task = tasks.get(getIndex());
            tasks.remove(getIndex());
            System.out.println("Noted. I've removed this task:");
            System.out.println(task.print());
        }
        ui.showSize(tasks.size());
    }
}
