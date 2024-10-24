package wkduke.command;

import wkduke.exception.CommandOperationException;
import wkduke.exception.StorageOperationException;
import wkduke.storage.Storage;
import wkduke.task.TaskList;
import wkduke.ui.Ui;

public abstract class Command {
    protected Command() {
    }

    public abstract void execute(TaskList taskList, Ui ui, Storage storage) throws StorageOperationException, CommandOperationException;

    public boolean isExit() {
        return false;
    }
}