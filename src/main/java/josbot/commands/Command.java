package josbot.commands;

import josbot.storage.FileStorage;
import josbot.task.TaskList;
import josbot.ui.UI;

import java.io.File;
import java.nio.file.FileStore;

public class Command {

    protected String commandType;
    protected String description;
    protected boolean exit;

    public void setCommandType(String commandType, String description) {
        this.commandType = commandType;
        this.description = description;
        this.exit = false;
    }

    public void execute(TaskList tasks, UI ui, FileStorage file)
    {

    }

    public String getCommandType() {return commandType;}
    public String getDescription() {return description;}

    public boolean isExit(){
        return exit;
    }

//    public void CommandResult(){
//        throw new UnsupportedOperationException("Not supported!");
//    }
}
