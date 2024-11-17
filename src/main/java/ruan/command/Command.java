package ruan.command;

import ruan.task.*;
import ruan.ui.*;
import ruan.storage.*;
import ruan.exception.*;

public abstract class Command {
    
    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws RuanException;

    public boolean isExit() {
        return false;
    }
}





