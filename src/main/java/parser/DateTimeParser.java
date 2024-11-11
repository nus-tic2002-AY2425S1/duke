package parser;

import common.Constants;
import common.Messages;
import exception.CommandException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoField;

/**
 * Parses and formats date and time strings. It is a utility class,
 * This class provides methods to convert date and time strings in various formats to {@link LocalDateTime}
 * and {@link LocalDate} objects, as well as to format these objects back into strings.
 * It supports specific input and output patterns for deadlines, events, and general date display.
 */

// Accept dates in a format such as yyyy-mm-dd format (e.g., 2019-10-15).
// Print in a different format such as MMM dd yyyy e.g., (Oct 15 2019).

public class DateTimeParser {

    public static final String INPUT_DATE_FORMAT = "yyyy-MM-dd";
    public static final String TIME_PATTERN = "HHmm";

    public static final String INPUT_DATETIME_PATTERN = INPUT_DATE_FORMAT + Constants.SPACE + TIME_PATTERN;
    public static final DateTimeFormatter INPUT_DATETIME_FORMAT = DateTimeFormatter.ofPattern(INPUT_DATETIME_PATTERN);

    public static final String OUTPUT_DATETIME_PATTERN = "MMM dd yyyy" + Constants.SPACE + TIME_PATTERN;
    public static final DateTimeFormatter OUTPUT_DATETIME_FORMAT = DateTimeFormatter.ofPattern(OUTPUT_DATETIME_PATTERN);

    // Deadline must have date but time is optional
    public static final DateTimeFormatter deadlineFormatter = new DateTimeFormatterBuilder()
        .appendPattern(INPUT_DATE_FORMAT).optionalStart().appendPattern(Constants.SPACE + TIME_PATTERN)
        .optionalEnd().parseDefaulting(ChronoField.HOUR_OF_DAY, 0)
        .parseDefaulting(ChronoField.MINUTE_OF_HOUR, 0).toFormatter();

    public static final String SHOW_DATE_PATTERN = "yyyy-MM-dd";
    public static final DateTimeFormatter SHOW_DATE_FORMAT = DateTimeFormatter.ofPattern(SHOW_DATE_PATTERN);

    // Add a private constructor to hide the implicit public one
    private DateTimeParser() {

    }

    /**
     * Parses a date-time string in the format "yyyy-MM-dd HHmm" and returns a {@link LocalDateTime} object.
     *
     * @param input represents the date-time string to parse.
     * @return the parsed LocalDateTime object.
     * @throws CommandException if the input string is not in the expected format.
     */
    // Convert "yyyy-MM-dd HHmm" in String to "MMM dd yyyy HHmm" in LocalDateTime
    public static LocalDateTime parseOutputDateTime(String input) throws CommandException {
        try {
            // return LocalDateTime.parse(input, INPUT_DATETIME_FORMAT);
            return LocalDateTime.parse(input, OUTPUT_DATETIME_FORMAT);
        } catch (DateTimeParseException e) {
            throw new CommandException(Messages.ERROR_INVALID_DATETIME_FORMAT);
        }
    }

    /**
     * Parses a deadline date-time string and returns a {@link LocalDateTime} object.
     * The time is optional and defaults to midnight if not provided.
     *
     * @param input represents the deadline date-time string to parse.
     * @return the parsed LocalDateTime object.
     * @throws CommandException if the input string is not in the expected format.
     */
    // For adding deadline tasks
    public static LocalDateTime parseInputDeadlineDateTime(String input) throws CommandException {
        try {
            return LocalDateTime.parse(input, deadlineFormatter);
            // return LocalDateTime.parse(input, INPUT_DATETIME_FORMAT);
        } catch (DateTimeParseException e) {
            // System.out.println(e.getMessage());
            throw new CommandException(Messages.ERROR_INVALID_DATETIME_FORMAT,
                String.format("Received `%s`", input),
                String.format("Expected format: %s. Example: 2019-10-15 [1800]", INPUT_DATETIME_PATTERN)
            );
        }
    }

    /**
     * Parses an event date-time string and returns a {@link LocalDateTime} object.
     *
     * @param input represents the event date-time string to parse.
     * @return the parsed LocalDateTime object.
     * @throws CommandException if the input string is not in the expected format.
     */
    // For adding event tasks
    public static LocalDateTime parseInputEventDateTime(String input) throws CommandException {
        try {
            return LocalDateTime.parse(input, INPUT_DATETIME_FORMAT);
        } catch (DateTimeParseException e) {
            // System.out.println(e.getMessage());
            throw new CommandException(Messages.ERROR_INVALID_DATETIME_FORMAT,
                String.format("Received `%s`", input),
                String.format("Expected format: %s. Example: 2019-10-15 1800", INPUT_DATETIME_PATTERN)
            );
        }
    }

    /**
     * Parses a date string for Show command and returns a {@link LocalDate} object.
     *
     * @param input represents the date String passed to the Show command.
     * @return the parsed LocalDate object.
     * @throws CommandException if the input string is not in the expected format.
     */
    public static LocalDate parseInputShowDate(String input) throws CommandException {
        try {
            return LocalDate.parse(input, SHOW_DATE_FORMAT);
        } catch (DateTimeParseException e) {
            // System.out.println(e.getMessage());
            throw new CommandException(Messages.ERROR_INVALID_DATETIME_FORMAT,
                String.format("Received `%s`", input),
                String.format("Expected format: %s. Example: 2019-10-15 1800", INPUT_DATETIME_PATTERN)
            );
        }
    }

    /**
     * Formats a {@link LocalDateTime} object into a String in the format "MMM dd yyyy HHmm".
     *
     * @param dateTime represents the LocalDateTime object to format.
     * @return the formatted String.
     */
    // For listing tasks in "MMM dd yyyy HHmm" format
    public static String formatDateTime(LocalDateTime dateTime) {
        // if (dateTime == null) {
        //     throw new DateTimeParserException("Date/time cannot be null");
        // }
        // System.out.println("formatDateTime: dateTime is " + dateTime);
        return dateTime.format(OUTPUT_DATETIME_FORMAT);
    }
}
