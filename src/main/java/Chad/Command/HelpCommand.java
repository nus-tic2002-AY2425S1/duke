package Chad.Command;

import Chad.Exception.ChadException;
import Chad.Storage.Storage;
import Chad.TaskList.TaskList;
import Chad.Ui.Ui;

public class HelpCommand extends Command {

    private String helpType;
    //before function calll convert to uppercase..
    public HelpCommand(String helpType) {
        this.helpType = helpType.toUpperCase();
    }


    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws ChadException {
        HelpType mType=HelpType.valueOf(helpType);
        String HelpMessage=mType.getMessage(); // choose HelpMessage according to user input helpType,
        ui.showHelp(HelpMessage);
        //storage.save(tasks.toString());
    }

}