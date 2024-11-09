package snitch.command;

import snitch.task.TaskList;
import snitch.Ui;
import snitch.Storage;
import snitch.SnitchException;

public interface Command {
    void execute(TaskList tasks, Ui ui, Storage storage) throws SnitchException;
}