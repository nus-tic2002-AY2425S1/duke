package ruan.statistics;

import ruan.task.Task;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

/**
 * Provides statistical methods to analyze tasks
 */

public class Statistics {

    /**
     * Calculates the number of tasks completed in the past week
     * @param tasks List of tasks to analyze
     * @return Number of tasks completed in the past 7 days
     */
    public int getTasksCompletedInPastWeek(List<Task> tasks) {
        LocalDateTime oneWeekAgo = LocalDateTime.now().minus(7, ChronoUnit.DAYS);
        int count = 0;

        for (Task task : tasks) {
            if (task.isDone() && task.getCompletionDateTime() != null &&
                    task.getCompletionDateTime().isAfter(oneWeekAgo)) {
                count++;
            }
        }
        return count;
    }

    /**
     * Calculates the total number of tasks completed
     * @param tasks List of tasks to analyze
     * @return Total number of completed tasks
     */
    public int getTotalCompletedTasks(List<Task> tasks) {
        int count = 0;
        for (Task task : tasks) {
            if (task.isDone()) {
                count++;
            }
        }
        return count;
    }

    /**
     * Calculates the total number of pending tasks
     * @param tasks List of tasks to analyze
     * @return Total number of pending tasks
     */
    public int getTotalPendingTasks(List<Task> tasks) {
        int count = 0;
        for (Task task : tasks) {
            if (!task.isDone()) {
                count++;
            }
        }
        return count;
    }
}
