package josbot.commands;

import josbot.storage.FileStorage;
import josbot.task.Task;
import josbot.task.TaskList;
import josbot.ui.UI;


/**
 * This Command is initialised when user executes the 'find' command
 */

public class FindCommand extends Command {

    public FindCommand(String commandType, String description) {
        super(commandType, description);
    }

    /**
     *
     * Execute command for 'list'
     * to show the list of tasks through UI class based on the search input
     * given by the user
     *
     * @param tasks A TaskList used to store the current tasks
     * @param ui A class to generate messages
     * @param file It stores the filepath and method related to storing and saving of the file
     */

    @Override
    public void execute(TaskList tasks, UI ui, FileStorage file) {
        TaskList results = new TaskList();

        for(Task task : tasks.getTasks()) {
            if(task.getDescription().contains(description)) {
                results.addTask(task);
            }
        }
        ui.showTaskLists(results, false);
    }
}
