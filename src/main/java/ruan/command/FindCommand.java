package ruan.command;

import ruan.task.TaskList;
import ruan.ui.Ui;
import ruan.storage.Storage;

import java.util.ArrayList;
import ruan.task.Task;

/**
 * Represents a command to find tasks with a keyword
 */

public class FindCommand extends Command {

    private String keyword;

    /**
     * Constructs a FindCommand with the given keyword
     * @param keyword The keyword to search for in task list
     */
    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ArrayList<Task> matchingTasks = tasks.findTasks(keyword);

        if (matchingTasks.isEmpty()) {
            String[] message = {"There are no matching tasks in your list."};
            ui.printMessage(message);
        } else {
            String[] message = new String[matchingTasks.size() + 1];
            message[0] = "Here are the matching tasks in your list:";
            for (int i = 0; i < matchingTasks.size(); i++) {
                message[i + 1] = (i + 1) + ". " + matchingTasks.get(i);
            }
            ui.printMessage(message);
        }
    }
}
