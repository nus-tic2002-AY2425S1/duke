package josbot.commands;

import josbot.parser.DateTimeParser;
import josbot.storage.FileStorage;
import josbot.task.Task;
import josbot.task.TaskList;
import josbot.ui.UI;

public class ListCommand extends Command {

    public ListCommand(String commandType, String description) {
        super(commandType, description);
    }

    @Override
    public void execute(TaskList tasks, UI ui, FileStorage file)
    {
        ui.showListMessage();
        if(description.contains("/"))
        {
            TaskList results = new TaskList();
            results = checkList(tasks);
            ui.showTaskLists(results, false);
        }
        else
        {
            ui.showTaskLists(tasks, true);
        }

    }

    private TaskList checkList(TaskList tasks){
        TaskList results = new TaskList();
        DateTimeParser dt = new DateTimeParser();
        for(Task task : tasks.getTasks()) {
            if(task.getDateTime() != null) {
                if(dt.convertToString(task.getDateTime(),"view_date").equals(description)) {
                    results.addTask(task);
                }
            }
        }
        return results;
    }
}
