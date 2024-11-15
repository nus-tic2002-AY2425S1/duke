package wkduke.common;

import wkduke.task.TaskList;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Utility class providing common helper methods for operations.
 */
public class Utils {
    /**
     * Parses a string of task numbers separated by a specified delimiter into a list of 1-based unsigned integers.
     *
     * @param input     The input string containing task numbers separated by the delimiter.
     * @param delimiter The delimiter used to separate the task numbers.
     * @return A list of parsed 1-based unsigned integers.
     * @throws NumberFormatException If the input contains invalid task numbers (e.g., zero, negative, or non-numeric values).
     */
    public static List<Integer> parseTaskNumbers(String input, String delimiter) {
        List<Integer> taskNumbers = new ArrayList<>();
        String[] parts = input.split(delimiter);
        for (String part : parts) {
            int number = Integer.parseUnsignedInt(part.trim());
            if (number == 0) {
                throw new NumberFormatException("For input string: \"0\"");
            }
            taskNumbers.add(number);
        }
        return taskNumbers;
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
