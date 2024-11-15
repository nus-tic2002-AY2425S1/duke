package josbot.parser;

import josbot.JosBotException;
import josbot.ui.UI;

import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateTimeParser {

    //inspired by https://howtodoinjava.com/java/date-time/java8-datetimeformatter-example/
    static final DateTimeFormatter FORMATTER_DISPLAY_DATETIME= DateTimeFormatter.ofPattern("dd MMMM yyyy 'at' hh:mm a");
    static final DateTimeFormatter FORMATTER_DISPLAY_TIME = DateTimeFormatter.ofPattern("hh:mm a");
    static final DateTimeFormatter FORMATTER_CHECK_TIME = DateTimeFormatter.ofPattern("HHmm");
    static final DateTimeFormatter FORMATTER_DISPLAY_DATE= DateTimeFormatter.ofPattern("dd MMMM yyyy");
    static final DateTimeFormatter FORMATTER_STORE_DATETIME = DateTimeFormatter.ofPattern("dd/MM/yyyy,HHmm");
    static final DateTimeFormatter FORMATTER_COMPARE_DATE= DateTimeFormatter.ofPattern("dd/MM/yyyy");
    static final DateTimeFormatter FORMATTER_STORE_DATE = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    /**
     *
     * Convert date/date & time in String format to LocalDateTime format
     *
     *
     * @param Deadline datetime in String format
     * @return Converted date/date & time in LocalDateTime format
     * @throws DateTimeException If date or time specified does not exist
     * @throws JosBotException If the datetime specified by user is in the wrong format
     * For example, it needs to be in dd/MM/yyyy format
     */
    public LocalDateTime convertToDateTime(String deadline_datetime) throws IndexOutOfBoundsException, JosBotException, DateTimeException {
        String[] datetime = deadline_datetime.split(" ");
        LocalDateTime dt = null;
            if (datetime.length == 2) {
                String[] date = datetime[0].split("/");
                String hour = datetime[1].substring(0, 2);
                String minute = datetime[1].substring(2, 4);
                dt = LocalDateTime.parse(date[2] + "-" + date[1] + "-" + date[0] + "T" + hour + ":" + minute);
            } else if (datetime.length == 1 && datetime[0].contains("/")) {

                String[] date = datetime[0].split("/");
                dt = LocalDateTime.parse(date[2] + "-" + date[1] + "-" + date[0] + "T00:00");
            }
            else {
                throw new JosBotException("invalid_datetime_format");
            }
        return dt;
    }

    public String convertToString(LocalDateTime dt, String purpose) throws DateTimeException {
        if(purpose.equals("view"))
        {
            if(dt.format(FORMATTER_CHECK_TIME).equals("0000"))
            {
                return dt.format(FORMATTER_DISPLAY_DATE);
            }
            return dt.format(FORMATTER_DISPLAY_DATETIME);
        }
        else if(purpose.equals("view_date"))
        {
            return dt.format(FORMATTER_COMPARE_DATE);
        }
        else if(purpose.equals("store"))
        {
            return dt.format(FORMATTER_STORE_DATETIME);
        }
        return dt.format(FORMATTER_DISPLAY_TIME);
    }


    public String getTimeGreeting() throws DateTimeException {
        LocalDateTime dt = LocalDateTime.now();
        int hour = dt.getHour();
        if(hour < 12)
        {
            return "Morning";
        }
        else if(hour < 17)
        {
            return "Afternoon";
        }
        else {
            return "Evening";
        }
    }
}
