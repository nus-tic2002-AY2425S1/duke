package starkchatbot.taskmanager;

import starkchatbot.userui.StarkException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class DateTimeParser {

    private static String readableDate;
    private static String readableTime;


    public static String parseDateTime(String dateTime) {
        readableDate = "";
        readableTime = "";
        try {
            if (dateTime.length() == 15) {
                LocalDateTime localDateTime = LocalDateTime.parse(dateTime, DateTimeFormatter.ofPattern("yyyy-M-dd HHmm"));
                readableDate = localDateTime.format(DateTimeFormatter.ofPattern("MMMM dd yyyy"));
                readableTime = " " + localDateTime.format(DateTimeFormatter.ofPattern("hh:mma"));

            } else {

                LocalDate localDate = LocalDate.parse(dateTime, DateTimeFormatter.ofPattern("yyyy-M-dd"));
                readableDate = localDate.format(DateTimeFormatter.ofPattern("MMMM dd yyyy"));
            }
            return readableDate + readableTime;
        } catch (DateTimeParseException e) {
            throw new StarkException.InvalidDescriptionException("Unable to parse Date/Time. Input a valid Date-Time" + System.lineSeparator()
                                                                + " \t eg : \"YYYY-MM-DD HHmm\" or \"YYYY-MM-DD\" ");
        } catch (NullPointerException | IllegalArgumentException e) {
            throw new StarkException.InvalidDescriptionException("OOPS!!! Please re-enter Date-Time in below format" + System.lineSeparator()
                                                                + " \t eg : \"YYYY-MM-DD HHmm\" or \"YYYY-MM-DD\" ");
        } catch (Exception e) {
            throw new StarkException.InvalidDescriptionException(e.getMessage());
        }
    }

    public static String getDate() {
        return readableDate;
    }

    public static String getTime() {
        return readableTime;
    }

}
