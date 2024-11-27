package commands;

import java.io.IOException;
import root.*;
import tasks.Task;

public class UnmarkCommand extends Command {
    private int taskIndex;

    public UnmarkCommand(String arguments) throws DukeException {
        try {
            this.taskIndex = Integer.parseInt(arguments.trim()) - 1;
        } catch (NumberFormatException e) {
            throw new DukeException("Invalid task number.");
        }
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException, IOException {
        if (taskIndex < 0 || taskIndex >= tasks.size()) {
            throw new DukeException("Invalid task number.");
        }
        Task task = tasks.get(taskIndex);
        task.markAsNotDone();
        storage.saveTasks(tasks.getTasks());
        //ui.showLine();
        System.out.println("\tOK, I've marked this task as not done yet:");
        System.out.println("\t" + task);
        ui.showLine();
    }
}

