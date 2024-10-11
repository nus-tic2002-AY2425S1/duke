package denny.command;

import denny.task.TaskList;
import denny.ui.Ui;
import denny.storage.Storage;
import denny.exception.DennyException;

public abstract class Command {
    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws DennyException;

    public boolean isExit() {
        return false;
    }
}
