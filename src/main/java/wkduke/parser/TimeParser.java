package wkduke.parser;

import wkduke.common.Messages;
import wkduke.exception.TaskFormatException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoField;

/**
 * Provides utility methods for parsing and formatting date and time strings.
 * Supports flexible input formats and standardized output formats.
 */
public class TimeParser {
    // Solution below inspired by https://stackoverflow.com/questions/50023654/java-datetimeformatterbuilder-with-optional-pattern-results-in-datetimeparseexce
    public static final DateTimeFormatter DECODING_FORMATTER = new DateTimeFormatterBuilder()
            .appendPattern("[yyyy/MM/dd][yyyy-MM-dd]")
            .optionalStart()
            .appendPattern(" [HHmm][HH:mm]")
            .optionalEnd()
            .parseDefaulting(ChronoField.HOUR_OF_DAY, 0)
            .parseDefaulting(ChronoField.MINUTE_OF_HOUR, 0)
            .toFormatter();
    public static final DateTimeFormatter ENCODING_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
    public static final DateTimeFormatter CLI_FORMATTER = DateTimeFormatter.ofPattern("d MMM yyyy h:mma");

    /**
     * Parses a date-time string into a {@code LocalDateTime} object using the decoding formatter.
     *
     * @param dateTimeString The date-time string to parse.
     * @return A {@code LocalDateTime} object representing the parsed date and time.
     * @throws TaskFormatException If the date-time string does not match the expected decoding format.
     */
    public static LocalDateTime parseDateTime(String dateTimeString) throws TaskFormatException {
        try {
            return LocalDateTime.parse(dateTimeString, DECODING_FORMATTER);
        } catch (DateTimeParseException e) {
            throw new TaskFormatException(
                    e.getMessage(),
                    String.format("DateTimeArguments='%s'", dateTimeString),
                    Messages.MESSAGE_INVALID_DATETIME_FORMAT
            );
        }
    }

}
