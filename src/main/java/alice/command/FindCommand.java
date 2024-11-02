package alice.command;

import alice.exception.NoArgsException;
import alice.storage.Storage;
import alice.task.TaskList;
import alice.ui.Ui;

import java.io.IOException;

public class FindCommand extends Command{

    public FindCommand(String action, String instruction) {
        super(action,instruction);
    }

    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws IOException, NoArgsException {
        assert !getInstruction().isEmpty() : "Search word(s) shouldn't be empty";
        tasks.printTasks(getInstruction());
    }

}
