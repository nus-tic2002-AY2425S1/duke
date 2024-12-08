package Chad.Command;

import Chad.Exception.ChadException;
import Chad.Storage.Storage;
import Chad.TaskList.TaskList;
import Chad.Ui.Ui;

public class FindCommand extends Command {

    private final String findIdx;

    public FindCommand(String findIdx) {
        this.findIdx = findIdx;
    }


    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws ChadException {
        //checkthrough tasks and show all event task deadline within range 
        
        TaskList taskTolist = tasks.findTaskbyIdx(findIdx);
        ui.showTaskList(taskTolist); // UI method to display the task list
        
    }

}