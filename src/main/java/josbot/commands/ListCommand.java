package josbot.commands;

import josbot.parser.DateTimeParser;
import josbot.storage.FileStorage;
import josbot.task.Task;
import josbot.task.TaskList;
import josbot.ui.UI;

/**
 * This Command is initialised when user executes the 'list' command
 */

public class ListCommand extends Command {

    public ListCommand(String commandType, String description) {
        super(commandType, description);
    }


    /**
     *
     * Execute Command for List.
     * It will list all the available tasks created so far
     * or if it includes the date, it will list all available deadline and event for that particular date
     *
     * @param tasks A TaskList used to store the current tasks
     * @param ui A class to generate messages
     * @param file It stores the filepath and method related to storing and saving of the file
     */
    @Override
    public void execute(TaskList tasks, UI ui, FileStorage file){
        ui.showListMessage();
        if(description.contains("/")){
            TaskList results = new TaskList();
            results = checkList(tasks);
            ui.showTaskLists(results, false);
        } else{
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
