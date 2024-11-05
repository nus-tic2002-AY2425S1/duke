package Chad.Command;

import java.time.LocalDateTime;
import java.time.Period;
import Chad.Parser.ChadDate;
import Chad.Exception.ChadException;
import Chad.Storage.Storage;
import Chad.TaskList.TaskList;
import Chad.Ui.Ui;

public class StatisticsCommand extends Command {

    private LocalDateTime statisCheckTime;
    private String timePeriod;

    // Constructor that initializes the timePeriod and sets the check time to now
    public StatisticsCommand(String timePeriod) {
        this.timePeriod = timePeriod.toUpperCase(); // Convert to uppercase for uniform handling
        this.statisCheckTime = LocalDateTime.now();
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws ChadException {
        // Determine the period based on the provided string
        Period checkPeriod = ChadDate.findPeriodByString(timePeriod);
        // If the period is zero, execute a HelpCommand and terminate the command
        if (checkPeriod.isZero()) {
            HelpCommand helpCommand = new HelpCommand("Summary"); // Or specify a more relevant help type
            helpCommand.execute(tasks, ui, storage); // Execute help command
            return; // Exit the execute method after showing help
        }
        TaskList taskListWithStatistics = tasks.getTaskByTimeRange(statisCheckTime, checkPeriod);
        // Display the tasks to the user
        Ui.showMsg("You have complete "+taskListWithStatistics.getNoOfTask()+" task(s) since "+timePeriod);
        ui.showTaskList(taskListWithStatistics);
    }
}
