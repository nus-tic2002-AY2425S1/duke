package josbot.commands;

import josbot.JosBotException;
import josbot.storage.FileStorage;
import josbot.task.Task;
import josbot.task.TaskList;
import josbot.ui.UI;

import java.util.ArrayList;

public class FindCommand extends Command {
    protected String search_input;

    public FindCommand(String search_input) {
        this.search_input = search_input;
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
            if(task.getDescription().contains(search_input)) {
                results.addTask(task);
            }
        }
        ui.showTaskLists(results);
    }


}
