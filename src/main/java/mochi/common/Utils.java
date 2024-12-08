package mochi.common;

/**
 * The Utils class contains common utility that can be used within the project
 */
public class Utils {
    /**
     * Trims and concatenates elements from a string array, starting from a specified element and ending
     * before another specified element.
     * Elements are concatenated with a given delimiter.
     * @param string    the array of strings to process.
     * @param start     the starting string from which to begin concatenation (inclusive).
     * @param end       the ending string where concatenation stops (exclusive).
     * @param delimiter the delimiter to use between concatenated elements.
     * @return a trimmed string of concatenated elements from the array, or an empty string if
     * the start element is not found.
     */
    // * Created with the use of chatGPT

    public static String trimStringArrayWithStartEnd(String[] string, String start, String end, String delimiter) {
        StringBuilder body = new StringBuilder();
        boolean shouldCopy = false;
        for (String i : string) {
            if (!end.isEmpty() && i.equals(end))
                break;
            if (shouldCopy) {
                body.append(i).append(delimiter);
            }
            if (i.equalsIgnoreCase(start))
                shouldCopy = true;
        }
        return body.toString().trim();
    }

    public static int[] splitStringToIntArray(String input, String delimiter) {
        String[] tmp = input.split(delimiter);
        int[] result = new int[tmp.length];
        for (int i = 0; i < tmp.length; i++) {
            result[i] = Integer.parseInt(tmp[i].trim());
        }
        return result;
    }
}
