package Chad.Command;

import Chad.Exception.ChadException;
import Chad.Storage.Storage;
import Chad.TaskList.TaskList;
import Chad.Ui.Ui;

public class ListByDateCommand extends Command {

    private String listDate;

    public ListByDateCommand(String listDate) {
        this.listDate = listDate;
    }


    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws ChadException {
        //checkthrough tasks and show all event task deadline within range 
        //ArrayList<Task> taskTolist = tasks.getTaskbyDeadline(listDate);
        TaskList taskTolist = tasks.getTaskbyDeadline(listDate);
        ui.showTaskList(taskTolist); // UI method to display the task list
        storage.save(tasks.toString());
    }

}