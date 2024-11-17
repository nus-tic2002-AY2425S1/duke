package parser;

import commands.Command;
import common.Constants;
import common.Messages;
import exception.CommandException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.format.DateTimeParseException;
import java.time.format.ResolverStyle;
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

    private static final String INPUT_DATE_PATTERN = "yyyy-MM-dd";
    private static final DateTimeFormatter INPUT_DATE_FORMAT = DateTimeFormatter.ofPattern(INPUT_DATE_PATTERN);
    private static final String TIME_PATTERN = "HHmm";

    private static final String INPUT_DATETIME_PATTERN = INPUT_DATE_PATTERN + Constants.SPACE + TIME_PATTERN;
    private static final DateTimeFormatter INPUT_DATETIME_FORMAT = DateTimeFormatter.ofPattern(INPUT_DATETIME_PATTERN);

    private static final String OUTPUT_DATETIME_PATTERN = "MMM dd yyyy" + Constants.SPACE + TIME_PATTERN;
    private static final DateTimeFormatter OUTPUT_DATETIME_FORMAT = DateTimeFormatter.ofPattern(OUTPUT_DATETIME_PATTERN);

    // Deadline must have date but time is optional
    // Solution below inspired by https://stackoverflow.com/questions/49358893/datetimeformatter-parsing-string-with-optional-time-part-fails-if-space-removed
    private static final DateTimeFormatter deadlineFormatter = new DateTimeFormatterBuilder()
        .appendPattern(INPUT_DATE_PATTERN).optionalStart().appendPattern(Constants.SPACE + TIME_PATTERN)
        .optionalEnd().parseDefaulting(ChronoField.HOUR_OF_DAY, 0)
        .parseDefaulting(ChronoField.MINUTE_OF_HOUR, 0).toFormatter();

