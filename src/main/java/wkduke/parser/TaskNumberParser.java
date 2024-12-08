package wkduke.parser;

import java.util.ArrayList;
import java.util.List;

/**
 * Utility class for parsing task numbers.
 */
class TaskNumberParser {
    /**
     * Parses a string of task numbers separated by a specified delimiter into a list of 1-based unsigned integers.
     *
     * @param input     The input string containing task numbers separated by the delimiter.
     * @param delimiter The delimiter used to separate the task numbers.
     * @return A list of parsed 1-based unsigned integers.
     * @throws NumberFormatException If the input contains invalid task numbers (e.g., zero, negative, or non-numeric values).
     */
    static List<Integer> parseTaskNumbers(String input, String delimiter) {
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
}
