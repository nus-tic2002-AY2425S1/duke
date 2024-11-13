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
     * @param userInput in yyyy-MM-dd[ [HHmm][HH:mm]] format
     * @return LocalDateTime object converted from userInput
     * @throws InputException for DateTimeException
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
     * @param localDateTime
     * @return a string in MMM dd yyyy HH:mm format
     */
    public static String toString(LocalDateTime localDateTime) {
        // Referenced from DateTimeFormatter docs
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(LOCAL_DATE_TIME_CLI_OUTPUT_FORMAT);
        return localDateTime.format(formatter);
    }

    /**
     * @param localDateTime string in ISO local date time format
     * @return LocalDateTime object
     */
    public static LocalDateTime isoToLocalDateTime(String localDateTime) {
        // Referenced from DateTimeFormatter docs
        return LocalDateTime.parse(localDateTime, DateTimeFormatter.ISO_LOCAL_DATE_TIME);
    }

    /**
     * @param localDateTime object
     * @return a string in ISO local date time format
     */
    public static String toIsoString(LocalDateTime localDateTime) {
        // Referenced from DateTimeFormatter docs
        return localDateTime.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME);
    }
}
