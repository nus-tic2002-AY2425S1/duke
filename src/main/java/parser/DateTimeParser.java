package parser;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import common.Constants;
import common.Messages;
import exception.DateTimeParserException;

// Accept dates in a format such as yyyy-mm-dd format (e.g., 2019-10-15) and 
// print in a different format such as MMM dd yyyy e.g., (Oct 15 2019).

public class DateTimeParser {

    public static final String TIME_PATTERN = "HHmm";
    public static final String INPUT_DATETIME_PATTERN = "yyyy-MM-dd" + Constants.SPACE + TIME_PATTERN;
    public static final DateTimeFormatter INPUT_DATETIME_FORMAT = DateTimeFormatter.ofPattern(INPUT_DATETIME_PATTERN);
    public static final String OUTPUT_DATETIME_PATTERN = "MMM dd yyyy" + Constants.SPACE + TIME_PATTERN;
    public static final DateTimeFormatter OUTPUT_DATETIME_FORMAT = DateTimeFormatter.ofPattern(OUTPUT_DATETIME_PATTERN);

    // Convert "yyyy-MM-dd HHmm" in String to "MMM dd yyyy HHmm" in LocalDateTime
    public static LocalDateTime decodeDateTime(String input) throws DateTimeParserException {
        try {
            // return LocalDateTime.parse(input, INPUT_DATETIME_FORMAT);
            return LocalDateTime.parse(input, OUTPUT_DATETIME_FORMAT);
        } catch (DateTimeParseException e) {
            throw new DateTimeParserException(Messages.ERROR_INVALID_DATETIME_FORMAT);
        }
    }

    public static LocalDateTime parseDateTime(String input) throws DateTimeParserException {
        try {
            return LocalDateTime.parse(input, INPUT_DATETIME_FORMAT);
        } catch (DateTimeParseException e) {
            throw new DateTimeParserException(Messages.ERROR_INVALID_DATETIME_FORMAT);
        }
    }


    /* 
    // Parse to output format
    public static LocalDateTime parseDateTime(String input) throws DateTimeParserException {

        System.out.println("input is " + input);

        // LocalDateTime now = LocalDateTime.now();
        LocalDateTime dateTime;
        
        // https://stackoverflow.com/questions/22463062/how-can-i-parse-format-dates-with-localdatetime-java-8
        try {
            // dateTime = LocalDateTime.parse(input, INPUT_DATETIME_FORMAT);
            dateTime = LocalDateTime.parse(input, OUTPUT_DATETIME_FORMAT);
        } catch (DateTimeParseException e) {
            // throw new IllegalArgumentException(
            //     String.format("Invalid date format. Expected format: `%s`", INPUT_DATETIME_PATTERN)
            // );

            throw new DateTimeParserException(
                String.format("%s", Messages.ERROR_INVALID_DATETIME_FORMAT),
                String.format("Received `%s`", input),
                String.format("Expected format: `%s`", INPUT_DATETIME_PATTERN)
            );
        }

        // Validate the constructed LocalDateTime
        // if (dateTime.isBefore(now)) {
        //     throw new IllegalArgumentException("The date and time must be in the future.");
        // }

        System.out.println("dateTime is " + dateTime);
        return dateTime;
    }

    // Convert string to localdatetime
    public static LocalDateTime convertToDateTime(String input) throws DateTimeParserException {

        System.out.println("input is " + input);

        // LocalDateTime now = LocalDateTime.now();
        LocalDateTime dateTime;
        
        // https://stackoverflow.com/questions/22463062/how-can-i-parse-format-dates-with-localdatetime-java-8
        try {
            dateTime = LocalDateTime.parse(input, INPUT_DATETIME_FORMAT);
            // dateTime = LocalDateTime.parse(input, OUTPUT_DATETIME_FORMAT);
        } catch (DateTimeParseException e) {
            // throw new IllegalArgumentException(
            //     String.format("Invalid date format. Expected format: `%s`", INPUT_DATETIME_PATTERN)
            // );

            throw new DateTimeParserException(
                String.format("%s", Messages.ERROR_INVALID_DATETIME_FORMAT),
                String.format("Received `%s`", input),
                String.format("Expected format: `%s`", INPUT_DATETIME_PATTERN)
            );
        }

        // Validate the constructed LocalDateTime
        // if (dateTime.isBefore(now)) {
        //     throw new IllegalArgumentException("The date and time must be in the future.");
        // }

        System.out.println("dateTime is " + dateTime);
        return dateTime;
    }
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