//    private static final String SHOW_DATE_PATTERN = INPUT_DATE_FORMAT;
//    private static final DateTimeFormatter SHOW_DATE_FORMAT = DateTimeFormatter.ofPattern(SHOW_DATE_PATTERN);

    // Add a private constructor to hide the implicit public one
    private DateTimeParser() {

    }

    /**
     * Parses a date-time string in the format "yyyy-MM-dd HHmm" and returns a {@link LocalDateTime} object.
     * Convert "yyyy-MM-dd HHmm" in String to "MMM dd yyyy HHmm" in LocalDateTime
     *
     * @param input represents the date-time string to parse.
     * @return the parsed LocalDateTime object.
     * @throws CommandException if the input string is not in the expected format.
     */
    public static LocalDateTime parseOutputDateTime(String input) throws CommandException {
        assert input != null : "Input string must not be null";
        try {
            return LocalDateTime.parse(input, OUTPUT_DATETIME_FORMAT);
        } catch (DateTimeParseException e) {
            throw new CommandException(Messages.ERROR_INVALID_DATETIME_FORMAT);
        }
    }

    /**
     * Parses a deadline date-time string and returns a {@link LocalDateTime} object.
     * The time is optional and defaults to midnight if not provided.
     * For adding deadline tasks
     *
     * @param input represents the deadline date-time string to parse.
     * @return the parsed LocalDateTime object.
     * @throws CommandException if the input string is not in the expected format.
     */
    // Solution below adapted from ChatGPT.
    public static LocalDateTime parseInputDeadlineDateTime(String input) throws CommandException {
        // Check if the input matches the date pattern
        // \d{4} specifies 4 digits for the year
        if (input.matches("\\d{4}-\\d{2}-\\d{2}")) {

            // Extract year, month, and day from the input string
            String[] parts = input.split("-");
            int year = Integer.parseInt(parts[0]);
            int month = Integer.parseInt(parts[1]);
            int day = Integer.parseInt(parts[2]);

            // Validate the date manually
            if (!isValidDate(year, month, day)) {
                throw new CommandException(Messages.ERROR_INVALID_DATETIME_FORMAT,
                    String.format("Received `%s`", input),
                    String.format("Expected format: %s. Example: 2019-10-15 [1800]", INPUT_DATETIME_PATTERN)
                );
            }

            // If valid, create LocalDate and return the time at the start of the day
            LocalDate date = LocalDate.of(year, month, day);
            return date.atStartOfDay();
        } else {
            // If it doesn't match the date pattern, try parsing as datetime
            try {
                return LocalDateTime.parse(input, INPUT_DATETIME_FORMAT);
            } catch (DateTimeParseException e) {
                throw new CommandException(Messages.ERROR_INVALID_DATETIME_FORMAT,
                    String.format("Received `%s`", input),
                    String.format("Expected format: %s. Example: 2019-10-15 [1800]", INPUT_DATETIME_PATTERN)
                );
            }
        }
    }

    private static boolean isValidDate(int year, int month, int day) {
        // Check if the month is valid
        if (month < 1 || month > 12) {
            return false;
        }

        // Solution below inspired by https://www.geeksforgeeks.org/localdate-lengthofmonth-method-in-java-with-examples/
        // Check if the day is valid for the given month
        int daysInMonth = LocalDate.of(year, month, 1).lengthOfMonth();
        return day >= 1 && day <= daysInMonth;
    }

    /*
    public static LocalDateTime parseInputDeadlineDateTime(String input) throws CommandException {
        assert input != null : "Input string must not be null";
        try {
//            LocalDate dateRaw = input.split(Constants.SPACE)[0];
//
//            // Get the number of days in the month for the given date
//            YearMonth yearMonth = YearMonth.of(date.getYear(), date.getMonth());
//            int daysInMonth = yearMonth.lengthOfMonth();
//
//            // Check if the day is valid
//            if (date.getDayOfMonth() > daysInMonth) {
//                throw new DateTimeParseException("Invalid day for the given month/year", date.toString(), 0);
//            }

            // First, parse the date only to check if it's valid
//            LocalDate date = LocalDate.parse(input.trim(), INPUT_DATE_FORMAT);
//            System.out.println("date is " + date);


            // Now, parse the time (if present), and combine both parts into a LocalDateTime
//            LocalDateTime dateTime = LocalDateTime.parse(input.trim(), deadlineFormatter);

            // return LocalDateTime.parse(input, INPUT_DATETIME_FORMAT);

            LocalDateTime dateTime = LocalDateTime.parse(input, INPUT_DATETIME_FORMAT);
            return dateTime;

//            return dateTime;
        } catch (DateTimeParseException e) {
            // System.out.println(e.getMessage());
            System.out.println("Parsing failed for input: " + input); // Debugging output
            System.out.println("Exception message: " + e.getMessage()); // Debugging output

//            if (e.getMessage().contains("Invalid date") || e.getMessage().contains("Invalid leap year")) {
//                throw new CommandException(Messages.ERROR_INVALID_DATETIME_FORMAT,
//                    String.format("Received `%s`", input),
//                    "The date provided is invalid. Please check for leap year errors or invalid date format.");
//            } else {
//                throw new CommandException(Messages.ERROR_INVALID_DATETIME_FORMAT,
//                    String.format("Received `%s`", input),
//                    String.format("Expected format: %s. Example: 2019-10-15 [1800]", INPUT_DATETIME_PATTERN)
//                );
//            }

            try {
                LocalDate date = LocalDate.parse(input, DateTimeFormatter.ofPattern(INPUT_DATE_PATTERN));
                // Convert LocalDate to LocalDateTime at midnight (00:00)
                return date.atStartOfDay();
            } catch (DateTimeParseException dtpe) {
                // If both parsing attempts fail, throw CommandException
                throw new CommandException(Messages.ERROR_INVALID_DATETIME_FORMAT,
                    String.format("Received `%s`", input),
                    String.format("Expected format: %s. Example: 2019-10-15 [1800]", INPUT_DATETIME_PATTERN)
                );
            }

        }
    }
    */


    public static LocalDate isValidLocalDate(String dateStr, DateTimeFormatter dateFormatter) {
        LocalDate date = null;
        try {
            date = LocalDate.parse(dateStr, dateFormatter);
        } catch (DateTimeParseException e) {
            //handle exception
            e.printStackTrace();
        }
        return date;
    }

    /**
     * Parses an event date-time string and returns a {@link LocalDateTime} object.
     * For adding event tasks
     *
     * @param input represents the event date-time string to parse.
     * @return the parsed LocalDateTime object.
     * @throws CommandException if the input string is not in the expected format.
     */
    public static LocalDateTime parseInputEventDateTime(String input) throws CommandException {
        assert input != null : "Input string must not be null";
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
        assert input != null : "Input string must not be null";

        if (input.trim().isEmpty()) {
            throw new CommandException(
                Messages.ERROR_INVALID_DATETIME_FORMAT,
                String.format("Received `%s`", input),
                String.format("Expected format: %s. Example: 2019-10-15", INPUT_DATE_PATTERN)
            );
        }

        try {
            return LocalDate.parse(input, INPUT_DATE_FORMAT);
        } catch (DateTimeParseException e) {
            // System.out.println(e.getMessage());
            throw new CommandException(Messages.ERROR_INVALID_DATETIME_FORMAT,
                String.format("Received `%s`", input),
                String.format("Expected format: %s. Example: 2019-10-15", INPUT_DATE_PATTERN)
            );
        }
    }

    /**
     * Formats a {@link LocalDateTime} object into a String in the format "MMM dd yyyy HHmm".
     * For listing tasks in "MMM dd yyyy HHmm" format
     *
     * @param dateTime represents the LocalDateTime object to format.
     * @return the formatted String.
     */
    public static String formatDateTime(LocalDateTime dateTime) {
        assert dateTime != null : "Date and time object must not be null";
        // if (dateTime == null) {
        //     throw new DateTimeParserException("Date/time cannot be null");
        // }
        // System.out.println("formatDateTime: dateTime is " + dateTime);
        return dateTime.format(OUTPUT_DATETIME_FORMAT);
    }
}
