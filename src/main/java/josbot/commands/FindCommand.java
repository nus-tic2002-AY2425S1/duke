package josbot.commands;

import josbot.JosBotException;
import josbot.storage.FileStorage;
import josbot.task.Task;
import josbot.task.TaskList;
import josbot.ui.UI;

public class FindCommand extends Command {

    public FindCommand(String commandType, String description) {
        super(commandType, description);
    }

    /**
     *
     * Used to execute the find command to show the list of tasks through UI class based on the search input
     * given by the user
     *
     * @param tasks
     * @param ui
     * @param file
     * @throws JosBotException
     */

    @Override
    public void execute(TaskList tasks, UI ui, FileStorage file) throws JosBotException {
        TaskList results = new TaskList();

        for(Task task : tasks.getTasks()) {
            if(task.getDescription().contains(description)) {
                results.addTask(task);
            }
        }
        ui.showTaskLists(results, false);
    }
}
