package ruan.datetime;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents a DateTime object that can either store a LocalDateTime/LocalDate or a String if parsing fails
 */

public class DateTime {
    private LocalDateTime dateTime;
    private LocalDate date;
    private String dateTimeString;
    private static final DateTimeFormatter INPUT_FORMATTER_DATE = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private static final DateTimeFormatter INPUT_FORMATTER_DATETIME = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
    private static final DateTimeFormatter OUTPUT_FORMATTER_DATE = DateTimeFormatter.ofPattern("MMM dd yyyy");
    private static final DateTimeFormatter OUTPUT_FORMATTER_DATETIME = DateTimeFormatter.ofPattern("MMM dd yyyy, h:mm a");

    /**
     * Constructs a DateTime object from the input string
     * If string is valid datetime format, store as LocalDateTime
     * Otherwise, it is stored as string
     * @param input input string representing the datetime
     */
    public DateTime(String input) {
        try {
            // Try to parse input as LocalDateTime
            this.dateTime = LocalDateTime.parse(input, INPUT_FORMATTER_DATETIME);
            this.dateTimeString = null;
        } catch (DateTimeParseException e) {
            try {
                // If that fails, try to parse as LocalDate
                this.date = LocalDate.parse(input, INPUT_FORMATTER_DATE);
                this.dateTimeString = null;
            } catch (DateTimeParseException ex) {
                // If both parsing attempts fail, store as a raw string
                this.dateTime = null;
                this.date = null;
                this.dateTimeString = input;
            }
        }
    }

    /**
     * Checks if this DateTime contains a valid LocalDateTime
     * @return True if the input string was parsed successfully
     */
    public boolean isValidDateTime() {
        return dateTime != null || date != null;
    }

    /**
     * Returns formatted DateTime
     * If DateTime is valid, returns in a specific format
     * If not valid, returns the original string
     * @return A string representing the date and time
     */
    public String dateTimeToString() {
        if (dateTime != null) {
            return dateTime.format(OUTPUT_FORMATTER_DATETIME);
        } else if (date != null) {
            return date.format(OUTPUT_FORMATTER_DATE);
        } else {
            return dateTimeString;
        }
    }

}
