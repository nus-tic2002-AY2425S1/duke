package Chad.Command;

import Chad.Exception.*;
import Chad.TaskList.*;
import Chad.Storage.*;
import Chad.Ui.*;

public abstract class Command {
    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws ChadException;
    public boolean isExit(){
        return false;
    }
}