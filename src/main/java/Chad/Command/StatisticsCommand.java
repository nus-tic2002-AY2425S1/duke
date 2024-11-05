package Chad.Command;

import java.time.LocalDateTime;
import java.time.Period;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
        Period checkPeriod = findPeriodByString(timePeriod);
        // If the period is zero, execute a HelpCommand and terminate the command
        if (checkPeriod.isZero()) {
            HelpCommand helpCommand = new HelpCommand("Summary"); // Or specify a more relevant help type
            helpCommand.execute(tasks, ui, storage); // Execute help command
            return; // Exit the execute method after showing help
        }
        TaskList taskListWithStatistics = tasks.getTaskByTimeRange(statisCheckTime, checkPeriod);
        // Display the tasks to the user
        ui.showTaskList(taskListWithStatistics);
        // Optionally save task statistics to storage
        storage.save(taskListWithStatistics.toString());
    }

    /**
     * Converts a string representation of a time period into a Period object.
     *
     * @param timePeriodString The string representing the time period (e.g., "1 week", "3 days").
     * @return The corresponding Period object.
     */
    public Period findPeriodByString(String timePeriodString) {
        Pattern pattern = Pattern.compile("(\\d+)\\s*(days?|weeks?|months?)", Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(timePeriodString);
        
        Period period = Period.ZERO; // Start with a zero period
        while (matcher.find()) {
            int amount = Integer.parseInt(matcher.group(1)); // Get the amount (number) before the unit
            String unit = matcher.group(2).toLowerCase(); // Get the unit
            
            switch (unit) {
                case "day":
                //Fallthrough
                case "days":
                    period = period.plusDays(amount); // Add days to the period
                    break;
                case "week":
                //Fallthrough
                case "weeks":
                    period = period.ofWeeks(amount); // Add weeks to the period
                    break;
                case "month":
                //Fallthrough
                case "months":
                    period = period.plusMonths(amount); // Add months to the period
                    break;
                default:
                period = Period.ZERO;
                break;
            }
        }
        
        return period; // Return the constructed Period
    }
}
