package utility;

import exception.DukeException;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class DateTimeParser {

    private static final DateTimeFormatter inputFormatterWithTime = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
    private static final DateTimeFormatter inputFormatterWithoutTime = DateTimeFormatter.ofPattern("d/M/yyyy");
    private static final DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("MMM dd yyyy, h:mm a");

    /**
     * Parses a string with date and time (or just date) into a LocalDateTime object.
     * If only a date is provided, it defaults the time to 23:59.
     *
     * @param dateTimeString The string representing date and time.
     * @return The parsed LocalDateTime object.
     * @throws DukeException If the input date or time format is invalid.
     */
    public static LocalDateTime parseDateTime(String dateTimeString) throws DukeException {
        try {
            if (dateTimeString.trim().contains(" ")) {
                return LocalDateTime.parse(dateTimeString.trim(), inputFormatterWithTime);
            } else {
                return LocalDateTime.of(
                        LocalDateTime.parse(dateTimeString.trim(), inputFormatterWithoutTime).toLocalDate(),
                        LocalTime.of(23, 59)
                );
            }
        } catch (DateTimeParseException e) {
            throw new DukeException("OOPS!! Please provide a valid date and time in the format d/M/yyyy HHmm.");
        }
    }

    /**
     * Formats a LocalDateTime object into a human-readable string.
     *
     * @param dateTime The LocalDateTime object to format.
     * @return The formatted string.
     */
    public static String formatDateTime(LocalDateTime dateTime) {
        return dateTime.format(outputFormatter);
    }

    /**
     * Formats a LocalDateTime object into a string suitable for saving to a file.
     *
     * @param dateTime The LocalDateTime object to format.
     * @return The formatted string in the file format.
     */
    public static String formatToFile(LocalDateTime dateTime) {
        return dateTime.format(inputFormatterWithTime);
    }
}
