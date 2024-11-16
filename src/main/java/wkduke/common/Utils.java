package wkduke.common;

import wkduke.exception.TaskFormatException;
import wkduke.parser.TimeParser;
import wkduke.task.TaskList;

import java.time.LocalDateTime;
import java.util.Set;

/**
 * Utility class providing common helper methods for operations.
 */
public class Utils {
    /**
     * Validates that the start date-time is not after the end date-time.
     *
     * @param fromDateTime The start date-time.
     * @param toDateTime   The end date-time.
     * @param arguments    The original input arguments.
     * @throws TaskFormatException If the start date-time is after the end date-time.
     */
    public static void validateDateTimeRange(LocalDateTime fromDateTime, LocalDateTime toDateTime, String arguments)
            throws TaskFormatException {
        if (fromDateTime.isAfter(toDateTime)) {
            throw new TaskFormatException(
                    String.format(Messages.MESSAGE_INVALID_DATETIME_RANGE, fromDateTime, toDateTime),
                    String.format("Arguments='%s'", arguments),
                    System.lineSeparator() + TimeParser.MESSAGE_USAGE
            );
        }
    }

    /**
     * Validates that all specified 1-based task numbers exist in the taskList.
     *
     * @param taskList The taskList to validate against.
     * @throws IndexOutOfBoundsException If any task number is invalid.
     */
    public static void validateTaskNumbers(TaskList taskList, Set<Integer> taskNumbers) {
        for (Integer taskNumber : taskNumbers) {
            taskList.getTask(taskNumber - 1);
        }
    }
}
