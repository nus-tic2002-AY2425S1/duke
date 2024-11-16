package wkduke.parser;

import wkduke.common.Messages;
import wkduke.exception.TaskFormatException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoField;

import static wkduke.ui.Ui.INDENT_HELP_MSG_NUM;

/**
 * Provides utility methods for parsing and formatting date and time strings.
 * Supports flexible input formats and standardized output formats.
 */
public class TimeParser {
    public static final DateTimeFormatter ENCODING_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
    public static final DateTimeFormatter CLI_FORMATTER = DateTimeFormatter.ofPattern("d MMM yyyy h:mma");
    public static final String MESSAGE_USAGE = "DateTime Format:".indent(INDENT_HELP_MSG_NUM)
            + "  yyyy-MM-dd - Date only, e.g., 2024-11-05".indent(INDENT_HELP_MSG_NUM)
            + "  yyyy/MM/dd - Alternative date format, e.g., 2024/11/05".indent(INDENT_HELP_MSG_NUM)
            + "  HH:mm      - Optional time in 24-hour format, e.g., 23:59".indent(INDENT_HELP_MSG_NUM)
            + "  HHmm       - Alternative time format, e.g., 2359".indent(INDENT_HELP_MSG_NUM)
            + "  Note: Time defaults to 00:00 (12:00am) if not provided.".indent(INDENT_HELP_MSG_NUM);
    // Solution below inspired by https://stackoverflow.com/questions/50023654/java-datetimeformatterbuilder-with-optional-pattern-results-in-datetimeparseexce
    private static final DateTimeFormatter DECODING_FORMATTER = new DateTimeFormatterBuilder()
            .appendPattern("[yyyy/MM/dd][yyyy-MM-dd]")
            .optionalStart()
            .appendPattern(" [HHmm][HH:mm]")
            .optionalEnd()
            .parseDefaulting(ChronoField.HOUR_OF_DAY, 0)
            .parseDefaulting(ChronoField.MINUTE_OF_HOUR, 0)
            .toFormatter();

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
                    String.format("%s %s ", Messages.MESSAGE_INVALID_DATETIME_FORMAT, e.getMessage()),
                    String.format("DateTimeArguments='%s'", dateTimeString),
                    System.lineSeparator() + MESSAGE_USAGE
            );
        }
    }

}
