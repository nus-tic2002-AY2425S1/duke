package mochi.common;

import mochi.common.exception.ExceptionMessages;
import mochi.ui.Ui;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * The DateTime class provides utility methods for parsing and converting date and time values.
 * It handles user-friendly formatting for display purposes and formatting that is used for storage in a database.
 */
public class DateTime {
    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("d/M/yyyy");
    private static final DateTimeFormatter DISPLAY_FORMATTER = DateTimeFormatter.ofPattern("MMM dd yyyy, h:mma");

    /**
     * Parses a string representing a date and time into a LocalDateTime object.
     *
     * @param dateTime the date and time string to parse, expected in the format "d/M/yyyy HHmm".
     * @return the parsed LocalDateTime object, or null if the input format is invalid.
     */
    public static LocalDateTime parse(String dateTime) {
        try {
            return LocalDateTime.parse(dateTime, DATE_TIME_FORMATTER);
        } catch (DateTimeParseException e) {
            Ui.response(ExceptionMessages.DATE_FORMAT_INVALID);
            return null;
        }
    }

    public static LocalDate parseDate(String dateTime) {
        try {
            return LocalDate.parse(dateTime, DATE_FORMATTER);
        } catch (DateTimeParseException e) {
            Ui.response(ExceptionMessages.DATE_FORMAT_INVALID);
            return null;
        }
    }

    /**
     * Converts a LocalDateTime object to a formatted string for display.
     *
     * @param dateTime the LocalDateTime object to format.
     * @return a formatted string in the "MMM dd yyyy, h:mma" format.
     */
    public static String toString(LocalDateTime dateTime) {
        return dateTime.format(DISPLAY_FORMATTER);
    }

    /**
     * Converts a LocalDateTime object to a string used for database storage.
     *
     * @param dateTime the LocalDateTime object to format.
     * @return a formatted string in the "d/M/yyyy HHmm" format.
     */
    public static String toDBString(LocalDateTime dateTime) {
        return dateTime.format(DATE_TIME_FORMATTER);
    }
}
