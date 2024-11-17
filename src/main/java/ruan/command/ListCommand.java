package ruan.command;

import ruan.task.*;
import ruan.ui.*;
import ruan.storage.*;

public class ListCommand extends Command {
    
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
