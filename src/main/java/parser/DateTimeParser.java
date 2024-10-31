package parser;

import exception.DukeException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Handles datetime parsing
 */
public class DateTimeParser {
    private static final DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
    private static final DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("dd MMM yyyy, h:mm a");


    /**
     * Parses a string with date and time into a LocalDateTime object.
     *
     * @param dateTimeString The string representing date and time.
     * @return The parsed LocalDateTime object.
     * @throws DukeException If the input date or time format is invalid.
     */
    public static LocalDateTime parseDateTime(String dateTimeString) throws DukeException {
        try{
            return LocalDateTime.parse(dateTimeString.trim(),inputFormatter);
        } catch (DateTimeParseException e){
            throw new DukeException("OOPSS!! Please provide a valid date and time in the format d/M/yyyy HHmm");
        }
    }

    /**
     * Formats a LocalDateTime object into output format.
     *
     * @param dateTime The LocalDateTime object to format.
     * @return The formatted string.
     */
    public static String formatDateTime(LocalDateTime dateTime) {
        return dateTime.format(outputFormatter);
    }

    /**
     * Formats a LocalDateTime object into a input format to save to file.
     *
     * @param dateTime The LocalDateTime object to input format for file saving.
     * @return Formatted string in input format.
     */
    public static String formatToFile(LocalDateTime dateTime) {
        return dateTime.format(inputFormatter);
    }
}
