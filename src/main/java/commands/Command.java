package commands;

import root.TaskList;
import root.Ui;
import root.Storage;
import root.DukeException;
import java.io.IOException;

public abstract class Command {
    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException, IOException;

    public boolean isExit() {
        return false;
    }
}
