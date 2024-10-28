package wkduke.parser;

import wkduke.common.Messages;
import wkduke.exception.TaskFormatException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoField;

public class TimeParser {
    public static final DateTimeFormatter FORMATTER = new DateTimeFormatterBuilder()
            .appendPattern("[yyyy/MM/dd][yyyy-MM-dd]")
            .optionalStart()
            .appendPattern(" [HHmm][HH:mm]")
            .optionalEnd()
            .parseDefaulting(ChronoField.HOUR_OF_DAY, 0)
            .parseDefaulting(ChronoField.MINUTE_OF_HOUR, 0)
            .toFormatter();
    
    // Solution below inspired by https://stackoverflow.com/questions/50023654/java-datetimeformatterbuilder-with-optional-pattern-results-in-datetimeparseexce
    public static LocalDateTime parseDateTime(String dateTimeString) throws TaskFormatException {
        try {
            return LocalDateTime.parse(dateTimeString, FORMATTER);
        } catch (DateTimeParseException e) {
            throw new TaskFormatException(
                    e.getMessage(),
                    String.format("DateTimeArguments='%s'", dateTimeString),
                    Messages.MESSAGE_INVALID_DATETIME_FORMAT
            );
        }
    }
}
