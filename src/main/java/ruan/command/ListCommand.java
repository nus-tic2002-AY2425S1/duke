package ruan.command;

import ruan.task.*;
import ruan.ui.*;
import ruan.storage.*;

/**
 * Represents a command to list all tasks in the task list
 */

public class ListCommand extends Command {
    
    /**
     * Executes the list command by displaying all tasks in the task list
     * If task list is empty, displays a message indicating that
     * @param tasks TaskList containing the current tasks
     * @param ui Ui instance used for interacting with the user/displaying message
     * @param storage Storage instance used for saving tasks (not used in this command).
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        if (tasks.size() == 0) {
            String[] message = {"Your task list is currently empty."};
            ui.printMessage(message);
        } else {
            String[] message = new String[tasks.size() + 1];
            message[0] = "Here are the tasks in your list:";
            for (int i = 0; i < tasks.size(); i++) {
                message[i + 1] = (i + 1) + ". " + tasks.getTaskDescription(i);
            }
            ui.printMessage(message);
        }
    }

}
