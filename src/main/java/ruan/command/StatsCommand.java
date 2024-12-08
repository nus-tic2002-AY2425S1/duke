package ruan.command;

import ruan.task.TaskList;
import ruan.ui.Ui;
import ruan.storage.Storage;
import ruan.exception.RuanException;

/**
 * Represents a command to display statistics about tasks
 */
public class StatsCommand extends Command {

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws RuanException {
        int completedLastWeek = tasks.getTasksCompletedInPastWeek();
        int totalCompleted = tasks.getTotalCompletedTasks();
        int totalPending = tasks.getTotalPendingTasks();

        String[] message = {
            "Statistics:",
            "Tasks completed in the past week: " + completedLastWeek,
            "Total completed tasks: " + totalCompleted,
            "Total pending tasks: " + totalPending
        };
        ui.printMessage(message);
    }
}
