package wkduke.task;

import java.time.LocalDateTime;

/**
 * Represents a task that has time-based characteristics, allowing it to determine if it occurs on a specific date.
 */
interface TimeAware {
    /**
     * Checks if the task occurs on the specified date and time.
     *
     * @param targetDateTime The date and time to check.
     * @return {@code true} if the task occurs on the given date and time; {@code false} otherwise.
     */
    boolean isOccursOnDate(LocalDateTime targetDateTime);
}
