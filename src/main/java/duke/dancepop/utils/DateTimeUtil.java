package duke.dancepop.utils;

import duke.dancepop.exceptions.ExceptionConsts;
import duke.dancepop.exceptions.InputException;

import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.temporal.ChronoField;

public class DateTimeUtil {
    public static final String YEAR_MONTH_DAY_FORMAT = "yyyy-MM-dd";
    public static final String HOUR_MINUTES_FORMAT = "[HHmm][HH:mm]";
    public static final String LOCAL_DATE_TIME_CLI_OUTPUT_FORMAT = "MMM dd yyyy HH:mm";

    /**
     * Converts a user input string into a LocalDateTime object.
     * The input must be in the format "yyyy-MM-dd[ [HHmm][HH:mm]]".
     * If time is omitted, it defaults to 00:00.
     *
     * @param userInput The input string in the expected format.
     * @return A LocalDateTime object representing the input.
     * @throws InputException If the input cannot be parsed into a valid LocalDateTime.
     */
    public static LocalDateTime userInputToLocalDateTime(String userInput) throws InputException {
        // Referenced from
        // https://docs.oracle.com/javase/10/docs/api/java/time/format/DateTimeFormatterBuilder.html#appendPattern(java.lang.String)
        // https://docs.oracle.com/javase/10/docs/api/java/time/format/SignStyle.html
        // https://docs.oracle.com/javase/10/docs/api/java/time/format/DateTimeFormatterBuilder.html#optionalStart()
        // https://docs.oracle.com/javase/10/docs/api/java/time/format/DateTimeFormatterBuilder.html#parseDefaulting(java.time.temporal.TemporalField,long)
        DateTimeFormatter formatter = new DateTimeFormatterBuilder()
                .appendPattern(YEAR_MONTH_DAY_FORMAT)
                .optionalStart()
                .appendLiteral(' ')
                .appendPattern(HOUR_MINUTES_FORMAT)
                .optionalEnd()
                .parseDefaulting(ChronoField.HOUR_OF_DAY, 0)
                .parseDefaulting(ChronoField.MINUTE_OF_HOUR, 0)
                .toFormatter();

        try {
            return LocalDateTime.parse(userInput, formatter);
        } catch (DateTimeException e) {
            throw new InputException(ExceptionConsts.INVALID_DATETIME_ERROR);
        }
    }

    /**
     * Formats a LocalDateTime object into a string in the format "MMM dd yyyy HH:mm".
     *
     * @param localDateTime The LocalDateTime object to format.
     * @return A formatted string representation of the LocalDateTime.
     */
    public static String toString(LocalDateTime localDateTime) {
        // Referenced from DateTimeFormatter docs
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(LOCAL_DATE_TIME_CLI_OUTPUT_FORMAT);
        return localDateTime.format(formatter);
    }

    /**
     * Parses a string in ISO local date-time format into a LocalDateTime object.
     *
     * @param localDateTime A string in ISO local date-time format.
     * @return A LocalDateTime object representing the input.
     */
    public static LocalDateTime isoToLocalDateTime(String localDateTime) {
        // Referenced from DateTimeFormatter docs
        return LocalDateTime.parse(localDateTime, DateTimeFormatter.ISO_LOCAL_DATE_TIME);
    }

    /**
     * Formats a LocalDateTime object into a string in ISO local date-time format.
     *
     * @param localDateTime The LocalDateTime object to format.
     * @return A formatted string representation of the LocalDateTime in ISO local date-time format.
     */
    public static String toIsoString(LocalDateTime localDateTime) {
        // Referenced from DateTimeFormatter docs
        return localDateTime.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME);
    }
}
